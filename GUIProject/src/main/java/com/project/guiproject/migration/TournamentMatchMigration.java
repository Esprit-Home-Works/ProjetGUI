package com.project.guiproject.migration;

import com.project.guiproject.helpers.SqlHelpers;

import java.sql.SQLException;

public class TournamentMatchMigration implements MigrationInterface {

    @Override
    public void migrate(Boolean remove) throws SQLException {
        boolean tableExists = SqlHelpers.tableExists(connection, "tournament_matches");
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
                    "CREATE TABLE IF NOT EXISTS tournament_matches ("
                            + "id INT PRIMARY KEY AUTO_INCREMENT, "
                            + "tournament_id INT, "
                            + "match_id INT, "
                            + "team1_id INT, "
                            + "team2_id INT, "
                            + "winner_id INT"
                            + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void down() {
        try {
            connection.createStatement().execute("DROP TABLE IF EXISTS tournament_matches");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
