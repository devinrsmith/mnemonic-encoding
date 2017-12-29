package com.devinrsmith.mnemonic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.Collator;

public class WordListLoader {
    public static WordList loadWordlist(WordListInfo info) throws IOException {
        final BufferedReader reader = new BufferedReader(
            new InputStreamReader(info.getClass().getResourceAsStream(info.wordlistResource())));
        final String[] words = new String[info.numWords()];
        int index = 0;
        String line;
        while ((line = reader.readLine()) != null) {
            words[index] = line;
            ++index;
        }
        Preconditions.checkState(index == info.numWords());
        return new WordList(words, Collator.getInstance(info.locale()), info.locale());
    }
}
