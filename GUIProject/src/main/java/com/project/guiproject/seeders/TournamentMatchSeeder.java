package com.project.guiproject.seeders;

import com.project.guiproject.models.TournamentMatch;
import com.project.guiproject.services.TournamentMatchService;

public class TournamentMatchSeeder extends Seeders {

    TournamentMatchService tournamentMatchService = new TournamentMatchService();

    @Override
    public void seed() {
        try {
            tournamentMatchService.add(new TournamentMatch(1, 1, 1, 2 ));
            tournamentMatchService.add(new TournamentMatch(1, 2, 3, 4));
            // Add more tournament matches as needed
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
