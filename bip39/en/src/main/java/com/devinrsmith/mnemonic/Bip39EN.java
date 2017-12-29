package com.devinrsmith.mnemonic;

public enum Bip39EN implements Encoding {
    INSTANCE;

    private final Bip39Encoding encoding;

    Bip39EN() {
        encoding = new Bip39Encoding(Bip39ENInfo.INSTANCE);
    }

    @Override
    public Words encode(byte[] data) {
        return encoding.encode(data);
    }

    @Override
    public byte[] decode(Words words) {
        return encoding.decode(words);
    }
}
