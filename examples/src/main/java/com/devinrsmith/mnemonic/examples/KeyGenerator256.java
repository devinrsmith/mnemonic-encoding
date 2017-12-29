package com.devinrsmith.mnemonic.examples;

import com.devinrsmith.mnemonic.MnemonicEncoding;

public class KeyGenerator256 extends KeyGeneratorBase {
    private KeyGenerator256() {
        super(MnemonicEncoding.BIP39_EN.instance(), 32);
    }

    public static void main(String[] args) {
        new KeyGenerator256().run();
    }
}
