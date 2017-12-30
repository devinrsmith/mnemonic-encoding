package com.devinrsmith.mnemonic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.Collator;

public class WordListLoader {
    public static WordList loadSortedWordlist(WordListInfo info) throws IOException {
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
        final Collator collator = Collator.getInstance(info.locale());
        //collator.setStrength(Collator.PRIMARY);
        return new SortedWordList(words, info.locale(), collator);
    }

    public static WordList loadIndexedWordlist(WordListInfo info) throws IOException {
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
        final Collator collator = Collator.getInstance(info.locale());
        //collator.setStrength(Collator.PRIMARY);
        return new IndexedWordList(words, info.locale(), collator);
    }
}
