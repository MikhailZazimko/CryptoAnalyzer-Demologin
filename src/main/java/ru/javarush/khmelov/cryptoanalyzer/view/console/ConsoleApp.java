package ru.javarush.khmelov.cryptoanalyzer.view.console;

import ru.javarush.khmelov.cryptoanalyzer.controllers.MainController;
import ru.javarush.khmelov.cryptoanalyzer.entity.Result;
import ru.javarush.khmelov.cryptoanalyzer.entity.ResultCode;

import java.util.Arrays;

@SuppressWarnings("ClassCanBeRecord")
public class ConsoleApp {

    private final MainController mainController;
    private final Menu menu;

    public ConsoleApp(MainController mainController, Menu menu) {
        this.mainController = mainController;
        this.menu = menu;
    }

    public void run(String[] args) {
        //Life cycle app
        Result result;
        do {
            if (args.length == 0) {
                args = menu.getArgs();
            }
            //work phase
            result = getResult(args);
            print(result);
            //next request
            args = new String[0];
        } while (result.resultCode == ResultCode.ERROR);
    }


    private void print(Result result) {
        String message = switch (result.resultCode) {
            case OK -> String.format("""
                        Operation complete
                        Result file: %s
                    """, result.resultFile);
            case ERROR -> String.format("""
                        ERROR
                        Result file: %s
                    """, result.resultFile);
        };
        System.out.println(message);
    }

    private Result getResult(String[] args) {
        String action = args[0];
        String[] parameters = Arrays.copyOfRange(args, 1, args.length);
        return mainController.doAction(action, parameters);
    }
}
