package com.project.guiproject.migration;

import com.project.guiproject.seeders.MatchesSeeder;
import com.project.guiproject.seeders.TournamentSeeder;

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
        TournamentMigration tournamentMigration = new TournamentMigration();
        try {
            tournamentMigration.migrate(true);
        } catch (SQLException e) {
            e.printStackTrace();
    }
        if (seed) {
            TournamentSeeder tournamentSeeder = new TournamentSeeder();
            tournamentSeeder.seed();
        }
        /*try {
            UserMigration userMigration = new UserMigration();
            userMigration.migrate(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

    }
}
