package com.devinrsmith.mnemonic;

public interface Encoding {
    Words encode(byte[] data);
    byte[] decode(Words words);
}
