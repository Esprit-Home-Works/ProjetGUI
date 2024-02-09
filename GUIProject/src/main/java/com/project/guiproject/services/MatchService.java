package com.project.guiproject.services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.project.guiproject.models.Match;
import com.project.guiproject.utils.MyDataBase;
import javafx.collections.ObservableList;

public class MatchService implements IService<Match> {

    private Connection connection;

    public MatchService() {
        connection = MyDataBase.getInstace().getConnection();
    }

    @Override
    public void add(Match match) throws SQLException {
        String req = "INSERT INTO matches (duration, name, description, code, startDate, endDate) VALUES ('"
                + match.getDuration() + "', '" + match.getName() + "', '" + match.getDescription() + "', '"
                + match.getCode() + "', '" + match.getStartDate() + "', '" + match.getEndDate() + "')";
        Statement statement = connection.createStatement();
        statement.executeUpdate(req);
    }

    @Override
    public void update(Match match) throws SQLException {
        String req = "UPDATE matches SET duration = ?,name = ?, description = ? ,code = ?, startDate = ?, endDate = ? WHERE id = ?";

        PreparedStatement PS = connection.prepareStatement(req);
        PS.setInt(1, match.getDuration());
        PS.setString(2, match.getName());
        PS.setString(3, match.getDescription());
        PS.setString(4, match.getCode());
        PS.setDate(5, (Date) match.getStartDate());
        PS.setDate(6, (Date) match.getEndDate());
        PS.setInt(7, match.getId());
        PS.executeUpdate();

    }

    @Override
    public void delete(int id) throws SQLException {
        String req = "DELETE FROM matches WHERE id = ?";
        PreparedStatement PS = connection.prepareStatement(req);
        try {
            PS.setInt(1, id);
            PS.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Match getById(int id) throws SQLException {
        String query = "SELECT * FROM matches WHERE id = ?";
        PreparedStatement PS = connection.prepareStatement(query);
        PS.setInt(1, id);
        PS.executeQuery();
        return PS.getResultSet().next()
                ? new Match(PS.getResultSet().getInt("id"), PS.getResultSet().getInt("duration"),
                        PS.getResultSet().getString("name"), PS.getResultSet().getString("description"),
                        PS.getResultSet().getString("code"), PS.getResultSet().getDate("startDate"),
                        PS.getResultSet().getDate("endDate"))
                : null;
    }

    @Override
    public List<Match> get() throws SQLException {
        List<Match> matches = new ArrayList<>();
        String query = "SELECT * FROM matches";
        PreparedStatement PS = connection.prepareStatement(query);
        PS.executeQuery();
        ResultSet RS = PS.getResultSet();
        while (RS.next()) {
            Match match = new Match(RS.getInt("id"), RS.getInt("duration"), RS.getString("name"),
                    RS.getString("description"), RS.getString("code"), RS.getDate("startDate"),
                    RS.getDate("endDate"));
            matches.add(match);
        }
        return matches;
    }

    public ObservableList<Match> filter(String text, String text1) {
        try{
            String query = "select * from matches where name like ? and code like ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, "%" + text + "%");
            ps.setString(2, "%" + text1 + "%");
            ResultSet rs = ps.executeQuery();
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
