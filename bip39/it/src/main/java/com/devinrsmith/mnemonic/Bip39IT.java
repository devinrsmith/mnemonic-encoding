package com.devinrsmith.mnemonic;

import java.util.Random;

public enum Bip39IT implements Encoding {
    INSTANCE;

    private final Bip39Encoding encoding;

    Bip39IT() {
        encoding = new Bip39Encoding(Bip39ITInfo.INSTANCE);
    }

    @Override
    public Words encode(byte[] data) {
        return encoding.encode(data);
    }

    @Override
    public byte[] decode(Words words) {
        return encoding.decode(words);
    }

    public static void main(String[] args) {
        final Random r = new Random();
        final byte[] key = new byte[16];
        r.nextBytes(key);

        final Words words = Bip39IT.INSTANCE.encode(key);
        System.out.println(words);
    }
}
