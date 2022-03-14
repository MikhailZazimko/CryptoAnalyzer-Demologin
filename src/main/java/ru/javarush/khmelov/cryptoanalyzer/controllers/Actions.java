package ru.javarush.khmelov.cryptoanalyzer.controllers;

import ru.javarush.khmelov.cryptoanalyzer.commands.Action;
import ru.javarush.khmelov.cryptoanalyzer.commands.BruteForce;
import ru.javarush.khmelov.cryptoanalyzer.commands.Decoder;
import ru.javarush.khmelov.cryptoanalyzer.commands.Encoder;
import ru.javarush.khmelov.cryptoanalyzer.exceptions.AppException;

public enum Actions {
    ENCODE(new Encoder()),
    DECODE(new Decoder()),
    BRUTEFORCE(new BruteForce());

    private final Action action;

    Actions(Action action) {
        this.action = action;
    }

    public static Action find(String actionName) {
        try {
            Actions value = Actions.valueOf(actionName.toUpperCase());
            return value.action;
        } catch (IllegalArgumentException e) {
            throw new AppException("not found " + actionName, e);
        }
    }
}
