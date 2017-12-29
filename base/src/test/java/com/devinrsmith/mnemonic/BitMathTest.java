package com.devinrsmith.mnemonic;

import java.util.Random;
import org.junit.jupiter.api.Test;

import static com.devinrsmith.mnemonic.BitMath.getInt;
import static com.devinrsmith.mnemonic.BitMath.writeInt;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;

public class BitMathTest {
    @Test
    public void name() {
        final byte[] data = {(byte) 0b10010001, (byte) 0b01011001};
        assertThat(getInt(data, 0, 8)).isEqualTo(0b10010001);
        assertThat(getInt(data, 0, 4)).isEqualTo(0b1001);
        assertThat(getInt(data, 1, 4)).isEqualTo(0b0010);
        assertThat(getInt(data, 7, 1)).isEqualTo(0b1);

        assertThat(getInt(data, 6, 3)).isEqualTo(0b010);
        assertThat(getInt(data, 6, 4)).isEqualTo(0b0101);
        assertThat(getInt(data, 6, 5)).isEqualTo(0b01010);
        assertThat(getInt(data, 6, 6)).isEqualTo(0b010101);
        assertThat(getInt(data, 6, 7)).isEqualTo(0b0101011);
        assertThat(getInt(data, 6, 8)).isEqualTo(0b01010110);
        assertThat(getInt(data, 6, 9)).isEqualTo(0b010101100);
        assertThat(getInt(data, 6, 10)).isEqualTo(0b0101011001);

    }

    @Test
    public void name2() {
        final byte[] data = {(byte) 0b00000000, (byte) 0b10000000};
        assertThat(getInt(data, 6, 3)).isEqualTo(0b001);
    }

    @Test
    public void test() {
        byte[] data = new byte[2];
        writeInt(data, 6, 3, 0);
        assertThat(data).containsExactly((byte)0b00000000, (byte)0b00000000);

        data = new byte[2];
        writeInt(data, 6, 3, 1);
        assertThat(data).containsExactly((byte)0b00000000, (byte)0b10000000);

        data = new byte[2];
        writeInt(data, 6, 3, 2);
        assertThat(data).containsExactly((byte)0b00000001, (byte)0b00000000);

        data = new byte[2];
        writeInt(data, 6, 3, 3);
        assertThat(data).containsExactly((byte)0b00000001, (byte)0b10000000);

        data = new byte[2];
        writeInt(data, 6, 3, 4);
        assertThat(data).containsExactly((byte)0b00000010, (byte)0b00000000);

        data = new byte[2];
        writeInt(data, 6, 3, 5);
        assertThat(data).containsExactly((byte)0b00000010, (byte)0b10000000);

        data = new byte[2];
        writeInt(data, 6, 3, 6);
        assertThat(data).containsExactly((byte)0b00000011, (byte)0b00000000);

        data = new byte[2];
        writeInt(data, 6, 3, 7);
        assertThat(data).containsExactly((byte)0b00000011, (byte)0b10000000);
    }

    @Test
    public void invalidWriteInt() {
        try {
            writeInt(new byte[1], 0, 3, 8);
            failBecauseExceptionWasNotThrown(IllegalArgumentException.class);
        } catch (IllegalArgumentException e) {
            // as expected
        }
    }

    @Test
    public void stasticalTest() {
        final byte[] bytes = new byte[2];
        final Random random = new Random();
        final int[] hist = new int[8];

        final int numRuns = 1024 * 1024;
        for (int i = 0; i < numRuns; ++i) {
            random.nextBytes(bytes);
            final int index = getInt(bytes, 6, 3);
            hist[index]++;
        }

        // todo: figure out what the p-value actually is for different percentages

        final int expectedOutput = 131072;

        for (int h : hist) {
            assertThat(h).isBetween(expectedOutput - 1000, expectedOutput + 1000);
        }
    }
}
