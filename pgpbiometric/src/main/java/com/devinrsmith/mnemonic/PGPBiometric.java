package com.devinrsmith.mnemonic;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public enum PGPBiometric implements Encoding {
    INSTANCE;

    private final RawEncoding evenEncoding;
    private final RawEncoding oddEncoding;

    PGPBiometric() {
        final WordList evenWordList;
        try {
            evenWordList = WordListLoader.loadIndexedWordlist(PGPEvenInfo.INSTANCE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        final WordList oddWordList;
        try {
            oddWordList = WordListLoader.loadIndexedWordlist(PGPOddInfo.INSTANCE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        evenEncoding = new RawEncoding(evenWordList);
        oddEncoding = new RawEncoding(oddWordList);
    }

    @Override
    public Words encode(byte[] data) {
        final byte[] even = new byte[(data.length + 1) / 2];
        final byte[] odd = new byte[data.length / 2];
        for (int i = 0; i < data.length; ++i) {
            if (i % 2 == 0) {
                even[i / 2] = data[i];
            } else {
                odd[i / 2] = data[i];
            }
        }
        final Words evenWords = evenEncoding.encode(even, even.length * Byte.SIZE);
        final Words oddWords = oddEncoding.encode(odd, odd.length * Byte.SIZE);

        final List<String> evenList = evenWords.stream().collect(Collectors.toList());
        final List<String> oddList = oddWords.stream().collect(Collectors.toList());

        final String[] words = new String[data.length];
        for (int i = 0; i < data.length; ++i) {
            words[i] = (i % 2 == 0 ? evenList : oddList).get(i / 2);
        }
        return new Words(words);
    }

    @Override
    public byte[] decode(Words words) {
        final String[] even = new String[(words.size() + 1) / 2];
        final String[] odd = new String[words.size() / 2];
        final Iterator<String> it = words.iterator();
        int i = 0;
        while (it.hasNext()) {
            even[i] = it.next();
            if (it.hasNext()) {
                odd[i] = it.next();
            }
            ++i;
        }
        final byte[] evenDecoded = evenEncoding.decode(new Words(even));
        final byte[] oddDecoded = oddEncoding.decode(new Words(odd));
        final byte[] decoded = new byte[evenDecoded.length + oddDecoded.length];
        for (i = 0; i < decoded.length; ++i) {
            decoded[i] = i % 2 == 0 ? evenDecoded[i / 2] : oddDecoded[i / 2];
        }
        return decoded;
    }
}
