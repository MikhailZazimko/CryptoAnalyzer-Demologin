package ru.javarush.khmelov.cryptoanalyzer;

import ru.javarush.khmelov.cryptoanalyzer.controllers.MainController;
import ru.javarush.khmelov.cryptoanalyzer.view.MainForm;

public class SwingRunner {
    public static void main(String[] args) {
        MainController controller = new MainController();
        MainForm mainForm = new MainForm(controller);
    }
}
