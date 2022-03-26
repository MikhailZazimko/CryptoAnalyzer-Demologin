package ru.javarush.khmelov.cryptoanalyzer.view.picocli;

import ru.javarush.khmelov.cryptoanalyzer.controllers.MainController;
import ru.javarush.khmelov.cryptoanalyzer.entity.Result;
import ru.javarush.khmelov.cryptoanalyzer.exceptions.AppException;

import java.util.Arrays;

@SuppressWarnings("ClassCanBeRecord")
public class PicocliApp {

    private final MainController mainController;

    public PicocliApp(MainController mainController) {
        this.mainController = mainController;
    }

    public Result run(String[] args) {
        if (args.length > 0) {
            String action = args[0];
            String[] parameters = Arrays.copyOfRange(args, 1, args.length);
            return mainController.doAction(action, parameters);
        } else
            throw new AppException("no args");
    }
}
