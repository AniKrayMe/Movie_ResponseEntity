package com.example.demo.database;

import com.example.demo.contant.MariaDBConstantForDatabase;
import com.example.demo.contant.MariaDBConstantForTable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database {
    private static Database objectReference = null;


    private Database() {
    }

    public static Database getInstance() {
        if (objectReference == null) {
            objectReference = new Database();
            return objectReference;
        } else {
            return objectReference;
        }
    }

    private void createDatabase() {
        try (Connection conn = DriverManager.getConnection(MariaDBConstantForDatabase.DB_URL,
                MariaDBConstantForDatabase.user, MariaDBConstantForDatabase.pass)) {
            if (conn != null) {
                String query = "CREATE DATABASE IF NOT EXISTS my_database";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }

    }


    private void createUserTable() {
        try (Connection conn = DriverManager.getConnection(MariaDBConstantForTable.DB_URL,
                MariaDBConstantForTable.user, MariaDBConstantForTable.pass)) {
            if (conn != null) {
                String query =
                        "CREATE TABLE IF NOT EXISTS user" +
                                "(" +
                                "id int AUTO_INCREMENT NOT NULL ," +
                                "name VARCHAR(50) NOT NULL ," +
                                "surname VARCHAR(50) NOT NULL ," +
                                "password VARCHAR(50) NOT NULL ," +
                                "PRIMARY KEY (id)" +
                                ");";

                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }


    private void createArticleTable() {
        try (Connection conn = DriverManager.getConnection(MariaDBConstantForTable.DB_URL,
                MariaDBConstantForTable.user, MariaDBConstantForTable.pass)) {
            if (conn != null) {
                String query =
                        "CREATE TABLE if NOT EXISTS article" +
                                "(" +
                                "id int AUTO_INCREMENT NOT NULL ," +
                                "title VARCHAR(50) NOT NULL ," +
                                "description VARCHAR(200) NOT NULL ," +
                                "date DATE NOT NULL ," +
                                "PRIMARY KEY (id)" +
                                ");";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    public static void createAndInitialiseDatabase() {
        Database database = Database.getInstance();
        database.createDatabase();
        database.createUserTable();
        database.createArticleTable();

    }
}


