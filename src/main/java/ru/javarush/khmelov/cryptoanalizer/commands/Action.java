package ru.javarush.khmelov.cryptoanalizer.commands;

import ru.javarush.khmelov.cryptoanalizer.entity.Result;

public interface Action {
    Result execute(String[] parameters);
}
