package com.project.guiproject.controllers.TeamPlayers;

import com.project.guiproject.models.Player;
import com.project.guiproject.services.PlayerService;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

import java.sql.SQLException;

import static com.project.guiproject.migration.MigrationInterface.connection;

public class AddPlayerController {

    @FXML
    private ComboBox<Player> playerComboBox;

    @FXML
    private TextField playerNameField;

    @FXML
    private Spinner<Integer> playerAgeField;

    @FXML
    private TextField playerPositionField;

    private PlayerService playerService;


    @FXML
    private void addPlayer(ActionEvent event) throws SQLException {
        playerService = new PlayerService(connection);
        String playerName = playerNameField.getText();
        int playerAge = playerAgeField.getValue();
        String playerPosition = playerPositionField.getText();

        // Add validation logic if needed

        Player player = new Player(playerName, playerAge, playerPosition);

        try {
            playerService.addPlayer(player);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // Optionally, show a success message to the user
    }

    public void populatePlayerComboBox(ObservableList<Player> players) {
        playerComboBox.setItems(players);
    }

    @FXML
    private void playerSelected() {
        Player selectedPlayer = playerComboBox.getSelectionModel().getSelectedItem();
        if (selectedPlayer != null) {
            // Display player details in the input fields
            playerNameField.setText(selectedPlayer.getPlayerName());
            playerAgeField.getValueFactory().setValue(selectedPlayer.getAge());
            playerPositionField.setText(selectedPlayer.getPosition());
        }
    }

    @FXML
    private void updatePlayer(ActionEvent event) throws SQLException {
        playerService = new PlayerService(connection);

        // Get the selected player from the ComboBox
        Player selectedPlayer = playerComboBox.getSelectionModel().getSelectedItem();

        // Check if a player is selected
        if (selectedPlayer == null) {
            System.out.println("No player selected for update.");
            return;
        }

        // Extract player information from the UI fields
        int playerId = selectedPlayer.getId();
        String playerName = playerNameField.getText();
        int playerAge = playerAgeField.getValue();
        String playerPosition = playerPositionField.getText();

        // Create a new Player object with updated information
        Player updatedPlayer = new Player(playerId, playerName, playerAge, playerPosition);

        try {
            // Call the updatePlayer method in PlayerService
            playerService.updatePlayer(updatedPlayer);
            System.out.println("Player updated successfully.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public void setPlayerService(PlayerService playerService) {
        this.playerService = playerService;
    }
}
