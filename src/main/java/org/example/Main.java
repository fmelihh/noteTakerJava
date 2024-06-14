package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PostgresConnector connector = new PostgresConnector();

        Scanner in = new Scanner(System.in);

        System.out.println("Message Title: ");
        String title = in.nextLine();

        System.out.println("Message Body: ");
        String body = in.nextLine();

        connector.saveNotes(title, body);
        System.out.println("Notes saves successfully.");
    }
}