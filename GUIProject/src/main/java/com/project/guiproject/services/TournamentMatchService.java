package com.project.guiproject.services;

import com.project.guiproject.models.TournamentMatch;
import com.project.guiproject.utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TournamentMatchService  {

    private static Connection connection;


    public TournamentMatchService() {
        connection = MyDatabase.getInstace().getConnection();
    }

    public static void add(TournamentMatch tournamentmatch) throws SQLException {
        String req = "INSERT INTO tournament_match (id,tournamentId ,matchId ,team1Id,team2Id ,winnerId) VALUES ('"
                + tournamentmatch.getId() + "', '" + tournamentmatch.getTournamentId() + "', '" + tournamentmatch.getMatchId() + "', '"
                + tournamentmatch.getTeam1Id() + "', '" + tournamentmatch.getTeam2Id() + "', '" + null + "')";
        Statement statement = connection.createStatement();
        statement.executeUpdate(req);
    }
    public static void setResults(int id, int winnerId) throws SQLException {

        String req = "UPDATE tournament_match SET winnerId = ? WHERE id = ?";
        PreparedStatement PS = connection.prepareStatement(req);
        PS.setInt(1, winnerId);
        PS.setInt(2, id);
        PS.executeUpdate();
    }

    public static List<String> getTournamentWinnersTeams(int tournamentId) throws SQLException {
        String req = "SELECT winnerId FROM tournament_match WHERE tournamentId = ? && status = 'pending'";
        PreparedStatement PS = connection.prepareStatement(req);
        PS.setInt(1, tournamentId);
        ResultSet resultSet = PS.executeQuery();
        List<String> winners = new ArrayList<>();
        while (resultSet.next()) {
            winners.add(resultSet.getString("winnerId"));
            String req2 = "UPDATE tournament_match SET status = 'played' WHERE tournamentId = ? && status = 'pending'";
            PreparedStatement PS2 = connection.prepareStatement(req2);
            PS2.setInt(1, tournamentId);
            PS2.executeUpdate();
        }
        return winners;
    }

}