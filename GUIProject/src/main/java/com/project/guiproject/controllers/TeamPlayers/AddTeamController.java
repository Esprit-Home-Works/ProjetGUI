package com.project.guiproject.controllers.TeamPlayers;

import com.project.guiproject.models.Team;
import com.project.guiproject.services.TeamService;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.sql.SQLException;

import static com.project.guiproject.migration.MigrationInterface.connection;

public class AddTeamController {

    @FXML

    private ComboBox<Team> teamComboBox;
    @FXML
    private TextField teamNameField;
    @FXML
    private TextField teamDescriptionField;

    @FXML
    private TextField teamLocationField;

    private TeamService teamService;

    @FXML
    private void addTeam(ActionEvent event) throws SQLException {
        teamService = new TeamService(connection);
        String teamName = teamNameField.getText();
        String teamLocation = teamLocationField.getText();
        String teamDescription = teamDescriptionField.getText();

        // Add validation logic if needed

        Team team = new Team(teamName, teamDescription, teamLocation);

        try {

            teamService.addTeam(team);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // Optionally, show a success message to the user
    }
    public void populateTeamComboBox(ObservableList<Team> teams) {
        teamComboBox.setItems(teams);
    }

    @FXML

    private void teamSelected(ActionEvent event) {

        Team selectedTeam = teamComboBox.getSelectionModel().getSelectedItem();

        if (selectedTeam != null) {
            teamNameField.setText(selectedTeam.getTeamName());
            teamDescriptionField.setText(selectedTeam.getTeamDescription());
            teamLocationField.setText(selectedTeam.getTeamlocation());
        }
    }


    @FXML

    private void updateTeam(ActionEvent event) throws SQLException {

        teamService = new TeamService(connection);

        int teamId = teamComboBox.getSelectionModel().getSelectedItem().getId();

        String teamName = teamNameField.getText();
        String teamDescription = teamDescriptionField.getText();
        String teamLocation = teamLocationField.getText();


        // Add validation logic if needed

        Team team = new Team(teamId, teamName, teamDescription, teamLocation);


        try {
            teamService.updateTeam(team);

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

        // Optionally, show a success message to the user

    }

    @FXML

    private void deleteTeam(ActionEvent event) throws SQLException {
        teamService = new TeamService(connection);

        Team selectedTeam = teamComboBox.getSelectionModel().getSelectedItem();

        if (selectedTeam != null) {
            int teamId = selectedTeam.getId();

            try {
                teamService.deleteTeam(teamId);
                // Optionally, show a success message to the user
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public void setTeamService(TeamService teamService) {
        this.teamService = teamService;
    }

}