package com.project.guiproject.services;

import com.project.guiproject.models.Player;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class PlayerService implements Player.PlayerService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Player addPlayer(Player player) {
        entityManager.persist(player);
        return player;
    }

    @Override
    public Player updatePlayer(Player player) {
        return entityManager.merge(player);
    }

    @Override
    public void deletePlayer(int playerId) {
        Player player = getPlayerById(playerId);
        if (player != null) {
            entityManager.remove(player);
        }
    }

    @Override
    public Player getPlayerById(int playerId) {
        return entityManager.find(Player.class, playerId);
    }

    @Override
    public List<Player> getAllPlayers() {
        return entityManager.createQuery("SELECT p FROM Player p", Player.class)
                .getResultList();
    }
}
