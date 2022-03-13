package ru.javarush.khmelov.cryptoanalyzer.commands;

import ru.javarush.khmelov.cryptoanalyzer.constants.Constants;
import ru.javarush.khmelov.cryptoanalyzer.entity.Result;
import ru.javarush.khmelov.cryptoanalyzer.exceptions.AppException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Encoder implements Action {
    @Override
    public Result execute(String[] parameters) {
        String form = parameters[0];
        String to = parameters[1];
        int key = Integer.parseInt(parameters[2]);
        Map<Character, Integer> alphabetIndex = new HashMap<>();
        char[] chars = Constants.ALPHABET.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            alphabetIndex.put(chars[i], i);
        }
        try (
                BufferedReader reader = Files.newBufferedReader(Path.of(form));
                BufferedWriter writer = Files.newBufferedWriter(Path.of(to));
        ) {
            while (reader.ready()) {
                Character character = (char) reader.read();
                if (alphabetIndex.containsKey(character)) {
                    Integer index = alphabetIndex.get(character);
                    index = (index + key) % chars.length;
                    writer.write(chars[index]);
                }
            }
        } catch (IOException e) {
            throw new AppException(e.getMessage(), e);
        }

        return null;
    }
}
