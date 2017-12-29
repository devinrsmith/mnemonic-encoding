package com.devinrsmith.mnemonic;

class Math {
    static boolean isPowerOfTwo(int x) {
        return x > 0 & (x & (x - 1)) == 0;
    }

    static int ceilLog2(int x) {
        return Integer.SIZE - Integer.numberOfLeadingZeros(x - 1);
    }
}
