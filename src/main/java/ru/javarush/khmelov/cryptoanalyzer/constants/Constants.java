package ru.javarush.khmelov.cryptoanalyzer.constants;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Constants {
    private static final String rus = "ЙЦУКЕНГШЩЗХЪЭЖДЛОРПАВЫФЯЧСМИТЬБЮ";
    private static final String eng = "QWERTYUIOPLKJHGFDSAZXCVBNM";
    private static final String numbers = "0123456789";
    //private static final String symbols = " -!@#$%^&*()[]{}?.,;:'\"";
    private static final String symbols = "☮.,”’:-!? ";

    public static final char[] ALPHABET = (rus.toLowerCase() + symbols)
            .toCharArray();
    public static final String TXT_FOLDER = System.getProperty("user.dir") +
            File.separator +
            "text" +
            File.separator;
    public static Map<Character, Integer> alphabetIndex = new HashMap<>();

    static {
        for (int i = 0; i < ALPHABET.length; i++) {
            alphabetIndex.put(ALPHABET[i], i);
        }
    }
}
