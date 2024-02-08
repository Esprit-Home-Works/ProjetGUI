package com.project.guiproject.test;

import com.project.guiproject.models.Match;
import com.project.guiproject.models.User;
import com.project.guiproject.services.MatchService;
import com.project.guiproject.services.MatchServiceImpl;
import com.project.guiproject.services.UserService;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        // Instantiate services
        UserService userService = new UserServiceImpl();
        MatchService matchService = new MatchServiceImpl();

        try {
            // Register a new user
            User newUser = userService.registerUser("testUser", "password123", "test@example.com");
            System.out.println("Registered user: " + newUser);

            // Create a new match
            Match newMatch = matchService.createMatch(180, "testMatch", "Description", "MT_001");
            System.out.println("Created match: " + newMatch);

            // Get a match by ID
            Match matchById = matchService.getMatchById(1);
            System.out.println("Match by ID: " + matchById);

            // Get all matches
            System.out.println("All matches:");
            matchService.getAllMatches().forEach(System.out::println);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static class UserServiceImpl extends UserService {
    }
}
