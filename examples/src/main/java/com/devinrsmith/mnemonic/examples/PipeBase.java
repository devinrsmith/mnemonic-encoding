package com.devinrsmith.mnemonic.examples;

import com.devinrsmith.mnemonic.Encoding;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.stream.Collectors;

/**
 * Reads bytes from the input stream and outputs the encoded words one per line.
 */
public class PipeBase {
    private final Encoding encoding;
    private final InputStream in;
    private final PrintStream out;
    private final int bytes;

    PipeBase(Encoding encoding, int bytes) {
        this(encoding, new BufferedInputStream(System.in, bytes), System.out, bytes);
    }

    PipeBase(Encoding encoding, InputStream in, PrintStream out, int bytes) {
        this.encoding = encoding;
        this.in = in;
        this.out = out;
        this.bytes = bytes;
    }

    public void run() throws IOException {
        final byte[] key = new byte[bytes];
        int pos = 0;
        while (pos < key.length) {
            final int read = in.read(key, pos, key.length - pos);
            if (read == -1) {
                return; // todo, exit code?
            }
            pos += read;
        }
        final String output = encoding
            .encode(key)
            .stream()
            .collect(Collectors.joining(System.lineSeparator()));
        out.println(output);
    }
}
