package com.devinrsmith.mnemonic;

import java.util.Locale;

public enum PGPOddInfo implements WordListInfo {
    INSTANCE;

    @Override public int numWords() {
        return 256;
    }

    @Override public Locale locale() {
        return Locale.ENGLISH;
    }

    @Override public String wordlistResource() {
        return "pgp-odd.txt";
    }
}
