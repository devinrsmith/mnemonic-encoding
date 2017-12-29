package com.devinrsmith.mnemonic;

import java.util.Locale;

public enum Bip39ENInfo implements WordListInfo {
    INSTANCE;

    @Override
    public int numWords() {
        return 2048;
    }

    @Override
    public Locale locale() {
        return Locale.ENGLISH;
    }

    @Override
    public String wordlistResource() {
        return "bip39-en.txt";
    }
}
