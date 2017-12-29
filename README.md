### Human Encoding

The goal of this project is to provide a variety of encoding and decoding functionalities for
reliably storing or transmitting small amounts of data in a human readable, writable, speakable,
and listenable way. There is no single best encoding scheme for all purposes, so advice and best
practices around scheme use cases will be provided.

The encoding is represented as an ordered list of words, typically called a mnemonic phrase.
"Mnemonic" is a bit of a misnomer, as they are used much more often written down than memorized.
The words come from a wordlist which typically has a length that is power of 2.

This project does not deal with the use of mnemonic phrases for the purpose of further seed
generation, although such projects could benefit from the use of this library.

This project has no external dependencies.

#### Use cases

 * Paper backup for seed data
 * Paper backup for private keys
 * Relaying small amounts of data over a secure text channel
 * Relaying small amounts of data over a secure voice channel

#### Research TODOs
 
 * Wordlists that are built specifically to minimize phonetic ambiguities
 * Wordlists that have stronger guarantees (ability to automatically deal with mistyped characters)
 * Best practices around creating and storing paper backups (archival paper, pencil vs pen, polyester sleeves, etc)
 
#### References

 * [RFC1751](https://tools.ietf.org/html/rfc1751)
 * [Mnemonic Phrase](https://en.bitcoin.it/wiki/Mnemonic_phrase)
 * [BIP-0039](https://github.com/bitcoin/bips/blob/master/bip-0039.mediawiki)
 * [Trezor Mnemonics](https://github.com/trezor/python-mnemonic)
 * [Phonetic Alphabet](https://en.wikipedia.org/wiki/NATO_phonetic_alphabet)
 * [PGP word list](https://en.wikipedia.org/wiki/PGP_word_list)
 * [singpolyma/mnemonicode](https://github.com/singpolyma/mnemonicode)
 * [RFC1760](https://tools.ietf.org/html/rfc1760)
 * [RFC2289](https://tools.ietf.org/html/rfc2289)
 
#### Build status

 * Master: [![Build Status](https://travis-ci.org/devinrsmith/mnemonic-encoding.svg?branch=master)](https://travis-ci.org/devinrsmith/mnemonic-encoding)