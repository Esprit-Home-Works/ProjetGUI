package com.project.guiproject.migration;

import com.project.guiproject.helpers.SqlHelpers;

import java.sql.SQLException;

public class MatchesMigration implements  MigrationInterface{

    @Override
    public void migrate(Boolean remove) throws SQLException {
        boolean tableExists = SqlHelpers.tableExists(connection, "matches");
        if (tableExists) {
            if (remove) {
                down();
                up();
            }
        } else {
            up();
        }
    }

    @Override
    public void up() {
        try {
            connection.createStatement().execute("CREATE TABLE IF NOT EXISTS matches (id INT PRIMARY KEY AUTO_INCREMENT, duration INT, name VARCHAR(255), description TEXT, code VARCHAR(255), startDate DATE, endDate DATE)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void down() {
        try {
            connection.createStatement().execute("DROP TABLE IF EXISTS matches");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
