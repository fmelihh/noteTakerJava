package org.example;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;

public class PostgresConnector {
    Connection c = null;

    public PostgresConnector() {
        try {
            Class.forName("org.postgresql.Driver");
            this.c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/testdb", "postgres", "postgres");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("Database connection established.");
        migrateDatabase();
    }

    public void migrateDatabase() {
        Statement stmt;
        try {
            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS NOTES " +
                    "(ID SERIAL PRIMARY KEY NOT NULL, " +
                    "NOTE_NAME TEXT NOT NULL, " +
                    "NOTE TEXT NOT NULL)";
            stmt.execute(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("Migration completed.");
    }

    public void saveNotes(String title, String body) {
        Statement stmt;
        try {
            stmt = c.createStatement();
            String sql = "INSERT INTO NOTES (NOTE_NAME, NOTE) " +
                    "VALUES ('" + title + "', '" + body + "')";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
             e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
    }
}
