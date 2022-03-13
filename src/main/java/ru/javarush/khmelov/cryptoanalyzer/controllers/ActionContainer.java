package ru.javarush.khmelov.cryptoanalyzer.controllers;

import ru.javarush.khmelov.cryptoanalyzer.commands.Action;
import ru.javarush.khmelov.cryptoanalyzer.commands.Decoder;
import ru.javarush.khmelov.cryptoanalyzer.commands.Encoder;
import ru.javarush.khmelov.cryptoanalyzer.exceptions.AppException;

@SuppressWarnings("unused")
public enum ActionContainer {

    DECODER(new Decoder()),
    ENCODER(new Encoder());

    private final Action action;

    ActionContainer(Action action) {
        this.action = action;
    }

    public static Action get(String actionName) {
        try {
            ActionContainer value = ActionContainer.valueOf(actionName);
            return value.action;
        } catch (IllegalArgumentException e) {
            throw new AppException("not found action: " + actionName, e);
        }
    }
}
