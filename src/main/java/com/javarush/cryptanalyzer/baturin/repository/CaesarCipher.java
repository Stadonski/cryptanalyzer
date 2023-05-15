package com.javarush.cryptanalyzer.baturin.repository;


import com.javarush.cryptanalyzer.baturin.constants.CryptoAlphabet;

public class CaesarCipher {
    private static CryptoAlphabet _alphabet = new CryptoAlphabet();

    private static final String alphabet = _alphabet.getAlphabet();

    public String encrypt(String message, int key) {
        StringBuilder result = new StringBuilder();

        for (char _char : message.toCharArray()) {
            int indexAlphabet = alphabet.indexOf(_char);

            int AlphabetPos;
            char newChar = 0;

            if (indexAlphabet > 0) {
                if (key >= 0) {
                    AlphabetPos = (indexAlphabet + key) % (alphabet.length() / 2);
                } else {
                    int _key = key % (alphabet.length() / 2);
                    AlphabetPos = (indexAlphabet + (alphabet.length() / 2) + _key) % alphabet.length();
                }
                newChar = alphabet.charAt(AlphabetPos);
            }
            result.append(newChar);
        }
        return result.toString();
    }

    public String decode(String message, int key) {
        return encrypt(message, key * -1);
    }

}
