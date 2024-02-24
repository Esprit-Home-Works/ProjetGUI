package com.project.guiproject.controllers.team;

import com.project.guiproject.models.Team;
import com.project.guiproject.services.TeamService;
import com.project.guiproject.services.UserService;
import com.project.guiproject.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

public class TeamController implements Initializable {
    TeamService teamService = new TeamService();
    UserService userService = new UserService();
    Preferences prefs = Preferences.userNodeForPackage(getClass());

    TableView.TableViewSelectionModel<Team> selectionModel;

    @FXML
    private TableView<Team> TeamsList;

    @FXML
    public TableColumn<Team, String> id;
    @FXML
    public TableColumn<Team, String> name;
    @FXML
    public TableColumn<Team, String> description;
    @FXML
    public TableColumn<Team, String> leaderId;
    @FXML
    public TableColumn<Team, String> members;
    @FXML
    public TextField nameFilter;
    @FXML
    public ImageView background;

    private ArrayList<Team> data;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
       /* try {
            loadTeams();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Image image1 = new Image("/com/project/guiproject/controllers/team/back.jpg");
        background.setImage(image1);*/
    }

    private void loadTeams() throws SQLException {
        /*data = (ArrayList<Team>) teamService.get();
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        leaderId.setCellValueFactory(new PropertyValueFactory<>("leaderId"));
        members.setCellValueFactory(new PropertyValueFactory<>("members"));
        TeamsList.setItems(FXCollections.observableArrayList(data));
        selectionModel = TeamsList.getSelectionModel();*/
    }

    @FXML
    public void clickItem(MouseEvent event) {
       /* event.consume();
        Team temp = selectionModel.getSelectedItem();
        if (temp != null) {
            // Join the selected team
            User currentUser = getCurrentUser();
            if (currentUser != null) {
                try {
                    teamService.addMember(temp.getId(), currentUser.getId());
                    loadTeams(); // Reload teams after joining
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                System.err.println("Error: Current user not found.");
            }
        }*/
    }




    @FXML
    public void applyNameFilter() {
        /*String filterText = nameFilter.getText();
        // Filter teams based on the name filter text
        ArrayList<Team> filteredData = teamService.filterByName(filterText);
        TeamsList.setItems(FXCollections.observableArrayList(filteredData));
        selectionModel = TeamsList.getSelectionModel();
         */
    }
}
