package ru.javarush.khmelov.cryptoanalyzer.controllers;

import ru.javarush.khmelov.cryptoanalyzer.commands.Action;
import ru.javarush.khmelov.cryptoanalyzer.entity.Result;
import ru.javarush.khmelov.cryptoanalyzer.entity.ResultCode;
import ru.javarush.khmelov.cryptoanalyzer.exceptions.AppException;

public class MainController {

    public Result doAction(String actionName, String[] parameters) {
        Action action = ActionContainer.get(actionName);
        try {
            return action.execute(parameters);
        } catch (NumberFormatException | AppException e) {
            return new Result(ResultCode.ERROR, e.getMessage());
        }
    }
}
