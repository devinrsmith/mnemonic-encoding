package com.devinrsmith.mnemonic.examples;

import com.devinrsmith.mnemonic.MnemonicEncoding;
import java.io.IOException;

public class InputGenerator160 extends PipeBase {
    private InputGenerator160() {
        super(MnemonicEncoding.BIP39_EN.instance(), 20);
    }

    public static void main(String[] args) throws IOException {
        new InputGenerator160().run();
    }
}
