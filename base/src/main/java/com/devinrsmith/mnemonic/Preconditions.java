package com.devinrsmith.mnemonic;

import java.util.function.Supplier;

class Preconditions {
    static void checkArgument(boolean expression) {
        if (!expression) {
            throw new IllegalArgumentException();
        }
    }

    static void checkArgument(boolean expression, String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }

    static void checkArgument(boolean expression, Supplier<String> message) {
        if (!expression) {
            throw new IllegalArgumentException(message.get());
        }
    }

    static void checkState(boolean expression) {
        if (!expression) {
            throw new IllegalStateException();
        }
    }

    static void checkState(boolean expression, String message) {
        if (!expression) {
            throw new IllegalStateException(message);
        }
    }

    static void checkState(boolean expression, Supplier<String> message) {
        if (!expression) {
            throw new IllegalStateException(message.get());
        }
    }
}
