package com.devinrsmith.mnemonic;

import static com.devinrsmith.mnemonic.Preconditions.checkArgument;

class BitMath {
    static int getInt(final byte[] data, final int startingBitPosition, final int numBits) {
        checkArgument(numBits > 0 && numBits < Integer.SIZE);

        int output = 0;
        int outputBitPosition = 0;

        int bytePosition = startingBitPosition / Byte.SIZE;
        final int startBitBytePosition = startingBitPosition % Byte.SIZE;

        // pre-byte, capturing the first bits of data when the bitPosition isn't on a byte boundary
        if (startBitBytePosition != 0) {
            final int tailBits = Byte.SIZE - startBitBytePosition;
            output = tail(output, outputBitPosition, data[bytePosition], tailBits);
            outputBitPosition += tailBits;
            ++bytePosition;
        }

        // bytes, capturing the middle bits that are full bytes
        while (numBits - outputBitPosition >= Byte.SIZE) {
            output = set(output, outputBitPosition, data[bytePosition]);
            outputBitPosition += Byte.SIZE;
            ++bytePosition;
        }

        // post-byte, capturing the last bits of data when the ending isn't on a byte boundary
        final int remainingBits = numBits - outputBitPosition;
        if (remainingBits > 0) {
            output = head(output, outputBitPosition, data[bytePosition], remainingBits);
            outputBitPosition += remainingBits;
            ++bytePosition;
        }

        return output >>> (Integer.SIZE - numBits);
    }

    static int set(int bits, int bitPosition, byte x) {
        return bits | ((x & 0xFF) << (Integer.SIZE - Byte.SIZE - bitPosition));
    }

    static int tail(int bits, int bitPosition, byte x, int numBits) {
        Preconditions.checkArgument(numBits > 0 && numBits < Byte.SIZE);
        return set(bits, bitPosition, (byte)((x & 0xFF) << (Byte.SIZE - numBits)));
    }

    static int head(int bits, int bitPosition, byte x, int numBits) {
        //Preconditions.checkArgument(numBits > 0 && numBits < Byte.SIZE);
        // todo: which is better?
        //final int mask = 0b1111111100000000 >> numBits;
        final int mask = 0b11111111 << (Byte.SIZE - numBits);
        return set(bits, bitPosition, (byte)(x & mask));
    }

    /**
     * Assumes that data is empty at all positions >= bitPosition
     */
    /*static void writeInt(byte[] data, int bitPosition, int numBits, int x) {
        checkArgument(numBits > 0 && numBits < Integer.SIZE);
        checkArgument(Math.ceilLog2(x + 1) <= numBits);

        int bytePosition = bitPosition / Byte.SIZE;
        int bitBytePosition = bitPosition % Byte.SIZE;

        int bitsWritten = 0;

        // fill in partially filled byte
        if (bitBytePosition != 0) {
            final int toFillBits = Byte.SIZE - bitBytePosition;
            data[bytePosition] |= (byte)(x >> (numBits - toFillBits));
            bitsWritten += toFillBits;
            ++bytePosition;
        }

        // fill in full bytes
        while (numBits - bitsWritten >= Byte.SIZE) {
            data[bytePosition] = (byte)(x >> (numBits - bitsWritten - Byte.SIZE));
            bitsWritten += Byte.SIZE;
            ++bytePosition;
        }

        // fill in last partial byte
        if (bitsWritten < numBits) {
            data[bytePosition] = (byte)(x << (Byte.SIZE - (numBits - bitsWritten)));
        }
    }*/

    static void writeInt(byte[] data, int bitPosition, int numBits, int x) {
        checkArgument(numBits > 0 && numBits < Integer.SIZE);
        checkArgument(Math.ceilLog2(x + 1) <= numBits);

        int bytePosition = bitPosition / Byte.SIZE;
        int bitBytePosition = bitPosition % Byte.SIZE;
        int bitsRemainingInCurrentByte = Byte.SIZE - bitBytePosition;

        if (numBits <= bitsRemainingInCurrentByte) {
            // easy case
            data[bytePosition] |= x << (bitsRemainingInCurrentByte - numBits);
            return;
        }

        final int remainingBits = numBits - bitsRemainingInCurrentByte;
        data[bytePosition] |= x >> remainingBits;
        x &= ((1 << remainingBits) - 1);
        // recursion not ideal here, but will be limited in depth b/c only dealing w/ ints

        // todo: iterative version
        writeInt(data, bitPosition + bitsRemainingInCurrentByte, remainingBits, x);
    }

    // checks the highest bits of the bytes for equality
    public static boolean equals(byte a, byte b, int bits) {
        final int mask = ~((1 << (Byte.SIZE - bits)) - 1);
        return (a & mask) == (b & mask);
    }


    public static void main(String[] args) {

        System.out.println(Math.ceilLog2(7));
        System.out.println(Math.ceilLog2(8));
        System.out.println(Math.ceilLog2(9));


    }
}
