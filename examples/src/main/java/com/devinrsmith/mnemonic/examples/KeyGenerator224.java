package com.devinrsmith.mnemonic.examples;

import com.devinrsmith.mnemonic.MnemonicEncoding;

public class KeyGenerator224 extends KeyGeneratorBase {
    private KeyGenerator224() {
        super(MnemonicEncoding.BIP39_EN.instance(), 28);
    }

    public static void main(String[] args) {
        new KeyGenerator224().run();
    }
}
