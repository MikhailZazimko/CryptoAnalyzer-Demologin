package ru.javarush.khmelov.cryptoanalyzer.command;

import ru.javarush.khmelov.cryptoanalyzer.constant.Alphabet;
import ru.javarush.khmelov.cryptoanalyzer.constant.Const;
import ru.javarush.khmelov.cryptoanalyzer.entity.Result;
import ru.javarush.khmelov.cryptoanalyzer.exception.AppException;
import ru.javarush.khmelov.cryptoanalyzer.util.PathBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class BruteForce implements Action {

    @Override
    public Result execute(String[] parameters) {
        String encryptedFilename = parameters[0];
        String decryptedFilename = parameters[1];
        int bestKey = 0;
        int bestSpaceCount = 0;
        char space = ' ';
        for (int key = 0; key < Alphabet.CHARS.length; key++) {
            int spaceCount = countCharInFileWithKey(encryptedFilename, key, space);
            if (spaceCount > bestSpaceCount) {
                bestSpaceCount = spaceCount;
                bestKey = key;
            }
        }
        return copyWithKey(encryptedFilename, decryptedFilename, bestKey);
    }

    private int countCharInFileWithKey(String encryptedFilename, int key, char fixChar) {
        int spaceCount = 0;
        Path path = PathBuilder.get(encryptedFilename);
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            int value;
            while ((value = reader.read()) > -1) {
                char character = (char) value;
                if (Alphabet.index.containsKey(character)) {
                    int index = Alphabet.index.get(character);
                    index = (index + key + Alphabet.CHARS.length) % Alphabet.CHARS.length;
                    if (Alphabet.CHARS[index] == fixChar) {
                        spaceCount++;
                    }
                }
            }
        } catch (IOException e) {
            throw new AppException(Const.INCORRECT_FILE + encryptedFilename, e);
        }
        return spaceCount;
    }
}
