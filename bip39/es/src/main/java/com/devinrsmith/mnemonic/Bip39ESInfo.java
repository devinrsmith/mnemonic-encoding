package com.devinrsmith.mnemonic;

import java.util.Locale;

public enum Bip39ESInfo implements WordListInfo {
    INSTANCE;

    private static final Locale LOCALE = new Locale("es", "ES");

    @Override
    public int numWords() {
        return 2048;
    }

    @Override
    public Locale locale() {
        return LOCALE;
    }

    @Override
    public String wordlistResource() {
        return "bip39-es.txt";
    }
}
