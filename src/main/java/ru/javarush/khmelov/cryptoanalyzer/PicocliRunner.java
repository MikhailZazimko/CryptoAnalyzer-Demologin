package ru.javarush.khmelov.cryptoanalyzer;

import picocli.CommandLine;
import picocli.CommandLine.*;
import picocli.CommandLine.Model.CommandSpec;
import ru.javarush.khmelov.cryptoanalyzer.entity.Result;

@Command(name = "cypher", subcommands = {CommandLine.HelpCommand.class},
        description = "Caesar cypher command")
public class PicocliRunner implements Runnable {

    private final Application application = new Application();

    @Spec
    CommandSpec spec;

    @Command(name = "encode", description = "Encrypt from file to file using key")
    void encode(
            @Parameters(paramLabel = "<source file>", description = "source file with text to encrypt") String src,
            @Parameters(paramLabel = "<dest file>", description = "dest file which should have encrypted text") String dest,
            @Parameters(paramLabel = "<key>", description = "key for encryption") String key) {
        String[] args = {"encode", src, dest, key};
        Result result = application.run(args);
        System.out.println(result);
    }

    @Command(name = "bruteforce", description = "Decrypt from file to file using brute force")
        // |3|
    void bruteForce(
            @Parameters(paramLabel = "<source file>", description = "source file with encrypted text") String src,
            @Option(names = {"-r", "--representative"}, description = "file with unencrypted representative text") String representativeFile,
            @Parameters(paramLabel = "<dest file>", description = "dest file which should have decrypted text") String dest) {
        String[] args = {"bruteforce", src, dest};
        Result result = application.run(args);
        System.out.println(result);
    }

    @Command(name = "analyze", description = "Decrypt from file to file using statistical analysis")
        // |3|
    void analyze(
            @Parameters(paramLabel = "<source file>", description = "source file with encrypted text") String src,
            @Parameters(paramLabel = "<dict file>", description = "source file with encrypted text") String dict,
            @Parameters(paramLabel = "<dest file>", description = "dest file which should have decrypted text") String dest) {
        String[] args = {"analyze", src, dict, dest};
        Result result = application.run(args);
        System.out.println(result);
    }


    @Command(name = "decode", description = "Decrypt from file to file using statistical analysis")
        // |3|
    void decode(
            @Parameters(paramLabel = "<source file>", description = "source file with encrypted text") String src,
            @Parameters(paramLabel = "<dest file>", description = "dest file which should have decrypted text") String dest,
            @Parameters(paramLabel = "<key>", description = "key for encryption") String key) {
        String[] args = {"decode", src, dest, key};
        Result result = application.run(args);
        System.out.println(result);
    }

    @Override
    public void run() {
        throw new ParameterException(spec.commandLine(), "Specify a subcommand");
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new PicocliRunner()).execute(args);
        System.exit(exitCode);
    }
}