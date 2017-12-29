package com.devinrsmith.mnemonic;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;
import java.util.OptionalInt;

import static java.lang.String.format;

public class WordList {
    private final String[] words;
    private final Comparator<? super String> comparator;
    private final Locale locale;

    WordList(String[] words, Comparator<? super String> comparator, Locale locale) {
        // todo: should this ordering be a requirement?
        for (int i = 1; i < words.length; ++i) {
            if (comparator.compare(words[i - 1], words[i]) >= 0) {
                throw new IllegalArgumentException(format(
                    locale,
                    "The words must be in sorted order. Found [%s] >= [%s]",
                    words[i - 1],
                    words[i]));
            }
        }
        this.words = words;
        this.comparator = comparator;
        this.locale = locale;
    }

    public OptionalInt lookup(String word) {
        final int index = Arrays.binarySearch(words, word, comparator);
        return index < 0 ? OptionalInt.empty() : OptionalInt.of(index);
    }

    public int forceLookup(String word) {
        final OptionalInt lookup = lookup(word);
        if (lookup.isPresent()) {
            return lookup.getAsInt();
        }
        throw new IllegalArgumentException(
            format(locale, "Unable to find word in wordlist. [%s]", word));
    }

    public String get(int index) {
        return words[index];
    }

    public int size() {
        return words.length;
    }

    public Locale getLocale() {
        return locale;
    }
}
