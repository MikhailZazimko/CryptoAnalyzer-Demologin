package ru.javarush.khmelov.cryptoanalyzer.commands;

import ru.javarush.khmelov.cryptoanalyzer.entity.Result;

public class Decode implements Action {
    @Override
    public Result execute(String[] parameters) {
        String sourceTextFile = parameters[0];
        String encryptedFile = parameters[1];
        int key = Integer.parseInt(parameters[2]);
        return copyWithKey(sourceTextFile, encryptedFile, -1 * key);
    }

}
