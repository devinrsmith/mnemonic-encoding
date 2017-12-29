package com.devinrsmith.mnemonic.examples;

import com.devinrsmith.mnemonic.MnemonicEncoding;
import java.io.IOException;

public class InputGenerator128 extends PipeBase {
    private InputGenerator128() {
        super(MnemonicEncoding.BIP39_EN.instance(), 16);
    }

    public static void main(String[] args) throws IOException {
        new InputGenerator128().run();
    }
}
