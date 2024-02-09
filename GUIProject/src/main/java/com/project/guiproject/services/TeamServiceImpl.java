package com.project.guiproject.services;

import com.project.guiproject.models.Team;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class TeamServiceImpl implements TeamService {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Team addTeam(Team team) {
        entityManager.persist(team);
        return team;
    }


    @Override
    public Team updateTeam(Team team) {
        return entityManager.merge(team);
    }


    @Override
    public void deleteTeam(int teamId) {
        Team team = getTeamById(teamId);
        if (team != null) {
            entityManager.remove(team);
        }
    }

    @Override
    public Team getTeamById(int teamId) {
        return entityManager.find(Team.class, teamId);
    }

    @Override
    public List<Team> getAllTeams() {
        return entityManager.createQuery("SELECT t FROM Team t", Team.class)
                .getResultList();
    }
}
