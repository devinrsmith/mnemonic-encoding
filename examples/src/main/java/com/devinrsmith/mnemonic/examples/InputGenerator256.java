package com.devinrsmith.mnemonic.examples;

import com.devinrsmith.mnemonic.MnemonicEncoding;
import java.io.IOException;

public class InputGenerator256 extends PipeBase {
    private InputGenerator256() {
        super(MnemonicEncoding.BIP39_EN.instance(), 32);
    }

    public static void main(String[] args) throws IOException {
        new InputGenerator256().run();
    }
}
