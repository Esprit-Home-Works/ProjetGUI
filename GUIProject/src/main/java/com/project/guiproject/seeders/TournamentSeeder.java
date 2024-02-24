package com.project.guiproject.seeders;

import com.project.guiproject.models.Tournament;
import com.project.guiproject.services.TournamentService;

import java.time.LocalDate;

public class TournamentSeeder extends Seeders {

    TournamentService tournamentService = new TournamentService();

    @Override
    public void seed() {
        try {
            tournamentService.add(new Tournament(1, "Tournament 1", LocalDate.of(2024, 4, 1), LocalDate.of(2024, 5, 1), "Description 1", 16));
            tournamentService.add(new Tournament(2, "Tournament 2", LocalDate.of(2024, 4, 1), LocalDate.of(2024, 5, 1), "Description 2", 8));
            // Add more tournaments as needed
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
