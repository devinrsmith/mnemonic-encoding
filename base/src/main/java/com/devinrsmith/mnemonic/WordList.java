package com.devinrsmith.mnemonic;

import java.util.Iterator;
import java.util.Locale;
import java.util.OptionalInt;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;

import static java.lang.String.format;

public interface WordList extends Iterable<String> {
    OptionalInt lookup(String word);

    default int forceLookup(String word) {
        final OptionalInt lookup = lookup(word);
        if (lookup.isPresent()) {
            return lookup.getAsInt();
        }
        throw new IllegalArgumentException(
            format(getLocale(), "Unable to find word in wordlist. [%s]", word));
    }

    String get(int index);

    int size();

    Locale getLocale();

    @Override
    default Iterator<String> iterator() {
        final int L = size();
        return new Iterator<String>() {
            int i = 0;
            @Override
            public boolean hasNext() {
                return i < L;
            }

            @Override
            public String next() {
                return get(i++);
            }
        };
    }

    @Override
    default void forEach(Consumer<? super String> action) {
        final int L = size();
        for (int i = 0; i < L; ++i) {
            action.accept(get(i));
        }
    }

    @Override
    default Spliterator<String> spliterator() {
        final int L = size();
        final int additionalCharacteristics =
            Spliterator.SIZED | Spliterator.IMMUTABLE | Spliterator.ORDERED;
        return new Spliterators.AbstractSpliterator<String>(L, additionalCharacteristics) {
            int i = 0;
            @Override
            public boolean tryAdvance(Consumer<? super String> action) {
                if (i >= L) {
                    return false;
                }
                action.accept(get(i++));
                return true;
            }
        };
    }
}
