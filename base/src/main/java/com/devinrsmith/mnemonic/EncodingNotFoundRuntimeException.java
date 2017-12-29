package com.devinrsmith.mnemonic;

public class EncodingNotFoundRuntimeException extends RuntimeException {
    EncodingNotFoundRuntimeException(ClassNotFoundException cause, String message) {
        super(message, cause);
    }

    @Override
    public synchronized ClassNotFoundException getCause() {
        return (ClassNotFoundException)super.getCause();
    }
}
