package ru.javarush.khmelov.cryptoanalyzer.commands;

import ru.javarush.khmelov.cryptoanalyzer.entity.Result;
import ru.javarush.khmelov.cryptoanalyzer.entity.ResultCode;

public class BrudeForce implements Action{
    @Override
    public Result execute(String[] parameters)  {
        //TODO something do
        return new Result("BrudeForce complete", ResultCode.OK);
    }
}
