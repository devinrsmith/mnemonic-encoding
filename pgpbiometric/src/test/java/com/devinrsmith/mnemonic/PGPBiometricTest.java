package com.devinrsmith.mnemonic;

import com.google.common.io.BaseEncoding;
import java.nio.ByteBuffer;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.assertj.core.api.Assertions.assertThat;

public class PGPBiometricTest {
    @ParameterizedTest
    @CsvFileSource(resources = "/com/devinrsmith/mnemonic/pgpbio-vectors.csv")
    void csv(String input, String words) {
        final byte[] data = BaseEncoding.base16().decode(input);
        final Words expected = Words.parse(words);
        final Words encoded = PGPBiometric.INSTANCE.encode(data);
        assertThat(encoded).isEqualTo(expected);
        final byte[] decoded = PGPBiometric.INSTANCE.decode(expected);
        assertThat(ByteBuffer.wrap(decoded)).isEqualTo(ByteBuffer.wrap(data));
    }
}
