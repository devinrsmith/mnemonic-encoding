package com.devinrsmith.mnemonic.examples;

import com.devinrsmith.mnemonic.MnemonicEncoding;
import java.io.IOException;

public class InputGenerator224 extends PipeBase {
    private InputGenerator224() {
        super(MnemonicEncoding.BIP39_EN.instance(), 28);
    }

    public static void main(String[] args) throws IOException {
        new InputGenerator224().run();
    }
}
