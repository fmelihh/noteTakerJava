package org.example;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;

public class PostgresConnector {
    Connection c = null;
    String username = null;
    String password = null;
    String connectionString = null;

    static final String PostgresClassName = "org.postgresql.Driver";

    public PostgresConnector() {
        this.username = "postgres";
        this.password = "postgres";
        this.connectionString = "jdbc:postgresql://localhost:5432/testdb";
        migrateDatabase();
    }

    public Connection getConnection() {
        try {
            Class.forName(PostgresClassName);
            c = DriverManager.getConnection(connectionString, username, password);
        } catch (Exception e) {
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("Database connection established.");
        return c;
    }

    public void migrateDatabase() {
        Statement stmt;
        Connection con = getConnection();
        try {
            stmt = con.createStatement();
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
        } finally {
            try { con.close(); } catch (Exception e ) {
                System.err.println(e.getClass().getName()+": "+e.getMessage());
                System.exit(0);
            }
        }
        System.out.println("Migration completed.");
    }

    public void saveNotes(String title, String body) {
        Statement stmt;
        Connection con = getConnection();
        try {
            stmt = con.createStatement();
            String sql = "INSERT INTO NOTES (NOTE_NAME, NOTE) " +
                    "VALUES ('" + title + "', '" + body + "')";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
             e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        finally {
            try { con.close(); } catch (Exception e ) {
                System.err.println(e.getClass().getName()+": "+e.getMessage());
                System.exit(0);
            }
        }
    }
}
