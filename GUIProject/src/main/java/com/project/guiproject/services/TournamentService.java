package com.project.guiproject.services;

import com.project.guiproject.models.Tournament;

import java.util.List;

public interface TournamentService {
    Tournament addTournament(Tournament tournament);
    Tournament updateTournament(Tournament tournament);
    void deleteTournament(int tournamentId);

    Tournament getTournamentById(int tournamentId);
    List<Tournament> getAllTournaments();
}

