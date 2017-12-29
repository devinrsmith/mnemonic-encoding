package com.devinrsmith.mnemonic;

import java.util.Locale;

public interface WordListInfo {
    int numWords();
    Locale locale();
    String wordlistResource();
}
