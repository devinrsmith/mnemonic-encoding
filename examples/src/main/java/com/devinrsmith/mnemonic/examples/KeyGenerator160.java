package com.devinrsmith.mnemonic.examples;

import com.devinrsmith.mnemonic.MnemonicEncoding;

public class KeyGenerator160 extends KeyGeneratorBase {
    private KeyGenerator160() {
        super(MnemonicEncoding.BIP39_EN.instance(), 20);
    }

    public static void main(String[] args) {
        new KeyGenerator160().run();
    }
}
