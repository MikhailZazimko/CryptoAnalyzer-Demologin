package ru.javarush.khmelov.cryptoanalyzer.commands;

import ru.javarush.khmelov.cryptoanalyzer.constants.Alphabet;
import ru.javarush.khmelov.cryptoanalyzer.constants.Const;
import ru.javarush.khmelov.cryptoanalyzer.entity.Result;
import ru.javarush.khmelov.cryptoanalyzer.entity.ResultCode;
import ru.javarush.khmelov.cryptoanalyzer.exceptions.AppException;
import ru.javarush.khmelov.cryptoanalyzer.util.PathBuilder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public interface Action {

    Result execute(String[] parameters);

    //common methods for actions: Encode Decode Bruteforce
    default Result copyWithKey(String sourceTextFile, String encryptedFile, int key) {
        Path source = PathBuilder.get(sourceTextFile);
        Path target = PathBuilder.get(encryptedFile);
        try (
                BufferedReader reader = Files.newBufferedReader(source);
                BufferedWriter writer = Files.newBufferedWriter(target)
        ) {
            int value;
            int length = Alphabet.CHARS.length;
            while ((value = reader.read()) > -1) {
                char character = (char) value;
                character = Character.toLowerCase(character);
                if (Alphabet.index.containsKey(character)) {
                    Integer index = Alphabet.index.get(character);
                    index = (index + key + Math.abs(key) * length) % length;
                    writer.write(Alphabet.CHARS[index]);
                } else if (character == '\n') {
                    writer.write(character);
                }
            }
        } catch (IOException e) {
            throw new AppException(Const.INCORRECT_FILE + e.getMessage(), e);
        }
        return new Result(ResultCode.OK, encryptedFile);
    }
}
