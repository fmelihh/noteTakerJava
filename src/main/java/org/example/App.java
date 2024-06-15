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
    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception {
        PostgresConnector connector = new PostgresConnector();

        Scanner in = new Scanner(System.in);

        System.out.println("Message Title: ");
        String title = in.nextLine();

        System.out.println("Message Body: ");
        String body = in.nextLine();

        connector.saveNotes(title, body);
        System.out.println("Notes saves successfully.");

        return 0;
    }


}
