package com.devinrsmith.mnemonic;

import java.util.Locale;

public enum Bip39FRInfo implements WordListInfo {
    INSTANCE;

    @Override
    public int numWords() {
        return 2048;
    }

    @Override
    public Locale locale() {
        return Locale.FRENCH;
    }

    @Override
    public String wordlistResource() {
        return "bip39-fr.txt";
    }
}
