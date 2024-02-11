package com.project.guiproject.models;

import java.sql.Date;

//@Entity
//@Table(name = "matches")
public class Match {

   // @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int duration;
    private String name;
    private String description;
    private String code;

    //@Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    //@Temporal(TemporalType.TIMESTAMP)
    private Date endDate;


    public Match(int id, int duration, String name, String description, String code, Date startDate, Date endDate) {
        this.id = id;
        this.duration = duration;
        this.name = name;
        this.description = description;
        this.code = code;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Match(int duration, String name, String description, String code, Date startDate, Date endDate) {
        this.duration = duration;
        this.name = name;
        this.description = description;
        this.code = code;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Match [duration=" + duration + ", name=" + name + ", description=" + description + ", code=" + code
                + ", startDate=" + startDate + ", endDate=" + endDate + "]";
    }
}