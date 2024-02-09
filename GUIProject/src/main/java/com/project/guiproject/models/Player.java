package com.project.guiproject.models;


import jakarta.persistence.Column;
import jakarta.persistence.Table;

import javax.persistence.Entity;
import java.util.List;


@Entity
@Table(name = "players")
public class Player extends User {
    @Column(name = "team_id")
    private int teamId;

    public Player() {
    }

    public Player(int userId, int teamId) {
        super();
        this.teamId = teamId;
    }

    public Player(int playerId, String username, String password, String email, int teamId) {
        super(playerId, username, password, email);
        this.teamId = teamId;
    }


    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

   /* @Override
   public String toString() {

        return "Player [userId=" + getUserId() + ", teamId=" + teamId + ", username=" + getUsername() + ", password=" + getPassword() + ", email=" + getEmail() + "]";
    }

    private String getUserId() {
    }*/


    public interface PlayerService {
        // Method to add a new player
        Player addPlayer(Player player);

        // Method to update an existing player
        Player updatePlayer(Player player);

        // Method to delete a player by ID
        void deletePlayer(int playerId);

        // Method to get a player by ID
        Player getPlayerById(int playerId);

        // Method to get all players
        List<Player> getAllPlayers();
    }

}
