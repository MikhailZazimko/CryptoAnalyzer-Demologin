package ru.javarush.khmelov.cryptoanalyzer.constants;

import java.io.File;

public class Constants {
    private static final String rus = "ЙЦУКЕНГШЩЗХЪЭЖДЛОРПАВЫФЯЧСМИТЬБЮ";
    private static final String eng = "QWERTYUIOPLKJHGFDSAZXCVBNM";
    private static final String cypher = "0123456789";
    private static final String symbols = "!@#$%^&*()[]{}?.,;:'\"";

    public static final String ALPHABET = rus + eng + rus.toLowerCase() + eng.toLowerCase() + cypher + symbols;
    public static final String TXT_FOLDER=System.getProperty("user.dir")+
            File.separator+
            "text"+
            File.separator;
}
