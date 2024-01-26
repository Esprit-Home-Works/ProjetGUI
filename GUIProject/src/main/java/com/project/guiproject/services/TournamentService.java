package com.project.guiproject.services;

import com.project.guiproject.models.Tournament;
import com.project.guiproject.utils.MyDatabase;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TournamentService implements IService<Tournament> {

    private Connection connection;

    public TournamentService() {
        this.connection = MyDatabase.getInstance().getConnection();
    }

    @Override
    public void add(Tournament tournament) throws SQLException {
        if (
                tournament.getName().isEmpty() ||
                tournament.getStartDate() == null ||
                tournament.getEndDate() == null ||
                tournament.getDescription().isEmpty() ||
                tournament.getMaxTeams() == 0
        )
        {
            System.out.println("Invalid tournament");
            return;
        } else if (tournament.getStartDate().isAfter(tournament.getEndDate())) {
            System.out.println("Start date must be before end date");
            return;

        } else if (tournament.getStartDate().isBefore(LocalDate.now())) {
            System.out.println("Start date must be in the future");
            return;

        } else if (tournament.getMaxTeams() < 2) {
            System.out.println("Tournament must have at least 2 teams");
            return;

        } else if (tournament.getMaxTeams() % 2 != 0) {
            System.out.println("Tournament must have an even number of teams");
            return;

        } else {
        String request = "INSERT INTO tournaments (name, start_date, end_date, description, max_teams) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(request);
        ps.setString(1, tournament.getName());
        ps.setDate(2, Date.valueOf(tournament.getStartDate()));
        ps.setDate(3, Date.valueOf(tournament.getEndDate()));
        ps.setString(4, tournament.getDescription());
        ps.setInt(5, tournament.getMaxTeams());
        ps.executeUpdate();}
    }

    @Override
    public void update(Tournament tournament) throws SQLException {
        if (
                tournament.getName().isEmpty() ||
                        tournament.getStartDate() == null ||
                        tournament.getEndDate() == null ||
                        tournament.getDescription().isEmpty() ||
                        tournament.getMaxTeams() == 0
        )
        {
            System.out.println("Invalid tournament");
            return;
        } else if (tournament.getStartDate().isAfter(tournament.getEndDate())) {
            System.out.println("Start date must be before end date");
            return;

        } else if (tournament.getStartDate().isBefore(LocalDate.now())) {
            System.out.println("Start date must be in the future");
            return;

        } else if (tournament.getMaxTeams() < 2) {
            System.out.println("Tournament must have at least 2 teams");
            return;

        } else if (tournament.getMaxTeams() % 2 != 0) {
            System.out.println("Tournament must have an even number of teams");
            return;

        } else {
        String request = "UPDATE tournaments SET name = ?, start_date = ?, end_date = ?, description = ?, max_teams = ? WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(request);
        ps.setString(1, tournament.getName());
        ps.setDate(2, Date.valueOf(tournament.getStartDate()));
        ps.setDate(3, Date.valueOf(tournament.getEndDate()));
        ps.setString(4, tournament.getDescription());
        ps.setInt(5, tournament.getMaxTeams());
        ps.setInt(6, tournament.getId()); // Assuming the tournament has an ID
        ps.executeUpdate();}
    }

    @Override
    public void delete(int id) throws SQLException {
        String request = "DELETE FROM tournaments WHERE id = ";
        PreparedStatement ps = connection.prepareStatement(request);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    @Override
    public Tournament get(int id) throws SQLException {
        String request = "SELECT * FROM tournaments WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(request);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            // Map database fields to Tournament object properties
            return new Tournament(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDate("start_date").toLocalDate(),
                    rs.getDate("end_date").toLocalDate(),
                    rs.getString("description"),
                    rs.getInt("max_teams")
            );
        } else {
            return null;
        }
    }

    @Override
    public List<Tournament> getAll() throws SQLException {
        List<Tournament> tournaments = new ArrayList<>();
        String request = "SELECT * FROM tournaments";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(request);
        while (rs.next()) {
            tournaments.add(new Tournament(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDate("start_date").toLocalDate(),
                    rs.getDate("end_date").toLocalDate(),
                    rs.getString("description"),
                    rs.getInt("max_teams")
            ));
        }
        return tournaments;
    }

    public void AddTeamToTournament(int tournamentId, String teamId) throws SQLException {


        if (this.get(tournamentId).getTeams().size() >= this.get(tournamentId).getMaxTeams()) {
            System.out.println("Tournament is full");
            return;
        }
        else
        {
           Tournament tournament = this.get(tournamentId);
           tournament.getTeams().add(teamId);
           this.update(tournament);
        }

    }
    public String getTournamentProgress(int tournamentId) throws SQLException {
        Tournament tournament = this.get(tournamentId);
        int teams = tournament.getTeams().size();
        int maxTeams = tournament.getMaxTeams();
        int progress = (teams * 100) / maxTeams;
        return progress + "%";
    }
}


