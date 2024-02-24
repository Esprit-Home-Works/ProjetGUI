package com.project.guiproject.controllers.tournament;

import com.project.guiproject.helpers.NavigationHelpers;
import com.project.guiproject.models.Tournament;
import com.project.guiproject.services.TournamentService;
import com.project.guiproject.test.Storage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class tournamentController implements Initializable {
    public Button homeButton;
    TournamentService tournamentService = new TournamentService();

    Tournament selectedItem;

    TableView.TableViewSelectionModel<Tournament> selectionModel;

    @FXML
    private TableView<Tournament> TournamentsList;

    @FXML
    public TableColumn<Tournament, String> name;
    @FXML
    public TableColumn<Tournament, String> description;
    @FXML
    public TableColumn<Tournament, String> id;
    @FXML
    public TableColumn<Tournament, String> startDate;
    @FXML
    public TableColumn<Tournament, String> endDate;
    @FXML
    public TableColumn<Tournament, String> maxTeams;
    @FXML
    public Button deleteButton;
    @FXML
    public Button updateButton;
    @FXML
    public Button addButton;
    @FXML
    public TextField idFilter;
    @FXML
    public ImageView background;

    NavigationHelpers nh = new NavigationHelpers();

    private ArrayList<Tournament> data;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            loadTournaments();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        initButtons();
        initFilters();
        Image image1 = new Image("C:\\Users\\YassineSBOUI\\IdeaProjects\\ProjetGUI\\GUIProject\\src\\main\\java\\com\\project\\guiproject\\controllers\\tournament\\back.jpg");
        background.setImage(image1);
    }

    private void initFilters() {
        // add listener to the filter fields
        idFilter.textProperty().addListener((observable, oldValue, newValue) -> filter());

    }

    public void filter() {
        data = tournamentService.filter(idFilter.getText());
        TournamentsList.setItems(FXCollections.observableArrayList(data));
    }
    @FXML
    public void clearIdFilter() {
        idFilter.clear();
    }

    private void initButtons() {
        deleteButton.setVisible(false);
        updateButton.setVisible(false);
    }

    private void loadTournaments() throws SQLException {
        data = (ArrayList<Tournament>) tournamentService.get();
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        startDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        maxTeams.setCellValueFactory(new PropertyValueFactory<>("maxTeams"));
        TournamentsList.setItems(FXCollections.observableArrayList(data));
        selectionModel = TournamentsList.getSelectionModel();
    }

    @FXML
    public void clickItem(MouseEvent event) {
        event.consume();
        Tournament temp = selectionModel.getSelectedItem();
        if (temp != null) {
            selectedItem = temp;
            deleteButton.setVisible(true);
            updateButton.setVisible(true);
        }
    }
    @FXML
    public void deleteButtonClicked(ActionEvent ignoredEv) {
        if (selectedItem != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Supprimer classe");
            alert.setContentText("Êtes-vous sûr de vouloir supprimer cette classe ("+ selectedItem.getName() + ") ?");
            alert.showAndWait().filter(response -> response == ButtonType.OK).ifPresent(response -> {
                try {
                    tournamentService.delete(selectedItem.getId());
                    loadTournaments();
                    selectionModel.clearSelection();
                    deleteButton.setVisible(false);
                    updateButton.setVisible(false);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        }
    }
    @FXML
    public void updateButtonClicked(ActionEvent ignoredEv) {
        if (selectedItem != null) {
            Pane ctrl;
            try {
                Storage.Tournament.id = String.valueOf(selectedItem.getId());
                ctrl = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/project/guiproject/UpsertTournament.fxml")));
                nh.navigate(addButton, "Modifier classe", ctrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @FXML
    public void addButtonClicked(ActionEvent ignoredEv) {
        Pane ctrl;
        try {
            ctrl = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/project/guiproject/UpsertTournament.fxml")));
            nh.navigate(addButton, "Ajouter classe", ctrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goHome(ActionEvent ignoredEv) {
        Pane ctrl;
        try {
            ctrl = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/project/guiproject/Dashboard.fxml")));
            nh.navigate(homeButton, "Dashboard", ctrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
