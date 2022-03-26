package ru.javarush.khmelov.cryptoanalyzer.commands;

import ru.javarush.khmelov.cryptoanalyzer.constants.Alphabet;
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
        String encryptedFilename = parameters[0];
        String dictionaryFilename = parameters[1];
        String analyzedFilename = parameters[2];

        List<Character> dictCharacters = getIntegerMap(dictionaryFilename);
        List<Character> encryptedCharacters = getIntegerMap(encryptedFilename);

        try (
                BufferedReader reader = Files.newBufferedReader(PathBuilder.get(encryptedFilename));
                BufferedWriter writer = Files.newBufferedWriter(PathBuilder.get(analyzedFilename))
        ) {
            int value;
            while ((value = reader.read()) > -1) {
                char character = (char) value;
                int index = encryptedCharacters.indexOf(character);
                Character characterDecrypted = dictCharacters.get(index);
                writer.write(
                        characterDecrypted != null
                                ? characterDecrypted
                                : character);
            }
        } catch (IOException e) {
            throw new AppException(e.getMessage(), e);
        }
        return new Result(ResultCode.OK, analyzedFilename);
    }

    private List<Character> getIntegerMap(String encryptedFile) {
        Map<Character, Integer> map = createStartMap();
        try (
                BufferedReader reader = Files.newBufferedReader(PathBuilder.get(encryptedFile))
        ) {
            int value;
            while ((value = reader.read()) > -1) {
                char character = (char) value;
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

    private Map<Character, Integer> createStartMap() {
        return Alphabet
                .index
                .keySet()
                .stream()
                .collect(Collectors
                        .toMap(
                                character -> character,
                                character -> 0, (a, b) -> b,
                                LinkedHashMap::new
                        )
                );
    }

}
