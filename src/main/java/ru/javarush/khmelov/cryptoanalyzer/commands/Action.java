package ru.javarush.khmelov.cryptoanalyzer.commands;

import ru.javarush.khmelov.cryptoanalyzer.entity.Result;

public interface Action {
    Result execute(String[] parameters);
}
