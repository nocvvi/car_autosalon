package org.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SQLiteDatabase {
    private static final String DATABASE_URL = "jdbc:sqlite:salon.db";
    private Connection connection;
    private static final Logger logger = LoggerFactory.getLogger(SQLiteDatabase.class);

    public SQLiteDatabase() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(DATABASE_URL);
            createTables();
        } catch (SQLException e) {
            logger.error("Failed to establish a database connection", e);
        } catch (ClassNotFoundException e) {
            logger.error("SQLite JDBC driver not found", e);
        }
    }

    public void createTables() {
        try {
            String createCarsTable = "CREATE TABLE IF NOT EXISTS cars (" +
                    "car_id INTEGER PRIMARY KEY, " +
                    "brand TEXT, " +
                    "model TEXT, " +
                    "type TEXT, " +
                    "customer_name TEXT, " +
                    "FOREIGN KEY (customer_name) REFERENCES customers(full_name)" +
                    ")";
            connection.createStatement().executeUpdate(createCarsTable);

            String createCustomersTable = "CREATE TABLE IF NOT EXISTS customers (" +
                    "full_name TEXT PRIMARY KEY, " +
                    "age INTEGER, " +
                    "gender TEXT" +
                    ")";
            connection.createStatement().executeUpdate(createCustomersTable);

            String createSoldCarsTable = "CREATE TABLE IF NOT EXISTS sold_cars (" +
                    "car_id INTEGER, " +
                    "customer_name TEXT, " +
                    "FOREIGN KEY (car_id) REFERENCES cars(car_id), " +
                    "FOREIGN KEY (customer_name) REFERENCES customers(full_name)" +
                    ")";
            connection.createStatement().executeUpdate(createSoldCarsTable);

            logger.info("Database tables created successfully");
        } catch (SQLException e) {
            logger.error("Failed to create database tables", e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

}
