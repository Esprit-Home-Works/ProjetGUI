package com.project.guiproject.controllers.TeamPlayers;

import com.project.guiproject.models.Player;
import com.project.guiproject.services.PlayerService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.List;

public class SearchPlayerController {

    @FXML
    private TextField searchField;

    @FXML
    private TextField NameSearchTextField;

    @FXML
    private Button searchButton;

    @FXML
    private TableView<Player> playerTableView;

    @FXML
    private TableColumn<Player, String> playerNameColumn;

    @FXML
    private TableColumn<Player, Integer> playerAgeColumn;

    @FXML
    private TableColumn<Player, String> playerPositionColumn;

    private PlayerService.PlayerSearchService playerSearchService;

    public void setPlayerSearchService(PlayerService.PlayerSearchService playerSearchService) {
        this.playerSearchService = playerSearchService;
    }

    @FXML
    void search(ActionEvent event) {
        String searchText = searchField.getText();
        try {
            List<Player> searchResult = playerSearchService.searchPlayersByName(searchText);
            displaySearchResult(searchResult);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQL exception
        }    }

    private void displaySearchResult(List<Player> players) {
        ObservableList<Player> playerObservableList = FXCollections.observableArrayList(players);
        playerTableView.setItems(playerObservableList);
    }

}
