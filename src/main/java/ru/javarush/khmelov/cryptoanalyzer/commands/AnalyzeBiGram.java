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

public class AnalyzeBiGram implements Action {

    private char prev = '~';

    @Override
    public Result execute(String[] parameters) {
        String encryptedFile = parameters[0];
        String dict = parameters[1];
        String analyzed = parameters[2];
        List<Letter> dictCharacters = getIntegerMap(dict);
        List<Letter> encryptedCharacters = getIntegerMap(encryptedFile);
        resort(dictCharacters, encryptedCharacters);
        try (
                BufferedReader reader = Files.newBufferedReader(PathBuilder.get(encryptedFile));
                BufferedWriter writer = Files.newBufferedWriter(PathBuilder.get(analyzed))
        ) {
            while (reader.ready()) {
                char character = (char) reader.read();
                int index = findIndex(encryptedCharacters, character);
                if (index >= 0) {
                    writer.write(dictCharacters.get(index).character);
                } else if (character == '\n') {
                    writer.write(character);
                }
            }
        } catch (IOException e) {
            throw new AppException(e.getMessage(), e);
        }
        return new Result(ResultCode.OK, analyzed);
    }

    private void resort(List<Letter> dict, List<Letter> enc) {
        int delta = 2;
        for (int i = 0; i < dict.size(); i++) {
            double d = Double.MAX_VALUE;
            int best = Integer.MIN_VALUE;
            for (int j = Math.max(0, i - delta); j < Math.min(enc.size(), i + delta); j++) {
                double d2 = getDistance(dict.get(i), enc.get(j));
                if (d2 < d) {
                    d = d2;
                    best = j;
                }
            }
            if (best != i) {
                Letter letter = enc.get(i);
                enc.set(i, enc.get(best));
                enc.set(best, letter);
            }
        }
    }

    private double getDistance(Letter d, Letter e) {

        return 0;
    }

    private int findIndex(List<Letter> encryptedCharacters, char character) {
        for (int i = 0; i < encryptedCharacters.size(); i++) {
            Letter letter = encryptedCharacters.get(i);
            if (letter.character == character) {
                return i;
            }
        }
        throw new AppException("not found character " + character);
    }


    private List<Letter> getIntegerMap(String encryptedFile) {
        Map<Character, Letter> map = Constants
                .alphabetIndex
                .keySet()
                .stream()
                .collect(Collectors
                        .toMap(character -> character,
                                Letter::new,
                                (key, value) -> value,
                                LinkedHashMap::new
                        )
                );

        try (
                BufferedReader reader = Files.newBufferedReader(PathBuilder.get(encryptedFile))
        ) {
            while (reader.ready()) {
                char character = (char) reader.read();
                character = Character.toLowerCase(character);
                if (map.containsKey(character)) {
                    Letter letter = map.get(character);
                    letter.count++;
                    Map<Character, Integer> next = letter.next;
                    if (next.containsKey(prev)) {
                        next.put(prev, next.get(prev) + 1);
                    } else {
                        next.put(prev, 0);
                    }
                    prev = character;
                }
            }
        } catch (IOException e) {
            throw new AppException(e.getMessage(), e);
        }
        return (map.entrySet()
                .stream()
                .sorted(Comparator.comparingInt(p -> p.getValue().count))
                .map(Map.Entry::getValue)
                .toList());
    }

    private static class Letter {

        final Character character;
        int count = 0;
        Map<Character, Integer> next = new HashMap<>();

        public Letter(Character character) {
            this.character = character;
        }
    }
}
