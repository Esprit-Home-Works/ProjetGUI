package com.project.guiproject.services;

import com.project.guiproject.models.Team;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeamService {

    private Connection connection;

    // Constructor to initialize the database connection
    public TeamService(Connection connection) {
        this.connection = connection;
    }

    // Method to add a new team
    public void addTeam(Team team) throws SQLException {
        String query = "INSERT INTO team (teamName, teamDescription,teamLocation ) VALUES (?,?,?)";
        System.out.printf(query);
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, team.getTeamName());
            statement.setString(2, team.getTeamDescription());
            statement.setString(3, team.getTeamlocation());
            statement.executeUpdate();
        }
    }

    // Method to update an existing team
    public void updateTeam(Team team) throws SQLException {
        String query = "UPDATE team SET teamName=?, teamDescription=?, teamLocation=? WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, team.getTeamName());
            statement.setString(2, team.getTeamDescription());
            statement.setString(3, team.getTeamlocation());
            statement.setInt(4, team.getId());
            statement.executeUpdate();
        }
    }

    // Method to delete a team by ID
    public void deleteTeam(int teamId) throws SQLException {
        String query = "DELETE FROM team WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, teamId);
            statement.executeUpdate();
        }
    }




    // Method to get a team by ID
    public Team getTeamById(int teamId) throws SQLException {
        String query = "SELECT * FROM team WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, teamId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Team(
                    resultSet.getInt("id"),
                    resultSet.getString("teamName"),
                    resultSet.getString("teamDescription"),
                    resultSet.getString("teamLocation")
                );
            }
        }
        return null;
    }

    // Method to get all teams
    public List<Team> getAllTeams() throws SQLException {
        List<Team> teams = new ArrayList<>();
        String query = "SELECT * FROM team";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                teams.add(new Team(
                    resultSet.getInt("id"),
                    resultSet.getString("teamName"),
                    resultSet.getString("teamDescription"),
                    resultSet.getString("teamLocation")
                ));
            }
        }
        return teams;
    }

    public List<Team> get() throws SQLException {
        List<Team> teams = new ArrayList<>();
        String query = "SELECT * FROM team";
        PreparedStatement PS = connection.prepareStatement(query);
        PS.executeQuery();
        ResultSet RS = PS.getResultSet();
        while (RS.next()) {
            Team team = new Team(
                    RS.getInt("id"),
                    RS.getString("teamName"),
                    RS.getString("teamDescription"),
                    RS.getString("teamLocation")
            );
            teams.add(team);
        }
        return teams;
    }
}
