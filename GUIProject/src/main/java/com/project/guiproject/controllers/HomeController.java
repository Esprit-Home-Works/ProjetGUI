package com.project.guiproject.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HomeController {

    @FXML
    private AnchorPane rootPane;

    private Map<String, String> buttonPaths = new HashMap<>();

    public void initializeButtons(String role) {
        System.out.println("Role: " + role);
        setBackground(); // Set background image

        switch (role) {
            case "admin":
                addButton("Gestion des tournois", "/com/project/guiproject/ManageTournament.fxml");
                addButton("Gestion des match", "/com/project/guiproject/ManageMatches.fxml");
                break;
            case "user":
                addButton("Voir les Tournois", "/com/project/guiproject/Tournaments.fxml");
                addButton("Rejoindre une equipe", "/com/project/guiproject/JoinTeamPage.fxml");
                addButton("Créer une equipe", "/com/project/guiproject/CreateTeamPage.fxml");
                break;
            default:
                // Handle unknown role or hide all buttons
                break;
        }
    }

    private void addButton(String buttonText, String path) {
        Button button = new Button(buttonText);
        button.setStyle("-fx-background-color: #42b15d; -fx-text-fill: white; -fx-font-size: 16px; -fx-pref-width: 200px; -fx-pref-height: 40px; -fx-padding: 10px; -fx-margin-bottom: 10px;");
        button.setOnAction(this::goToPage);
        buttonPaths.put(buttonText, path);

        // Positioning buttons vertically
        double buttonY = rootPane.getChildren().size() * 50; // Adjust this value for spacing
        button.setLayoutY(buttonY);

        rootPane.getChildren().add(button);
    }

    private void goToPage(ActionEvent event) {
        Button button = (Button) event.getSource();
        String path = buttonPaths.get(button.getText());

        if (path != null) {
            try {
                System.out.println("Loading: " + path);

                // Check if rootPane is null
                if (rootPane == null) {
                    System.out.println("Error: rootPane is null");
                    return;
                }

                // Attempt to load the FXML file
                Pane ctrl = FXMLLoader.load(getClass().getResource(path));

                // Check if ctrl is null
                if (ctrl == null) {
                    System.out.println("Error: Pane is null");
                    return;
                }

                // Set the loaded FXML file as the children of rootPane
                rootPane.getChildren().setAll(ctrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void setBackground() {
        // Load the background image
        Image backgroundImage = new Image("file:///C:/Users/YassineSBOUI/IdeaProjects/ProjetGUI/GUIProject/src/main/java/com/project/guiproject/controllers/matches/back.jpg");

        // Create a background image
        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,    // No repeat
                BackgroundRepeat.NO_REPEAT,    // No repeat
                BackgroundPosition.CENTER,     // Center the background
                new BackgroundSize(
                        BackgroundSize.AUTO,       // Auto width
                        BackgroundSize.AUTO,       // Auto height
                        false,                     // No width relative to the scene
                        false,                     // No height relative to the scene
                        true,                      // Width and height are proportional
                        true                       // Width and height are proportional
                )
        );

        // Set the background image to the rootPane
        rootPane.setBackground(new javafx.scene.layout.Background(background));
    }
}
