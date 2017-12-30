package com.devinrsmith.mnemonic;

import java.util.Locale;

public abstract class WordListBase implements WordList {
    protected final String[] words;
    protected final Locale locale;

    WordListBase(String[] words, Locale locale) {
        this.words = words;
        this.locale = locale;
    }

    @Override
    public String get(int index) {
        return words[index];
    }

    @Override
    public int size() {
        return words.length;
    }

    @Override public Locale getLocale() {
        return locale;
    }
}
