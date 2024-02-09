package com.project.guiproject.services;

import com.project.guiproject.models.Team;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
public interface TeamService {
    // Method to add a new team
    Team addTeam(Team team);

    // Method to update an existing team
    Team updateTeam(Team team);

    // Method to delete a team by ID
    void deleteTeam(int teamId);

    // Method to get a team by ID
    Team getTeamById(int teamId);

    // Method to get all teams
    List<Team> getAllTeams();
}



