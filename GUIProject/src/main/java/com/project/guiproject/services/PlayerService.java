package com.project.guiproject.services;

import com.project.guiproject.models.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerService {

    private Connection connection;

    // Constructor to initialize the database connection
    public PlayerService(Connection connection) {
        this.connection = connection;
    }

    // Method to add a new player
    public void addPlayer(Player player) throws SQLException {
        String query = "INSERT INTO player (playerName, playerAge, playerPosition) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, player.getPlayerName());
            statement.setInt(2, player.getAge());
            statement.setString(3, player.getPosition());
            statement.executeUpdate();
        }
    }

    // Method to update an existing player
    // Method to update an existing player
    public void updatePlayer(Player player) throws SQLException {
        String query = "UPDATE player SET playerName = ?, playerAge = ?, playerPosition = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, player.getPlayerName());
            preparedStatement.setInt(2, player.getAge());
            preparedStatement.setString(3, player.getPosition());
            preparedStatement.setInt(4, player.getId());
            preparedStatement.executeUpdate();
        }
    }


    // Method to delete a player by ID
//    public void deletePlayer(int playerId) throws SQLException {
//        String query = "DELETE FROM player WHERE id=?";
//        try (PreparedStatement statement = connection.prepareStatement(query)) {
//            statement.setInt(1, playerId);
//            statement.executeUpdate();
//        }
//    }

    // Method to get a player by ID
    public Player getPlayerById(int playerId) throws SQLException {
        String query = "SELECT * FROM player WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, playerId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Player(
                        resultSet.getInt("id"),
                        resultSet.getString("playerName"),
                        resultSet.getInt("playerAge"),
                        resultSet.getString("playerPosition")
                );
            }
        }
        return null;
    }

    // Method to get all players
    public List<Player> getAllPlayers() throws SQLException {
        List<Player> players = new ArrayList<>();
        String query = "SELECT * FROM player";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                players.add(new Player(
                        resultSet.getInt("id"),
                        resultSet.getString("playerName"),
                        resultSet.getInt("playerAge"),
                        resultSet.getString("playerPosition")
                ));
            }
        }
        return players;

    }
    public class PlayerSearchService {

        private Connection connection;

        // Constructor to initialize the database connection
        public PlayerSearchService(Connection connection) {
            this.connection = connection;
        }

        public List<Player> searchPlayersByName(String name) throws SQLException {
            List<Player> players = new ArrayList<>();
            String query = "SELECT * FROM player WHERE playerName LIKE ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, "%" + name + "%");
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    players.add(new Player(
                            resultSet.getInt("id"),
                            resultSet.getString("playerName"),
                            resultSet.getInt("playerAge"),
                            resultSet.getString("playerPosition")
                    ));
                }
            }
            return players;
        }
    }

}
