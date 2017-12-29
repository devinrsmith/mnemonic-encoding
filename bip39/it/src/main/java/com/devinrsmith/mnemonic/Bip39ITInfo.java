package com.devinrsmith.mnemonic;

import java.util.Locale;

public enum Bip39ITInfo implements WordListInfo {
    INSTANCE;

    @Override
    public int numWords() {
        return 2048;
    }

    @Override
    public Locale locale() {
        return Locale.ITALIAN;
    }

    @Override
    public String wordlistResource() {
        return "bip39-it.txt";
    }
}
