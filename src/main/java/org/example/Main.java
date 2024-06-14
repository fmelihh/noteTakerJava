package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PostgresConnector connector = new PostgresConnector();

        Scanner in = new Scanner(System.in);
        String message = in.nextLine();
        System.out.println("Message is " + message);
    }
}