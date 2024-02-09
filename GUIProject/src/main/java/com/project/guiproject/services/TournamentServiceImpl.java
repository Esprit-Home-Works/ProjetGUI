package com.project.guiproject.services;

import com.project.guiproject.models.Tournament;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public class TournamentServiceImpl implements TournamentService {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Tournament addTournament(Tournament tournament) {
        entityManager.persist(tournament);
        return tournament;
    }

    @Override
    public Tournament updateTournament(Tournament tournament) {
        return entityManager.merge(tournament);
    }

    @Override
    public void deleteTournament(int tournamentId) {
        Tournament tournament = getTournamentById(tournamentId);
        if (tournament != null) {
            entityManager.remove(tournament);
        }
    }

    @Override
    public Tournament getTournamentById(int tournamentId) {
        return entityManager.find(Tournament.class, tournamentId);
    }

    @Override
    public List<Tournament> getAllTournaments() {
        return entityManager.createQuery("SELECT t FROM Tournament t", Tournament.class)
                .getResultList();
    }
}
