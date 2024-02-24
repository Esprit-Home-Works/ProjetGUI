package com.project.guiproject.controllers.TeamPlayers;

import com.project.guiproject.helpers.NavigationHelpers;
import com.project.guiproject.models.Team;
import com.project.guiproject.services.TeamListService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class TeamController implements Initializable {
    TeamListService teamService = new TeamListService() {
        @Override
        public Team createTeam(String teamName, String teamDescription, String teamLocation) {
            return null;
        }

        @Override
        public Team getTeamById(int teamId) {
            return null;
        }

        @Override
        public List<Team> getAllTeams() {
            return null;
        }
    };

    Team selectedItem;

    TableView.TableViewSelectionModel<Team> selectionModel;

    @FXML
    private TableView<Team> TeamsList;

    @FXML
    public TableColumn<Team, String> teamName;

    @FXML
    public TableColumn<Team, String> teamDescription;
    @FXML
    public TableColumn<Team, String> teamLocation;

    @FXML
    public Button deleteButton;

    @FXML
    public Button updateButton;

    @FXML
    public Button addButton;

    @FXML
    public Button homeButton;

    @FXML
    public TextField idFilter;

    @FXML
    public TextField nomFilter;

    @FXML
    public ImageView background;

    NavigationHelpers nh = new NavigationHelpers();

    private ObservableList<Team> data;


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            loadTeams();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        initButtons();
        initFilters();
    }

    private void initFilters() {
        // add listener to the filter fields
        //   idFilter.textProperty().addListener((observable, oldValue, newValue) -> filter());
        // nomFilter.textProperty().addListener((observable, oldValue, newValue) -> filter());
    }

    public void filter() {
        data = teamService.filter(idFilter.getText(), nomFilter.getText());
        TeamsList.setItems(data);
    }

    private void initButtons() {
        //deleteButton.setVisible(false);
        //updateButton.setVisible(false);
    }

    private void loadTeams() throws SQLException {
        data = FXCollections.observableArrayList(teamService.get());
        teamName.setCellValueFactory(new PropertyValueFactory<>("teamName"));
        teamDescription.setCellValueFactory(new PropertyValueFactory<>("teamDescription"));
        teamLocation.setCellValueFactory(new PropertyValueFactory<>("teamLocation"));
        TeamsList.setItems(data);
        selectionModel = TeamsList.getSelectionModel();
    }

    @FXML
    public void clickItem(MouseEvent event) {
        event.consume();
        Team temp = selectionModel.getSelectedItem();
        if (temp != null) {
            selectedItem = temp;
            deleteButton.setVisible(true);
            updateButton.setVisible(true);
        }
    }
//
//
    @FXML
    private void goToAddView() {
        try {
            // Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddTeam.fxml"));
            Parent root = loader.load();

            // Create a new scene with the loaded FXML file
            Scene scene = new Scene(root);

            // Get the stage from the current button
            Stage stage = (Stage) addButton.getScene().getWindow();

            // Set the new scene to the stage
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    @FXML
    private void openAddTeamView() {
        try {
            // Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddTeam.fxml"));

            Parent root = loader.<Parent>load();

            // Create a new scene with the loaded FXML file
            Scene scene = new Scene(root);

            // Create a new stage for the scene
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Add Team");

            // Show the stage
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }
}
