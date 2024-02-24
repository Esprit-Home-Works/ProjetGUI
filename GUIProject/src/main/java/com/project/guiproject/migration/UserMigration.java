package com.project.guiproject.migration;

import com.project.guiproject.helpers.SqlHelpers;

import java.sql.SQLException;

public class UserMigration implements MigrationInterface {

    @Override
    public void migrate(Boolean remove) throws SQLException {
        boolean tableExists = SqlHelpers.tableExists(connection, "user");
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
                    "CREATE TABLE IF NOT EXISTS user ("
                            + "id INT PRIMARY KEY AUTO_INCREMENT, "
                            + "username VARCHAR(255), "
                            + "password VARCHAR(255), "
                            + "email VARCHAR(255), "
                            + "role VARCHAR(255)"
                            + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void down() {
        try {
            connection.createStatement().execute("DROP TABLE IF EXISTS user");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
