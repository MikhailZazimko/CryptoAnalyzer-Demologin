package ru.javarush.khmelov.cryptoanalyzer.view.console;

import ru.javarush.khmelov.cryptoanalyzer.constants.Const;

interface Messages {
    String[][][] QUESTIONS = new String[][][]{
            {
                    {Const.ENCODE},
                    {"Enter source (full path OR only filename OR Enter for text.txt) :", "text.txt"},
                    {"Enter destination (full path OR only filename OR Enter for encrypted.txt) :", "encrypted.txt"},
                    {"Enter key (int number OR Enter for key=1) :", "1"},
            },
            {
                    {Const.DECODE},
                    {"Enter source (full path OR only filename OR Enter for encrypted.txt) :", "encrypted.txt"},
                    {"Enter destination (full path OR only filename OR Enter for decrypted.txt) :", "decrypted.txt"},
                    {"Enter key (int number OR Enter for key=1) :", "1"},
            },
            {
                    {Const.BRUTEFORCE},
                    {"Enter source (full path OR only filename OR Enter for encrypted.txt) :", "encrypted.txt"},
                    {"Enter destination (full path OR only filename OR Enter for bruteforce.txt) :", "bruteforce.txt"},
            },
            {
                    {Const.ANALYZE},
                    {"Enter source (full path OR only filename OR Enter for encrypted.txt) :", "encrypted.txt"},
                    {"Enter dictionary  (full path OR only filename OR Enter for dict.txt) :", "dict.txt"},
                    {"Enter destination (full path OR only filename OR Enter for analyzed.txt) :", "analyzed.txt"},
            },
    };

    String MESSAGE_SELECT_MODE = """
            Please select mode:
            1. Encrypt
            2. Decrypt
            3. Brute force
            4. Analyze
            """;
    
    String INCORRECT_SELECTION = "Incorrect selection";
}
