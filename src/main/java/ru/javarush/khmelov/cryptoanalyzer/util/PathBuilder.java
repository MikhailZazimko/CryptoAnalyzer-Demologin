package ru.javarush.khmelov.cryptoanalyzer.util;

import ru.javarush.khmelov.cryptoanalyzer.constants.Constants;

import java.nio.file.Path;

public class PathBuilder {
    private PathBuilder() {
    }

    public static Path get(String filename) {
        Path path = Path.of(filename);
        return path.isAbsolute()
                ? path
                : Path.of(Constants.TXT_FOLDER + filename);
    }
}

