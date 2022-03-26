package ru.javarush.khmelov.cryptoanalyzer.command;

import ru.javarush.khmelov.cryptoanalyzer.constant.Const;
import ru.javarush.khmelov.cryptoanalyzer.entity.Result;
import ru.javarush.khmelov.cryptoanalyzer.entity.ResultCode;

public class Exit implements Action {

    @Override
    public Result execute(String[] parameters) {
        return new Result(ResultCode.OK, Const.APPLICATION_CLOSED);
    }
}
