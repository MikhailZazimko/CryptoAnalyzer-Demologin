package ru.javarush.khmelov.cryptoanalyzer.constants;

import java.io.File;

public interface Const {
    String ENCODE = "encode";
    String DECODE = "decode";
    String BRUTEFORCE = "bruteforce";
    String ANALYZE = "analyze";
    String TXT_FOLDER = System.getProperty("user.dir") +
            File.separator +
            "text" +
            File.separator;
}