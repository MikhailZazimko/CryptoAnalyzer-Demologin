package ru.javarush.khmelov.cryptoanalyzer.commands;

import ru.javarush.khmelov.cryptoanalyzer.constants.Constants;
import ru.javarush.khmelov.cryptoanalyzer.controllers.ActionContainer;
import ru.javarush.khmelov.cryptoanalyzer.entity.Result;
import ru.javarush.khmelov.cryptoanalyzer.entity.ResultCode;
import ru.javarush.khmelov.cryptoanalyzer.exceptions.AppException;
import ru.javarush.khmelov.cryptoanalyzer.util.PathBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;

public class BruteForce implements Action {
    @Override
    public Result execute(String[] parameters) {
        String encryptedFile = parameters[0];
        int bestKey = 0;
        int bestSpaceCount = 0;
        for (int key = 0; key < Constants.ALPHABET.length; key++) {
            int spaceCount = 0;
            try (
                    BufferedReader reader = Files.newBufferedReader(PathBuilder.get(encryptedFile));
            ) {
                while (reader.ready()) {
                    Character character = (char) reader.read();
                    if (Constants.alphabetIndex.containsKey(character)) {
                        Integer index = Constants.alphabetIndex.get(character);
                        index = (index - key + Constants.ALPHABET.length) % Constants.ALPHABET.length;
                        if (Constants.ALPHABET[index] == ' ') {
                            spaceCount++;
                        }
                    }
                }
            } catch (IOException e) {
                throw new AppException(e.getMessage(), e);
            }
            if (spaceCount > bestSpaceCount) {
                bestSpaceCount = spaceCount;
                bestKey = key;
            }
        }
        parameters = new String[]{parameters[0], parameters[1], Integer.toString(bestKey)};
        ActionContainer.get("decode").execute(parameters);
        System.out.println(bestSpaceCount);
        return new Result(ResultCode.OK, "bruteforce complete");

    }
}
