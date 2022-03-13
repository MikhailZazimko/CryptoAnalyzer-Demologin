package ru.javarush.khmelov.cryptoanalyzer.controllers;

import ru.javarush.khmelov.cryptoanalyzer.commands.Action;
import ru.javarush.khmelov.cryptoanalyzer.entity.Result;

public class MainController {

    public Result doAction(String actionName, String[] parameters) {
        Action action = ActionContainer.get(actionName);
        return action.execute(parameters);
    }
}
