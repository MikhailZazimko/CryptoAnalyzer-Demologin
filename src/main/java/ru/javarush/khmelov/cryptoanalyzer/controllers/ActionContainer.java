package ru.javarush.khmelov.cryptoanalyzer.controllers;

import ru.javarush.khmelov.cryptoanalyzer.commands.*;
import ru.javarush.khmelov.cryptoanalyzer.constants.Const;
import ru.javarush.khmelov.cryptoanalyzer.exceptions.AppException;

@SuppressWarnings("unused")
public enum ActionContainer {

    ENCODE(new Encode()),
    DECODE(new Decode()),
    BRUTEFORCE(new BruteForce()),
    ANALYZE(new Analyze()),
    EXIT(new Exit());

    private final Action action;

    ActionContainer(Action action) {
        this.action = action;
    }

    public static Action get(String actionName) {
        try {
            ActionContainer value = ActionContainer.valueOf(actionName.toUpperCase());
            return value.action;
        } catch (IllegalArgumentException e) {
            String message = String.format(Const.NOT_FOUND_ACTION_FORMAT, actionName);
            throw new AppException(message, e);
        }
    }
}
