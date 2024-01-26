package com.project.guiproject.test;

import java.sql.Date;

import com.project.guiproject.models.Match;
import com.project.guiproject.services.MatchService;
import com.project.guiproject.utils.MyDataBase;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        MyDataBase myDataBase = new MyDataBase();
        MatchService matchService = new MatchService();
        try {
            // matchService.add(new Match(180, "match 1", "test Match",
            // "MT_001", new Date(2021, 1, 1),
            // new Date(2021, 1, 1)));
            // matchService.add(new Match(180, "match 2", "test Match",
            // "MT_001", new Date(2021, 1, 1),
            // new Date(2021, 1, 1)));
            // matchService.update(new Match(1, 180, "match Updated", "test Match",
            // "MT_001", new Date(2021, 1, 1),
            // new Date(2021, 1, 1)));
            // matchService.delete(1);
            Match mt = matchService.getById(2);
            System.out.println(mt);

            System.out.println("the list of matches: ");

            matchService.get().forEach(match -> {
                System.out.println(match);
            });

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
