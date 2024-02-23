package com.project.guiproject.controllers.tournament;

import com.project.guiproject.helpers.NavigationHelpers;
import com.project.guiproject.models.Tournament;
import com.project.guiproject.services.TournamentService;
import com.project.guiproject.test.Storage;
import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.SendEmailRequest;
import com.resend.services.emails.model.SendEmailResponse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Objects;
import java.util.ResourceBundle;

public class UpsertTournamentController implements Initializable{
    TournamentService tournamentService = new TournamentService();

    Tournament item;

    @FXML
    public Button cancelButton;

    @FXML
    public Button upsertButton;
    @FXML
    public TextField name;
    @FXML
    public TextField description;
    @FXML
    public TextField maxTeams; 
    @FXML
    public DatePicker startDate;
    @FXML
    public DatePicker endDate;
    @FXML
    public Label title;
    NavigationHelpers nh = new NavigationHelpers();

    Alert alert = new Alert(Alert.AlertType.NONE);


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        if (Storage.Tournament.id != null && !Storage.Tournament.id.isBlank()) {
            try {
                item = tournamentService.getById(Integer.parseInt(Storage.Tournament.id));
                name.setText(item.getName());
                description.setText(item.getDescription());

                maxTeams.setText(String.valueOf(item.getMaxTeams()));
                startDate.setValue(Date.valueOf((item.getStartDate()).toString()).toLocalDate());
                endDate.setValue(Date.valueOf((item.getEndDate().toString())).toLocalDate());

                title.setText("Update Tournament");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            title.setText("Add Tournament");
        }
    }

    public void navigateToTournaments() {
        Pane ctrl;
        try {
            ctrl = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/project/guiproject/ManageTournament.fxml")));
            nh.navigate(cancelButton,"Gérer les Tournaments", ctrl);
            clearData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void clearData() {
        Storage.Tournament.id = null;
        name.clear();
        description.clear();
        maxTeams.clear();

        startDate.getEditor().clear();
        endDate.getEditor().clear();
    }

    public Boolean validateForm(){
        alert.setAlertType(Alert.AlertType.WARNING);
        alert.setTitle("Invalide");
        if (name.getText().isBlank() || description.getText().isBlank() ||  maxTeams.getText().isBlank() || startDate.getEditor().getText().isBlank() || endDate.getEditor().getText().isBlank() ) {
            alert.setContentText("Veuillez remplir tous les champs");
            alert.show();
            return false;
        }
        return true;

    }

    @FXML
    public void cancelButtonClicked(ActionEvent ignoredEvent) {
        navigateToTournaments();
    }

    @FXML
    public void upsertButtonClicked(ActionEvent ignoredEvent) {
        if (validateForm()) {
            if(Storage.Tournament.id != null) {
                try {
                    tournamentService.update(new Tournament(  name.getText(),
                            Date.valueOf(startDate.getValue()).toLocalDate(), Date.valueOf(endDate.getValue()).toLocalDate(), description.getText(),Integer.parseInt(maxTeams.getText())
                    ));
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setContentText("Tournament modifié avec success");
                    alert.show();
                } catch (Exception e) {
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setContentText(
                            "Tournament non modifié, un erreur est survenue.\nmessage d'erreur: " + e.getMessage());
                    alert.show();
                }
            }else {
                try {
                    tournamentService.add(new Tournament(  name.getText(),
                            Date.valueOf(startDate.getValue()).toLocalDate(), Date.valueOf(endDate.getValue()).toLocalDate(), description.getText(),Integer.parseInt(maxTeams.getText())
                    ));
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setContentText("Tournament ajouté avec success");
                    alert.show();
                } catch (Exception e) {
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setContentText(
                            "Tournament non ajouté, un erreur est survenue.\nmessage d'erreur: " + e.getMessage());
                    alert.show();
                }
            }
        }
    }






}
