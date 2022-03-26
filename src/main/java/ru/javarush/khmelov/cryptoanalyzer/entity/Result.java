package ru.javarush.khmelov.cryptoanalyzer.entity;

public class Result {
    public final ResultCode resultCode;
    public final String resultFile;
    private String errorMessage;

    public Result(ResultCode resultCode, String resultFile) {
        this.resultCode = resultCode;
        this.resultFile = resultFile;
    }


    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "Result{" +
                "resultCode=" + resultCode +
                ", resultFile='" + resultFile + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
