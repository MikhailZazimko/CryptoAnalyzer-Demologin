package ru.javarush.khmelov.cryptoanalyzer.entity;

public class Result {
    public final ResultCode resultCode;
    public final String resultFile;

    public Result(ResultCode resultCode, String resultFile) {
        this.resultCode = resultCode;
        this.resultFile = resultFile;
    }

    @Override
    public String toString() {
        return "Result{" +
                "resultCode=" + resultCode +
                ", message='" + resultFile + '\'' +
                '}';
    }
}
