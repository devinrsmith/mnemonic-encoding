package com.devinrsmith.mnemonic;

import static java.lang.String.format;

class RawEncoding {
    private final WordList wordlist;
    private final int bitsPerWord;

    RawEncoding(WordList wordlist) {
        Preconditions.checkArgument(wordlist.size() > 1);
        Preconditions.checkArgument(Math.isPowerOfTwo(wordlist.size()));
        this.wordlist = wordlist;
        this.bitsPerWord = Math.ceilLog2(wordlist.size());
    }

    public Words encode(byte[] data, int bits) {
        Preconditions.checkArgument(bits <= data.length * Byte.SIZE);
        Preconditions.checkArgument(bits > (data.length - 1) * Byte.SIZE);

        if (bits % bitsPerWord != 0) {
            throw new IllegalArgumentException(format(
                "Can't encode data, improper length. Must be multiple of %d bits.",
                bitsPerWord));
        }
        final int numWords = bits / bitsPerWord;

        final String[] words = new String[numWords];
        int bitPosition = 0;
        for (int i = 0; i < numWords; ++i, bitPosition += bitsPerWord) {
            final int index = BitMath.getInt(data, bitPosition, bitsPerWord);
            words[i] = wordlist.get(index);
        }
        return new Words(words);
    }

    public byte[] decode(Words words) {
        final int totalBits = bitsPerWord * words.size();
        /*if (totalBits % Byte.SIZE != 0) {
            throw new IllegalArgumentException("Invalid todo");
        }*/
        final byte[] decoded = new byte[(totalBits + Byte.SIZE - 1) / Byte.SIZE];
        int bitPosition = 0;
        for (String word : words) {
            final int index = wordlist.forceLookup(word);
            BitMath.writeInt(decoded, bitPosition, bitsPerWord, index);
            bitPosition += bitsPerWord;
        }
        return decoded;
    }
}
