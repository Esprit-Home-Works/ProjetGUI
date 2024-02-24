package com.project.guiproject.migration;

import com.project.guiproject.helpers.SqlHelpers;

import java.sql.SQLException;

public class TeamMigration implements MigrationInterface {

    @Override
    public void migrate(Boolean remove) throws SQLException {
        boolean tableExists = SqlHelpers.tableExists(connection, "teams");
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
            connection.createStatement().execute("CREATE TABLE IF NOT EXISTS teams (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255) NOT NULL, description TEXT, leaderId INT, members TEXT)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void down() {
        try {
            connection.createStatement().execute("DROP TABLE IF EXISTS teams");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
