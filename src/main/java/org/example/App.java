package org.example;

import picocli.CommandLine;

import java.util.Scanner;
import java.util.concurrent.Callable;

@CommandLine.Command(
    name = "My cli app",
    description = "Note Taker App",
    version = "noteTakerApp 0.1.0",
    mixinStandardHelpOptions = true
)
public class App implements Callable<Integer> {
    @CommandLine.Parameters(index = "0", description = "The parameter note urgency.")
    String urgency;

    @CommandLine.Parameters(index = "1", description = "The note title")
    String title;

    @CommandLine.Parameters(index = "2", description = "The note body")
    String body;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception {
        PostgresConnector connector = new PostgresConnector();
        connector.saveNotes(title, body);
        System.out.println("Notes saves successfully.");

        return 0;
    }

}
