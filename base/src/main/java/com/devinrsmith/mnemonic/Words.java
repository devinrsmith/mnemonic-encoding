package com.devinrsmith.mnemonic;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Words implements Iterable<String> {
    private final String[] words;

    public static Words parse(String input) {
        final String[] words = input.split("\\s+");
        return new Words(words);
    }

    Words(String[] words) {
        this.words = words;
    }

    public Stream<String> stream() {
        return Stream.of(words);
    }

    @Override
    public Iterator<String> iterator() {
        return Arrays.asList(words).iterator();
    }

    @Override
    public Spliterator<String> spliterator() {
        return Arrays.spliterator(words);
    }

    @Override
    public void forEach(Consumer<? super String> action) {
        for (String word : words) {
            action.accept(word);
        }
    }

    public int size() {
        return words.length;
    }

    @Override
    public String toString() {
        return Arrays.toString(words);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Words strings = (Words) o;

        return Arrays.equals(words, strings.words);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(words);
    }
}
