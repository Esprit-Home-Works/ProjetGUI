package com.project.guiproject.seeders;

import com.project.guiproject.models.Match;
import com.project.guiproject.services.MatchService;

import java.sql.Date;
public class MatchesSeeder extends Seeders{

    MatchService matchService = new MatchService();

    @Override
    public void seed() {
        try {

            matchService.add(new Match(60, "Match 1", "Description 1", "MT_001", new Date(2024,2,1 ), new Date(2024,02,01)));
            matchService.add(new Match(90, "Match 2", "Description 2", "MT_002", new Date(2024,2,1 ), new Date(2024,02,01)));
            matchService.add(new Match(120, "Match 3", "Description 3", "MT_003", new Date(2024,2,1 ), new Date(2024,02,01)));
            matchService.add(new Match(150, "Match 4", "Description 4", "MT_004", new Date(2024,2,1 ), new Date(2024,02,01)));
            matchService.add(new Match(180, "Match 5", "Description 5", "MT_005", new Date(2024,2,1 ), new Date(2024,02,01)));
            matchService.add(new Match(210, "Match 6", "Description 6", "MT_006", new Date(2024,2,1 ), new Date(2024,02,01)));
            matchService.add(new Match(240, "Match 7", "Description 7", "MT_007", new Date(2024,2,1 ), new Date(2024,02,01)));
            matchService.add(new Match(270, "Match 8", "Description 8", "MT_008", new Date(2024,2,1 ), new Date(2024,02,01)));
            matchService.add(new Match(300, "Match 9", "Description 9", "MT_009", new Date(2024,2,1 ), new Date(2024,02,01)));
            matchService.add(new Match(330, "Match 10", "Description 10", "MT_010", new Date(2024,2,1 ), new Date(2024,02,01)));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
