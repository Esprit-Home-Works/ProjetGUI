package com.project.guiproject.services;

import com.project.guiproject.models.Tournament;
import com.project.guiproject.models.TournamentMatch;
import com.project.guiproject.utils.MyDatabase;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TournamentService implements IService<Tournament> {

    private Connection connection;

    public TournamentService() {
        this.connection = MyDatabase.getInstace().getConnection();
    }

    @Override
    public void add(Tournament tournament) throws SQLException {
        if (
                tournament.getName().isEmpty() ||
                        tournament.getStartDate() == null ||
                        tournament.getEndDate() == null ||
                        tournament.getDescription().isEmpty() ||
                        tournament.getMaxTeams() == 0
        ) {
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
            String request = "INSERT INTO tournaments (name, startDate, endDate, description, maxTeams) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(request);
            ps.setString(1, tournament.getName());
            ps.setDate(2, Date.valueOf(tournament.getStartDate()));
            ps.setDate(3, Date.valueOf(tournament.getEndDate()));
            ps.setString(4, tournament.getDescription());
            ps.setInt(5, tournament.getMaxTeams());
            ps.executeUpdate();
        }
    }

    @Override
    public void update(Tournament tournament) throws SQLException {
        if (
                tournament.getName().isEmpty() ||
                        tournament.getStartDate() == null ||
                        tournament.getEndDate() == null ||
                        tournament.getDescription().isEmpty() ||
                        tournament.getMaxTeams() == 0
        ) {
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
            String request = "UPDATE tournaments SET name = ?, startDate = ?, endDate = ?, description = ?, maxTeams = ? WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(request);
            ps.setString(1, tournament.getName());
            ps.setDate(2, Date.valueOf(tournament.getStartDate()));
            ps.setDate(3, Date.valueOf(tournament.getEndDate()));
            ps.setString(4, tournament.getDescription());
            ps.setInt(5, tournament.getMaxTeams());
            ps.setInt(6, tournament.getId()); // Assuming the tournament has an ID
            ps.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String request = "DELETE FROM tournaments WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(request);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    @Override
    public Tournament getById(int id) throws SQLException {
        String request = "SELECT * FROM tournaments WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(request);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            // Map database fields to Tournament object properties
            return new Tournament(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDate("startDate").toLocalDate(),
                    rs.getDate("endDate").toLocalDate(),
                    rs.getString("description"),
                    rs.getInt("maxTeams")
            );
        } else {
            return null;
        }
    }



    @Override
    public List<Tournament> get() throws SQLException {
        List<Tournament> tournaments = new ArrayList<>();
        String request = "SELECT * FROM tournaments";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(request);
        while (rs.next()) {
            tournaments.add(new Tournament(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDate("startDate").toLocalDate(),
                    rs.getDate("endDate").toLocalDate(),
                    rs.getString("description"),
                    rs.getInt("maxTeams")
            ));
        }
        return tournaments;
    }

    public void AddTeamToTournament(int tournamentId, String teamId) throws SQLException {


        if (this.getById(tournamentId).getTeams().size() >= this.getById(tournamentId).getMaxTeams()) {
            System.out.println("Tournament is full");
            return;
        } else {
            Tournament tournament = this.getById(tournamentId);
            tournament.getTeams().add(teamId);
            this.update(tournament);
        }

    }
    public ArrayList<Tournament> filter(String text) {
        try{
            String query = "select * from tournaments where  name like ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, "%" + text + "%");
            ResultSet rs = ps.executeQuery();
            return getTournaemnts(rs);
        } catch (Exception e) {
            return null;
        }
    }
    public  ArrayList<Tournament> getTournaemnts(ResultSet rs) throws SQLException {
        ArrayList<Tournament> Tournaments = new ArrayList<>();
        while (rs.next()) {
            Tournament tournament = new Tournament(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDate("startDate").toLocalDate(),
                    rs.getDate("endDate").toLocalDate(),
                    rs.getString("description"),
                    rs.getInt("maxTeams"));
            Tournaments.add(tournament);
        }
        return Tournaments;
    }

    public String getTournamentProgress(int tournamentId) throws SQLException {
        Tournament tournament = this.getById(tournamentId);
        int teams = tournament.getTeams().size();
        int maxTeams = tournament.getMaxTeams();
        int progress = (teams * 100) / maxTeams;
        return progress + "%";
    }

    public void generateTournamentMatches(int tournamentId) throws SQLException {
        // use this function with is TournamentMatch.add :  public void add(TournamentMatch tournamentmatch) throws SQLException {
        //        String req = "INSERT INTO tournament_match (id,tournamentId ,matchId ,team1Id,team2Id ,winnerId) VALUES ('"
        //                + tournamentmatch.getId() + "', '" + tournamentmatch.getTournamentId() + "', '" + tournamentmatch.getMatchId() + "', '"
        //                + tournamentmatch.getTeam1Id() + "', '" + tournamentmatch.getTeam2Id() + "', '" + null + "')";
        //        Statement statement = connection.createStatement();
        //        statement.executeUpdate(req);
        //    }
        // and is TournamentMatchService.add : public void add(TournamentMatch tournamentmatch) throws SQLException {

        Tournament tournament = this.getById(tournamentId);
        List<String> teams = tournament.getTeams();
        int matches = teams.size() / 2;
        int matchId = MatchService.createRandomMatch();
        for (int i = 0; i < matches; i++) {
            int team1Id = Integer.parseInt(teams.get(i));
            int team2Id = Integer.parseInt(teams.get(i + 1));
            TournamentMatch match = new TournamentMatch(tournamentId, matchId, team1Id, team2Id);
            matchId++;
            TournamentMatchService.add(match);
        }
    }
    public void updateTeamsForNextRound(int tournamentId) {

        try {
            List<String> winners = TournamentMatchService.getTournamentWinnersTeams(tournamentId);
            Tournament tournament = this.getById(tournamentId);
            tournament.setTeams(winners);
            this.update(tournament);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}

