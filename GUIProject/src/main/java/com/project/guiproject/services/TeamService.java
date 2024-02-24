package com.project.guiproject.services;

import com.project.guiproject.models.Team;
import com.project.guiproject.utils.MyDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TeamService implements IService<Team> {

    private Connection connection;

    public TeamService() {
        connection = MyDatabase.getInstace().getConnection();
    }

    @Override
    public void add(Team team) throws SQLException {
        String query = "INSERT INTO teams (name, description, leaderId, members) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, team.getName());
        ps.setString(2, team.getDescription());
        ps.setInt(3, team.getLeaderId());
        ps.setString(4, serializeMembers(team.getMembers()));
        ps.executeUpdate();

        ResultSet generatedKeys = ps.getGeneratedKeys();
        if (generatedKeys.next()) {
            team.setId(generatedKeys.getInt(1));
        }
    }

    @Override
    public void update(Team team) throws SQLException {
        String query = "UPDATE teams SET name = ?, description = ?, leaderId = ?, members = ? WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, team.getName());
        ps.setString(2, team.getDescription());
        ps.setInt(3, team.getLeaderId());
        ps.setString(4, serializeMembers(team.getMembers()));
        ps.setInt(5, team.getId());
        ps.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        String query = "DELETE FROM teams WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    @Override
    public Team getById(int id) throws SQLException {
        String query = "SELECT * FROM teams WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return new Team(rs.getInt("id"), rs.getString("name"), rs.getString("description"),
                    rs.getInt("leaderId"), deserializeMembers(rs.getString("members")));
        } else {
            return null;
        }
    }

    @Override
    public List<Team> get() throws SQLException {
        List<Team> teams = new ArrayList<>();
        String query = "SELECT * FROM teams";
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Team team = new Team(rs.getInt("id"), rs.getString("name"), rs.getString("description"),
                    rs.getInt("leaderId"), deserializeMembers(rs.getString("members")));
            teams.add(team);
        }
        return teams;
    }

    // Method to add a member to a team
    public void addMember(int teamId, int memberId) throws SQLException {
        Team team = getById(teamId);
        if (team != null) {
            List<Integer> members = team.getMembers();
            members.add(memberId);
            update(team);
        }
    }

    // Method to remove a member from a team
    public void removeMember(int teamId, int memberId) throws SQLException {
        Team team = getById(teamId);
        if (team != null) {
            List<Integer> members = team.getMembers();
            members.remove(Integer.valueOf(memberId));
            update(team);
        }
    }

    // Helper method to serialize members list to string
    private String serializeMembers(List<Integer> members) {
        StringBuilder sb = new StringBuilder();
        for (Integer memberId : members) {
            sb.append(memberId).append(",");
        }
        return sb.toString();
    }

    // Helper method to deserialize string to members list
    private List<Integer> deserializeMembers(String membersString) {
        List<Integer> members = new ArrayList<>();
        if (membersString != null && !membersString.isEmpty()) {
            String[] memberIds = membersString.split(",");
            for (String memberId : memberIds) {
                members.add(Integer.parseInt(memberId));
            }
        }
        return members;
    }
}
