package com.devinrsmith.mnemonic;

import java.io.IOException;

public enum RFC1751 implements Encoding {
    INSTANCE;

    private final RawEncoding encoding;

    RFC1751() {
        final WordList wordList;
        try {
            wordList = WordListLoader.loadSortedWordlist(RFC1751Info.INSTANCE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        encoding = new RawEncoding(wordList);
    }

    @Override
    public Words encode(byte[] data) {
        return encoding.encode(data, data.length * Byte.SIZE);
    }

    @Override
    public byte[] decode(Words words) {
        return encoding.decode(words);
    }
}
