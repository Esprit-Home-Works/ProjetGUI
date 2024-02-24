package com.project.guiproject.controllers.matches;

import com.project.guiproject.helpers.NavigationHelpers;
import com.project.guiproject.models.Match;
import com.project.guiproject.services.MatchService;
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

public class UpsertMatchesController implements Initializable{
    MatchService matchService = new MatchService();

    Match item;

    @FXML
    public Button cancelButton;

    @FXML
    public Button upsertButton;
    @FXML
    public TextField name;
    @FXML
    public TextField description;
    @FXML
    public TextField code;
    @FXML
    public TextField duration;
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
        if (Storage.Match.code != null && !Storage.Match.code.isBlank()) {
            try {
                item = matchService.getByCode(Storage.Match.code);
                name.setText(item.getName());
                description.setText(item.getDescription());
                code.setText(item.getCode());
                duration.setText(String.valueOf(item.getDuration()));
                startDate.setValue(Date.valueOf((item.getStartDate()).toString()).toLocalDate());
                endDate.setValue(Date.valueOf((item.getEndDate().toString())).toLocalDate());

                title.setText("Update Match");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            title.setText("Add Match");
        }
    }

    public void navigateToMatches() {
        Pane ctrl;
        try {
            ctrl = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/project/guiproject/ManageMatches.fxml")));
            nh.navigate(cancelButton,"Gérer les Matches", ctrl);
            clearData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void clearData() {
        Storage.Match.code = null;
        name.clear();
        description.clear();
        code.clear();
        duration.clear();
        startDate.getEditor().clear();
        endDate.getEditor().clear();
    }

    public Boolean validateForm(){
        alert.setAlertType(Alert.AlertType.WARNING);
        alert.setTitle("Invalide");
        if (name.getText().isBlank() || description.getText().isBlank() || code.getText().isBlank() || duration.getText().isBlank() || startDate.getEditor().getText().isBlank() || endDate.getEditor().getText().isBlank() ) {
            alert.setContentText("Veuillez remplir tous les champs");
            alert.show();
            return false;
        }
        return true;

    }

    @FXML
    public void cancelButtonClicked(ActionEvent ignoredEvent) {
        navigateToMatches();
    }

    @FXML
    public void upsertButtonClicked(ActionEvent ignoredEvent) {
        if (validateForm()) {
            if(Storage.Match.code != null) {
                try {
                    matchService.update(new Match(item.getId(), Integer.parseInt(duration.getText()), name.getText(), description.getText(), code.getText(),
                           Date.valueOf(startDate.getValue()), Date.valueOf(endDate.getValue())
                    ));
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setContentText("Match modifié avec success");
                    alert.show();
                } catch (Exception e) {
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setContentText(
                            "Match non modifié, un erreur est survenue.\nmessage d'erreur: " + e.getMessage());
                    alert.show();
                }
            }else {
                try {
                    matchService.add(new Match(Integer.parseInt(duration.getText()), name.getText(), description.getText(), code.getText(), Date.valueOf(startDate.getValue()), Date.valueOf(endDate.getValue())));
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setContentText("Match ajouté avec success");
                    alert.show();
                } catch (Exception e) {
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setContentText(
                            "Match non ajouté, un erreur est survenue.\nmessage d'erreur: " + e.getMessage());
                    alert.show();
                }
            }
        }
    }






}
