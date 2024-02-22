package com.project.guiproject.controllers.TeamPlayers;

import com.project.guiproject.models.Team;
import com.project.guiproject.services.TeamService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.SQLException;

import static com.project.guiproject.migration.MigrationInterface.connection;

public class AddTeamController {

    @FXML
    private TextField teamNameField;
    @FXML
    private TextField teamDescriptionField;

    @FXML
    private TextField teamLocationField;

    private TeamService teamService;

    @FXML
    private void addTeam(ActionEvent event) throws SQLException {
        String teamName = teamNameField.getText();
        teamService = new TeamService(connection);
        String teamLocation = teamLocationField.getText();
        String teamDescription = teamDescriptionField.getText();

        // Add validation logic if needed

        Team team = new Team( teamName, teamDescription, teamLocation);

        try {

            teamService.addTeam(team);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // Optionally, show a success message to the user
    }

    public void setTeamService(TeamService teamService) {
        this.teamService = teamService;
    }

}