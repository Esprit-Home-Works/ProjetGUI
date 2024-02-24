package com.project.guiproject.models;

public class TournamentMatch {



    private int id, tournamentId, matchId, team1Id, team2Id, winnerId;


    public TournamentMatch(int tournamentId, int matchId, int team1Id, int team2Id) {
        // the winnerId is null because the match is not played yet
        this.tournamentId = tournamentId;
        this.matchId = matchId;
        this.team1Id = team1Id;
        this.team2Id = team2Id;
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

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public int getTeam1Id() {
        return team1Id;
    }

    public void setTeam1Id(int team1Id) {
        this.team1Id = team1Id;
    }

    public int getTeam2Id() {
        return team2Id;
    }

    public void setTeam2Id(int team2Id) {
        this.team2Id = team2Id;
    }

    public int getWinnerId() {
        return winnerId;
    }

    public void setWinnerId(int winnerId) {
        this.winnerId = winnerId;
    }
}
