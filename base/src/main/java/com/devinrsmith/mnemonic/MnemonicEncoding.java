package com.devinrsmith.mnemonic;

public enum MnemonicEncoding {
    //RFC1751("com.devinrsmith.mnemonic.RFC1751"),
    BIP39_EN("com.devinrsmith.mnemonic.Bip39EN", "mnemonic-bip39-en"),
    BIP39_FR("com.devinrsmith.mnemonic.Bip39FR", "mnemonic-bip39-fr"),
    BIP39_IT("com.devinrsmith.mnemonic.Bip39IT", "mnemonic-bip39-it"),
    BIP39_ES("com.devinrsmith.mnemonic.Bip39ES", "mnemonic-bip39-es"),
    PGPBIO("com.devinrsmith.mnemonic.PGPBiometric", "mnemonic-pgp");

    private static final String INSTANCE = "INSTANCE";

    private final String clazz;
    private final String artifact;
    private volatile Encoding instance;

    MnemonicEncoding(String clazz, String artifact) {
        this.clazz = clazz;
        this.artifact = artifact;
    }

    public Encoding instance() {
        if (instance == null) {
            synchronized (this) {
                if (instance == null) {
                    final ClassLoader loader = MnemonicEncoding.class.getClassLoader();
                    final Class loadedClass;
                    try {
                        loadedClass = loader.loadClass(clazz);
                    } catch (ClassNotFoundException e) {
                        final String message = String.format(
                            "Please make sure that the artifact [%s] is available on the java library path",
                            artifact);
                        throw new EncodingNotFoundRuntimeException(e, message);
                    }
                    instance = (Encoding) Enum.valueOf(loadedClass, INSTANCE);
                }
            }
        }
        return instance;
    }
}
