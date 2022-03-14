package ru.javarush.khmelov.cryptoanalyzer.commands;

import ru.javarush.khmelov.cryptoanalyzer.entity.Result;
import ru.javarush.khmelov.cryptoanalyzer.entity.ResultCode;

public class BruteForce implements Action{
    @Override
    public Result execute(String[] parameters)  {
        //TODO something do
        return new Result("BruteForce complete", ResultCode.OK);
    }
}
