package ru.javarush.khmelov.cryptoanalyzer.controllers;

import ru.javarush.khmelov.cryptoanalyzer.commands.Action;
import ru.javarush.khmelov.cryptoanalyzer.entity.Result;

public class MainController {

    public Result doAction(String actionName, String[] parameters) {
        //action == encode
        //parameters = [text.txt,encode.txt,12]
        Action action = Actions.find(actionName);
        return action.execute(parameters);
    }
}
