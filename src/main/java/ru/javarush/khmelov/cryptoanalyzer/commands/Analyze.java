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
import java.util.*;
import java.util.stream.Collectors;

public class Analyze implements Action {
    @Override
    public Result execute(String[] parameters) {
        String encryptedFile = parameters[0];
        String dict = parameters[1];
        String analyzed = parameters[2];

        List<Character> dictCharacters = getIntegerMap(dict);
        List<Character> encryptedCharacters = getIntegerMap(encryptedFile);

        try (
                BufferedReader reader = Files.newBufferedReader(PathBuilder.get(encryptedFile));
                BufferedWriter writer = Files.newBufferedWriter(PathBuilder.get(analyzed))
        ) {
            while (reader.ready()) {
                char character = (char) reader.read();
                int index = encryptedCharacters.indexOf(character);
                if (index >= 0) {
                    writer.write(dictCharacters.get(index));
                } else if (character == '\n') {
                    writer.write(character);
                }
            }
        } catch (IOException e) {
            throw new AppException(e.getMessage(), e);
        }
        return new Result(ResultCode.OK, analyzed);
    }

    private List<Character> getIntegerMap(String encryptedFile) {
        Map<Character, Integer> map = Constants
                .alphabetIndex
                .keySet()
                .stream()
                .collect(Collectors
                        .toMap(character -> character, character -> 0, (a, b) -> b, LinkedHashMap::new)
                );
        try (
                BufferedReader reader = Files.newBufferedReader(PathBuilder.get(encryptedFile))
        ) {
            while (reader.ready()) {
                char character = (char) reader.read();
                character = Character.toLowerCase(character);
                if (map.containsKey(character)) {
                    Integer i = map.get(character);
                    map.put(character, ++i);
                }
            }
        } catch (IOException e) {
            throw new AppException(e.getMessage(), e);
        }
        return Collections.unmodifiableList(map.entrySet()
                .stream()
                .sorted(Comparator.comparingInt(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .toList());

    }

}
