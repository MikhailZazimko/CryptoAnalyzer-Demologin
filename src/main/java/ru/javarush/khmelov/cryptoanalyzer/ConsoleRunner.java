package ru.javarush.khmelov.cryptoanalyzer;

import ru.javarush.khmelov.cryptoanalyzer.controllers.MainController;
import ru.javarush.khmelov.cryptoanalyzer.view.console.ConsoleApp;
import ru.javarush.khmelov.cryptoanalyzer.view.console.Menu;

import java.util.Scanner;

public class ConsoleRunner {

    public static void main(String[] args) {
        //build console app
        Scanner input = new Scanner(System.in);
        Menu menu = new Menu(input);
        MainController mainController = new MainController();
        ConsoleApp application = new ConsoleApp(mainController, menu);
        //run console app
        application.run(args);
    }


}

