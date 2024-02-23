package com.project.guiproject.migration;

import com.project.guiproject.helpers.SqlHelpers;

import java.sql.SQLException;

public class TournamentMigration implements MigrationInterface {

    @Override
    public void migrate(Boolean remove) throws SQLException {
        boolean tableExists = SqlHelpers.tableExists(connection, "tournaments");
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
            connection.createStatement().execute(
                    "CREATE TABLE IF NOT EXISTS tournaments ("
                            + "id INT PRIMARY KEY AUTO_INCREMENT, "
                            + "name VARCHAR(255), "
                            + "startDate DATE, "
                            + "endDate DATE, "
                            + "description TEXT, "
                            + "maxTeams INT"
                            + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void down() {
        try {
            connection.createStatement().execute("DROP TABLE IF EXISTS tournaments");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
