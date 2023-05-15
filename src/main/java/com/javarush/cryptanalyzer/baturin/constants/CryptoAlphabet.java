package com.javarush.cryptanalyzer.baturin.constants;

public class CryptoAlphabet {

    private static final String lettersUpperCase = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";

    private static final String lettersLowerCase = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    private static final String numbers = "1234567890";

    private static final String symbols = ".,\"\":-!? ";

    public static final String CAESAR_ALPHABET = lettersUpperCase + lettersLowerCase + numbers + symbols;
    public static final String CAESAR_ALPHABET2 = lettersUpperCase + lettersLowerCase + numbers + symbols;

    public static final String alphabet = CAESAR_ALPHABET + CAESAR_ALPHABET2;
    public String getAlphabet() {
        return alphabet;
    }

    public int getAlphabetLenght() {
        return CAESAR_ALPHABET.length();
    }
}
