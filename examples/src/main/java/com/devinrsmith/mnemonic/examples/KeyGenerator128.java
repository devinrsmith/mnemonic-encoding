package com.devinrsmith.mnemonic.examples;

import com.devinrsmith.mnemonic.MnemonicEncoding;

public class KeyGenerator128 extends KeyGeneratorBase {
    private KeyGenerator128() {
        super(MnemonicEncoding.BIP39_EN.instance(), 16);
    }

    public static void main(String[] args) {
        new KeyGenerator128().run();
    }
}
