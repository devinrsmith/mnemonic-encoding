package com.devinrsmith.mnemonic;

import com.google.common.io.BaseEncoding;
import java.nio.ByteBuffer;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.assertj.core.api.Assertions.assertThat;

public class Bip39ENTest {
    @ParameterizedTest
    @CsvFileSource(resources = "/com/devinrsmith/mnemonic/bip39-en-vectors.csv")
    void csv(String input, String words) {
        final byte[] data = BaseEncoding.base16().lowerCase().decode(input);
        final Words expected = Words.parse(words);
        final Words encoded = Bip39EN.INSTANCE.encode(data);
        assertThat(encoded).isEqualTo(expected);
        final byte[] decoded = Bip39EN.INSTANCE.decode(expected);
        assertThat(ByteBuffer.wrap(decoded)).isEqualTo(ByteBuffer.wrap(data));
    }
}
