package com.devinrsmith.mnemonic;

import java.text.CollationKey;
import java.text.Collator;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.OptionalInt;

public class IndexedWordList extends WordListBase {
    private final Collator collator;
    private final Map<Wrapper, Integer> map;

    private class Wrapper {
        private final CollationKey key;

        Wrapper(String source) {
            this.key = collator.getCollationKey(source);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Wrapper wrapper = (Wrapper) o;
            return key.equals(wrapper.key);
        }

        @Override
        public int hashCode() {
            return key.hashCode();
        }
    }

    IndexedWordList(String[] words, Locale locale, Collator collator) {
        super(words, locale);
        Preconditions.checkArgument(Collator.getInstance(locale).equals(collator));
        this.collator = collator;
        map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.put(new Wrapper(words[i]), i);
        }
    }

    @Override
    public OptionalInt lookup(String word) {
        final Integer index = map.get(new Wrapper(word));
        return index == null ? OptionalInt.empty() : OptionalInt.of(index);
    }
}
