package com.project.guiproject.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import javax.persistence.Entity;

@Entity
@Table(name = "tournaments")
public class TournamentTeam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int tournamentId;
    private int teamId;

    public TournamentTeam() {

    }

    public TournamentTeam(int id, int tournamentId, int teamId) {
        this.id = id;
        this.tournamentId = tournamentId;
        this.teamId = teamId;
    }

    public TournamentTeam(int tournamentId, int teamId) {
        this.tournamentId = tournamentId;
        this.teamId = teamId;
    }

    @Override
    public String toString() {
        return "TournamentTeam{" +
                "id=" + id +
                ", tournamentId=" + tournamentId +
                ", teamId=" + teamId +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(int tournamentId) {
        this.tournamentId = tournamentId;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }
}
