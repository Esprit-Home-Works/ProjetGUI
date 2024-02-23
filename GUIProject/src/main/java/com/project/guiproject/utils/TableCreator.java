package com.project.guiproject.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TableCreator {

    private Connection connection;

    public TableCreator(Connection connection) {
        this.connection = connection;
    }

    public void createTables() {
        createPlayerTable();
        createTeamTable();
        createTeamPlayerTable();
    }

    private void createPlayerTable() {
        String createPlayerTableSQL = "CREATE TABLE IF NOT EXISTS Player (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "playerName VARCHAR(255)," +
                "playerAge INT," +
                "playerPosition VARCHAR(255)" +
                ")";
        executeSQL(createPlayerTableSQL);
    }

    private void createTeamTable() {
        String createTeamTableSQL = "CREATE TABLE IF NOT EXISTS Team (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "teamName VARCHAR(255)," +
                "teamDescription VARCHAR(255)," +
                "teamLocation VARCHAR(255)" +
                ")";
        executeSQL(createTeamTableSQL);
    }

    private void createTeamPlayerTable() {
        String createTeamPlayerTableSQL = "CREATE TABLE IF NOT EXISTS TeamPlayer (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "playerId INT," +
                "teamId INT," +
                "FOREIGN KEY (playerId) REFERENCES Player(id)," +
                "FOREIGN KEY (teamId) REFERENCES Team(id)" +
                ")";
        executeSQL(createTeamPlayerTableSQL);
    }

    private void executeSQL(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
