package com.project.guiproject.test;

import com.project.guiproject.controllers.TeamPlayers.AddPlayerController;
import com.project.guiproject.controllers.TeamPlayers.AddTeamController;
import com.project.guiproject.migration.Init;
import com.project.guiproject.utils.MyDataBase;
import com.project.guiproject.utils.TableCreator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

public class Main extends Application {

    Init initDb = new Init();

    @Override
    public void start(Stage primaryStage) throws IOException {
        try {
            initDb.run(true);

            FXMLLoader manageMatchesLoader = new FXMLLoader(getClass().getResource("/ManageMatches.fxml"));
            Parent manageMatchesRoot = manageMatchesLoader.load();
            Scene manageMatchesScene = new Scene(manageMatchesRoot);
            manageMatchesScene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm()); // Add CSS if needed

            FXMLLoader addPlayerLoader = new FXMLLoader(getClass().getResource("/AddPlayer.fxml"));
            Parent addPlayerRoot = addPlayerLoader.load();
            AddPlayerController addPlayerController = addPlayerLoader.getController(); // Get the controller to interact with the interface

            FXMLLoader addTeamLoader = new FXMLLoader(getClass().getResource("/AddTeam.fxml"));
            Parent addTeamRoot = addTeamLoader.load();
            AddTeamController addTeamController = addTeamLoader.getController(); // Get the controller to interact with the interface

            primaryStage.setTitle("Systeme de gestion des matches");
            primaryStage.getIcons().add(new Image("file:/assets/icon.png"));

            // Initially show the Manage Matches scene
            primaryStage.setScene(manageMatchesScene);
            primaryStage.show();

            // Example of switching scenes programmatically
            // addPlayerButton.setOnAction(event -> primaryStage.setScene(new Scene(addPlayerRoot)));
            // addTeamButton.setOnAction(event -> primaryStage.setScene(new Scene(addTeamRoot)));

            // Example of passing data between scenes
            // addPlayerController.setPlayerService(playerService);
            // addTeamController.setTeamService(teamService);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle FXML loading error
        } catch (Exception e) {
            e.printStackTrace();
            // Handle other errors
        }

        try {
            Connection connection = MyDataBase.getInstace().getConnection();
            TableCreator tableCreator = new TableCreator(connection);
            tableCreator.createTables();
            System.out.println("Tables created successfully.");
        } catch (Exception e) {
            System.out.println("Error creating tables: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
