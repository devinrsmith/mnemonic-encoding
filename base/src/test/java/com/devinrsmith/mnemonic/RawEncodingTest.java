package com.devinrsmith.mnemonic;

import java.util.Locale;
import java.util.Random;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RawEncodingTest {

    private static final WordList
        WORDLIST = new SortedWordList(new String[] {"a", "b", "c", "d", "e", "f", "g", "h"}, Locale.ENGLISH, String::compareTo);

    @Test
    public void name() {
        final byte[] data = new byte[24];
        final Random random = new Random();
        final RawEncoding encoding = new RawEncoding(WORDLIST);

        final int times = 1024 * 1024;
        for (int i = 0; i < times; ++i) {
            random.nextBytes(data);
            final Words words = encoding.encode(data, data.length * Byte.SIZE);
            final byte[] decoded = encoding.decode(words);
            assertThat(decoded).containsExactly(data);
        }
    }
}
