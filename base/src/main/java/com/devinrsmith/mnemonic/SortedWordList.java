package com.devinrsmith.mnemonic;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;
import java.util.OptionalInt;
import java.util.Spliterator;

import static java.lang.String.format;

public class SortedWordList extends WordListBase {
    private final Comparator<? super String> comparator;

    SortedWordList(String[] words, Locale locale, Comparator<? super String> comparator) {
        super(words, locale);
        for (int i = 1; i < words.length; ++i) {
            if (comparator.compare(words[i - 1], words[i]) >= 0) {
                throw new IllegalArgumentException(format(
                    locale,
                    "The words must be in sorted order. Found [%s] >= [%s]",
                    words[i - 1],
                    words[i]));
            }
        }
        this.comparator = comparator;
    }

    @Override
    public OptionalInt lookup(String word) {
        final int index = Arrays.binarySearch(words, word, comparator);
        return index < 0 ? OptionalInt.empty() : OptionalInt.of(index);
    }

    @Override
    public Spliterator<String> spliterator() {
        return Arrays.spliterator(words);
    }
}
