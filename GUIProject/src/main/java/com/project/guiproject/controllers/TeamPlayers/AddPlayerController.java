package com.project.guiproject.controllers.TeamPlayers;
import com.project.guiproject.models.Player;
import com.project.guiproject.services.PlayerService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class AddPlayerController {

    @FXML
    private TextField playerNameField;

    @FXML
    private TextField playerAgeField;

    @FXML
    private TextField playerPositionField;

    private PlayerService playerService;

    @FXML
    private void addPlayer(ActionEvent event) {
        String playerName = playerNameField.getText();
        int playerAge = Integer.parseInt(playerAgeField.getText());
        String playerPosition = playerPositionField.getText();

        Player player = new Player(playerName, playerAge, playerPosition);
        try {
            add(player);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void add(Player player) throws SQLException {
        playerService.addPlayer(player);
        // Optionally, show a success message to the user
    }

    public void setPlayerService(PlayerService playerService) {
        this.playerService = playerService;
    }
}
