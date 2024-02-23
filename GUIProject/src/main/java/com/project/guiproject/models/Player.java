package com.project.guiproject.models;

import javax.persistence.*;

@Entity
@Table(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    private String playerName;
    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    private String position;
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    // Constructors
    public Player() {
    }

    public Player(String playerName, int age, String position) {
        this.playerName = playerName;
        this.age = age;
        this.position = position;
    }

    public Player(int id, String playerName, int age, String position) {
        this.id = id;
        this.playerName = playerName;
        this.age = age;
        this.position = position;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", playerName='" + playerName + '\'' +
                ", age=" + age +
                ", position='" + position + '\'' +
                '}';
    }
}