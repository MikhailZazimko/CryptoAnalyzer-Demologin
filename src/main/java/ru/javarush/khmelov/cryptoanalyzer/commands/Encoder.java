package ru.javarush.khmelov.cryptoanalyzer.commands;

import ru.javarush.khmelov.cryptoanalyzer.constants.Constants;
import ru.javarush.khmelov.cryptoanalyzer.entity.Result;
import ru.javarush.khmelov.cryptoanalyzer.entity.ResultCode;
import ru.javarush.khmelov.cryptoanalyzer.exceptions.AppException;
import ru.javarush.khmelov.cryptoanalyzer.util.PathBuilder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;

public class Encoder implements Action {
    @Override
    public Result execute(String[] parameters) {
        String sourceTextFile = parameters[0];
        String encryptedFile = parameters[1];
        int key = Integer.parseInt(parameters[2]);
        try (
                BufferedReader reader = Files.newBufferedReader(PathBuilder.get(sourceTextFile));
                BufferedWriter writer = Files.newBufferedWriter(PathBuilder.get(encryptedFile))
        ) {
            while (reader.ready()) {
                char character = (char) reader.read();
                character = Character.toLowerCase(character);
                if (Constants.alphabetIndex.containsKey(character)) {
                    Integer index = Constants.alphabetIndex.get(character);
                    index = (index + key) % Constants.ALPHABET.length;
                    writer.write(Constants.ALPHABET[index]);
                }
                if (character == '\n') {
                    writer.write(character);
                }
            }
        } catch (IOException e) {
            throw new AppException(e.getMessage(), e);
        }
        return new Result(ResultCode.OK, "encode complete");
    }
}
