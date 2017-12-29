package com.devinrsmith.mnemonic.examples;

import com.devinrsmith.mnemonic.Encoding;
import java.io.PrintStream;
import java.security.SecureRandom;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Reads random bytes and outputs the encoded words one per line.
 */
public class KeyGeneratorBase implements Runnable {
    private final Encoding encoding;
    private final PrintStream out;
    private final int bytes;

    KeyGeneratorBase(Encoding encoding, int bytes) {
        this(encoding, System.out, bytes);
    }

    KeyGeneratorBase(Encoding encoding, PrintStream out, int bytes) {
        this.encoding = encoding;
        this.out = out;
        this.bytes = bytes;
    }

    @Override
    public void run() {
        final Random random = new SecureRandom();
        final byte[] key = new byte[bytes];
        random.nextBytes(key);
        final String output = encoding
            .encode(key)
            .stream()
            .collect(Collectors.joining(System.lineSeparator()));
        out.println(output);
    }
}
