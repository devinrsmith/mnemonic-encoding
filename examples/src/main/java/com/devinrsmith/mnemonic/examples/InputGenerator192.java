package com.devinrsmith.mnemonic.examples;

import com.devinrsmith.mnemonic.MnemonicEncoding;
import java.io.IOException;

public class InputGenerator192 extends PipeBase {
    private InputGenerator192() {
        super(MnemonicEncoding.BIP39_EN.instance(), 24);
    }

    public static void main(String[] args) throws IOException {
        new InputGenerator192().run();
    }
}
