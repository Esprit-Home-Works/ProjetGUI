package com.project.guiproject.controllers.TeamPlayers;

import com.project.guiproject.models.Team;
import com.project.guiproject.services.TeamService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddTeamController {

    @FXML
    private TextField teamNameField;

    @FXML
    private TextField teamLocationField;

    private TeamService teamService;

    @FXML
    private void addTeam(ActionEvent event) {
        String teamName = teamNameField.getText();
        String teamLocation = teamLocationField.getText();

        // Add validation logic if needed

        Team team = new Team(teamName, teamLocation);
        teamService.addTeam(team);
        // Optionally, show a success message to the user
    }

    public void setTeamService(TeamService teamService) {
        this.teamService = teamService;
    }

}

