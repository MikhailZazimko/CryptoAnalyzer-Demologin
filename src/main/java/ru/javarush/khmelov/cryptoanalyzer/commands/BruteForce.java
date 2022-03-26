package ru.javarush.khmelov.cryptoanalyzer.commands;

import ru.javarush.khmelov.cryptoanalyzer.constants.Alphabet;
import ru.javarush.khmelov.cryptoanalyzer.entity.Result;
import ru.javarush.khmelov.cryptoanalyzer.exceptions.AppException;
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
        for (int key = 0; key < Alphabet.CHARS.length; key++) {
            Path path = PathBuilder.get(encryptedFilename);
            int spaceCount = getSpaceCount(encryptedFilename, key, path);
            if (spaceCount > bestSpaceCount) {
                bestSpaceCount = spaceCount;
                bestKey = key;
            }
        }
        return copyWithKey(encryptedFilename, decryptedFilename, bestKey);
    }

    private int getSpaceCount(String encryptedFilename, int key, Path path) {
        int spaceCount = 0;
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            int value;
            while ((value = reader.read()) > -1) {
                char character = (char) value;
                if (Alphabet.index.containsKey(character)) {
                    Integer index = Alphabet.index.get(character);
                    index = (index - key + Alphabet.CHARS.length) % Alphabet.CHARS.length;
                    if (Alphabet.CHARS[index] == ' ') {
                        spaceCount++;
                    }
                }
            }
        } catch (IOException e) {
            throw new AppException("can't read file:" + encryptedFilename, e);
        }
        return spaceCount;
    }
}
