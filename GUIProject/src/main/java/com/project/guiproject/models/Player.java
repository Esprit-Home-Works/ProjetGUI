package com.project.guiproject.models;
public class Player extends User {
    private int teamId;

    public Player() {

    }

    public Player(int userId, int teamId) {
        super();
        this.teamId = teamId;
    }

    public Player(int playerId, String username, String password, String email, int teamId) {
        super(playerId, username, password, email); // Appel du constructeur de la classe parente
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
