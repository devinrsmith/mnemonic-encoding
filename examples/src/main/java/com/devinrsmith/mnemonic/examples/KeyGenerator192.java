package com.devinrsmith.mnemonic.examples;

import com.devinrsmith.mnemonic.MnemonicEncoding;

public class KeyGenerator192 extends KeyGeneratorBase {
    private KeyGenerator192() {
        super(MnemonicEncoding.BIP39_EN.instance(), 24);
    }

    public static void main(String[] args) {
        new KeyGenerator192().run();
    }
}
