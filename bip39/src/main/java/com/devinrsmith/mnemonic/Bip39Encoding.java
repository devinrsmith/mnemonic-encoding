package com.devinrsmith.mnemonic;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Bip39Encoding implements Encoding {

    private final RawEncoding encoding;

    Bip39Encoding(WordListInfo info) {
        final WordList wordList;
        try {
            wordList = WordListLoader.loadSortedWordlist(info);
        } catch (IOException e) {
            throw new WordListRuntimeException(e);
        }
        encoding = new RawEncoding(wordList);
    }

    private static boolean canEncode(int len) {
        switch (len) {
            case 128:
            case 160:
            case 192:
            case 224:
            case 256:
                return true;
        }
        return false;
    }

    private static boolean canDecode(int words) {
        switch (words) {
            case 12:
            case 15:
            case 18:
            case 21:
            case 24:
                return true;
        }
        return false;
    }

    private static byte[] sha256(byte[] data) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        digest.update(data);
        return digest.digest();
    }

    public Words encode(byte[] data) {
        if (!canEncode(data.length * Byte.SIZE)) {
            throw new IllegalArgumentException("Invalid data length");
        }

        final byte[] sha256 = sha256(data);
        final int checksumLenBits = data.length * Byte.SIZE / 32;
        // round up, todo always 1 byte?
        final int checksumLenBytes = (checksumLenBits + Byte.SIZE - 1) / Byte.SIZE;

        final byte[] dataWithChecksum = new byte[data.length + checksumLenBytes];
        System.arraycopy(data, 0, dataWithChecksum, 0, data.length);
        System.arraycopy(sha256, 0, dataWithChecksum, data.length, checksumLenBytes);

        return encoding.encode(dataWithChecksum, data.length * Byte.SIZE + checksumLenBits);
    }

    public byte[] decode(Words words) {
        if (!canDecode(words.size())) {
            throw new IllegalArgumentException("Invalid number of words");
        }

        final int checksumBits = words.size() / 3;
        //final int dataBits = words.size() * 11 - checksumBits;


        final byte[] decoded = encoding.decode(words);


        final byte checksum = decoded[decoded.length - 1];
        final byte[] real = new byte[decoded.length - 1];
        System.arraycopy(decoded, 0, real, 0, real.length);

        if (!BitMath.equals(sha256(real)[0], checksum, checksumBits)) {
            throw new IllegalStateException("Invalid checksum");
        }

        return real;
    }
}
