package com.project.guiproject.models;


import jakarta.persistence.Column;
import jakarta.persistence.Table;

import javax.persistence.Entity;


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
}
