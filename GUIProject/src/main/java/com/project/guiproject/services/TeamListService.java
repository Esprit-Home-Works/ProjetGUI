package com.project.guiproject.services;

import com.project.guiproject.models.Team;
import com.project.guiproject.utils.MyDataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class TeamListService implements IService<Team> {

    private final Connection connection;

    public TeamListService() {
        connection = MyDataBase.getInstace().getConnection();
    }

    @Override
    public void add(Team team) throws SQLException {
        String req = "INSERT INTO team (teamName, teamDescription,teamLocation) VALUES(?,?,?)";
        PreparedStatement PS = connection.prepareStatement(req);
        PS.setString(1, team.getTeamName());
        PS.setString(2, team.getTeamDescription());
        PS.setString(3, team.getTeamlocation());
        PS.executeUpdate();
    }

    @Override
    public void update(Team team) throws SQLException {
        String req = "UPDATE team SET teamName=?, teamDescription=?, teamLocation=? WHERE id=?";

        PreparedStatement PS = connection.prepareStatement(req);
        PS.setString(1, team.getTeamName());
        PS.setString(2, team.getTeamDescription());
        PS.setString(3, team.getTeamlocation());
        PS.setInt(4, team.getId());
        PS.executeUpdate();

    }

    @Override
    public void delete(int id) throws SQLException {
        String req = "DELETE FROM team WHERE id = ?";
        PreparedStatement PS = connection.prepareStatement(req);
        try {
            PS.setInt(1, id);
            PS.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Team getById(int id) throws SQLException {
        String query = "SELECT * FROM team WHERE id = ?";
        PreparedStatement PS = connection.prepareStatement(query);
        PS.setInt(1, id);
        PS.executeQuery();
        return PS.getResultSet().next()
                ? new Team(PS.getResultSet().getInt("id"),
                PS.getResultSet().getString("teamName"), PS.getResultSet().getString("teamDescription"),
                PS.getResultSet().getString("teamLocation"))
                : null;
    }

    @Override
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

    public ObservableList<Team> filter(String text, String text1) {
        try {
            String query = "SELECT * FROM team WHERE teamName LIKE ? AND teamLocation LIKE ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, "%" + text + "%");
            ps.setString(2, "%" + text1 + "%");
            ResultSet rs = ps.executeQuery();

            ObservableList<Team> filteredTeams = FXCollections.observableArrayList();

            while (rs.next()) {
                Team team = new Team(
                        rs.getInt("id"),
                        rs.getString("teamName"),
                        rs.getString("teamDescription"),
                        rs.getString("teamLocation")
                );
                filteredTeams.add(team);
            }

            return filteredTeams;
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
            return null; // or return an empty ObservableList
        }
    }

    public abstract Team createTeam(String teamName, String teamDescription, String teamLocation);

    public abstract Team getTeamById(int teamId);

    public abstract List<Team> getAllTeams();
}
