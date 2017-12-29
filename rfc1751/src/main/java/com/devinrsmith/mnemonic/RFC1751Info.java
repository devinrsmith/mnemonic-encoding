package com.devinrsmith.mnemonic;

import java.util.Locale;

public enum RFC1751Info implements WordListInfo {
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
        return "rfc1751.txt";
    }
}
