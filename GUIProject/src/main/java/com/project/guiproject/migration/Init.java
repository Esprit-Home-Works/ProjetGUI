package com.project.guiproject.migration;

import com.project.guiproject.seeders.MatchesSeeder;

import java.sql.SQLException;

public class Init {

    public void run(Boolean seed) {
        MatchesMigration matchesMigration = new MatchesMigration();
        try {
            matchesMigration.migrate(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (seed) {
            MatchesSeeder matchesSeeder = new MatchesSeeder();
            matchesSeeder.seed();
        }
    }
}
