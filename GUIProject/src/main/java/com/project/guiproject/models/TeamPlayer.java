package com.project.guiproject.models;

public class TeamPlayer {
    private int id;
    private int teamId;
    private int playerId;

    public TeamPlayer() {

    }

    public TeamPlayer(int teamId, int playerId) {
        this.teamId = teamId;
        this.playerId = playerId;
    }

    public TeamPlayer(int id, int teamId, int playerId) {
        this.id = id;
        this.teamId = teamId;
        this.playerId = playerId;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    @Override
    public String toString() {
        return "TeamPlayer [id=" + id + ", teamId=" + teamId + ", playerId=" + playerId + "]";
    }

}
