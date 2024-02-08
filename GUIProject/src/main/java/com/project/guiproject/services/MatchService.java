package com.project.guiproject.services;





import com.project.guiproject.models.Match;

import java.util.List;

public interface MatchService {
    Match createMatch(int duration, String name, String description, String code);
    Match getMatchById(int matchId);
    List<Match> getAllMatches();
    // Add other methods as needed for managing matches
}


/*public class MatchService   {

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
    public void update(MatchService match) throws SQLException {
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
    public List<MatchService> get() throws SQLException {
        List<Match> matches = new ArrayList<>();
        String query = "SELECT * FROM matches";
        PreparedStatement PS = connection.prepareStatement(query);
        PS.executeQuery();
        ResultSet RS = PS.getResultSet();
        while (RS.next()) {
            Match match = new MatchService(RS.getInt("id"), RS.getInt("duration"), RS.getString("name"),
                    RS.getString("description"), RS.getString("code"), RS.getDate("startDate"),
                    RS.getDate("endDate"));
            matches.add(match);
        }
        return matches;
    }

    */

