package com.project.guiproject.models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.project.guiproject.models.User;
import com.project.guiproject.utils.MyDataBase;
public class MatchTeam {
    private int id;
    private int matchId;
    private int teamId;

    public MatchTeam() {
        // Constructeur par défaut
    }

    public MatchTeam(int matchId, int teamId) {
        this.matchId = matchId;
        this.teamId = teamId;
    }

    public MatchTeam(int id, int matchId, int teamId) {
        this.id = id;
        this.matchId = matchId;
        this.teamId = teamId;
    }

    // Ajouter les getters et setters nécessaires

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    @Override
    public String toString() {
        return "MatchTeam [id=" + id + ", matchId=" + matchId + ", teamId=" + teamId + "]";
    }
}
