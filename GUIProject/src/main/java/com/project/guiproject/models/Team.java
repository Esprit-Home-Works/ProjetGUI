package com.project.guiproject.models;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private int id;
    private String name;
    private String description;
    private int leaderId;
    private List<Integer> members;


    public Team(int id, String name, String description, int leaderId, List<Integer> members) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.leaderId = leaderId;
        this.members = members;
    }
    public Team(String name, String description, int leaderId, List<Integer> members) {
        this.name = name;
        this.description = description;
        this.leaderId = leaderId;
        this.members = members;
    }
    public Team(String name, String description, int leaderId) {
        this.name = name;
        this.description = description;
        this.leaderId = leaderId;
        this.members = new ArrayList<>();
    }
    public Team(int id, String name, String description, int leaderId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.leaderId = leaderId;
        this.members = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(int leaderId) {
        this.leaderId = leaderId;
    }

    public List<Integer> getMembers() {
        return members;
    }

    public void setMembers(List<Integer> members) {
        this.members = members;
    }
}
