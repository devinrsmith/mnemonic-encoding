package com.devinrsmith.mnemonic;

import java.io.IOException;

public class WordListRuntimeException extends RuntimeException {
    WordListRuntimeException(IOException cause) {
        super(cause);
    }

    @Override
    public synchronized IOException getCause() {
        return (IOException)super.getCause();
    }
}
