package com.project.guiproject.test;

import com.project.guiproject.controllers.TeamPlayers.AddPlayerController;
import com.project.guiproject.migration.Init;
import com.project.guiproject.models.Player;
import com.project.guiproject.services.PlayerService;
import com.project.guiproject.utils.MyDataBase;
import com.project.guiproject.utils.TableCreator;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

public class Main extends Application {

    Init initDb = new Init();


    @Override
    public void start(Stage primaryStage) throws IOException {


        // Add Player
//        try {
//            Connection connection = MyDataBase.getInstace().getConnection();
//            TableCreator tableCreator = new TableCreator(connection);
//            tableCreator.createTables();
//            System.out.println("Tables created successfully.");
//
//            FXMLLoader addPlayerLoader = new FXMLLoader(getClass().getResource("/AddPlayer.fxml"));
//            Parent addPlayerRoot = addPlayerLoader.load();
//            AddPlayerController addPlayerController = addPlayerLoader.getController();
//            PlayerService playerService = new PlayerService(connection);
//
//            // Pass the player service to the controller
//            addPlayerController.setPlayerService(playerService);
//
//            primaryStage.setTitle("Add Player");
//            primaryStage.setScene(new Scene(addPlayerRoot, 500, 250));
//            primaryStage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//            // Handle FXML loading error
//        } catch (Exception e) {
//            e.printStackTrace();
//            // Handle other errors
//        }
//    }
//    End add player





/////////////////////////////// Add Team////////////////////////////////////////////////////
//        try {
//            Connection connection = MyDataBase.getInstace().getConnection();
//            TableCreator tableCreator = new TableCreator(connection);
//            tableCreator.createTables();
//            System.out.println("Tables created successfully.");
//
//            FXMLLoader manageMatchesLoader = new FXMLLoader(getClass().getResource("/AddTeam.fxml"));
//            Parent manageMatchesRoot = manageMatchesLoader.load();
//            primaryStage.setTitle("Team Players");
//            primaryStage.setScene(new Scene(manageMatchesRoot, 500, 250));
//
//            primaryStage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//            // Handle FXML loading error
//        } catch (Exception e) {
//            e.printStackTrace();
//            // Handle other errors
//        }
/////////////////////////////// End Add Team//////////////////////////////////////////

///////////////////////////////// Update Team ////////////////////////////////////
// try {
//            Connection connection = MyDataBase.getInstace().getConnection();
//            TableCreator tableCreator = new TableCreator(connection);
//            tableCreator.createTables();
//            System.out.println("Tables created successfully.");
//
//            FXMLLoader manageMatchesLoader = new FXMLLoader(getClass().getResource("/UpdateTeam.fxml"));
//            Parent manageMatchesRoot = manageMatchesLoader.load();
//            AddTeamController controller = manageMatchesLoader.getController();
//            TeamService teamService = new TeamService(connection);
//
//            ObservableList<Team> teams = FXCollections.observableArrayList(teamService.getAllTeams());
//
//            controller.populateTeamComboBox(teams);
//
//
//            primaryStage.setTitle("Team Players");
//
//            primaryStage.setScene(new Scene(manageMatchesRoot, 500, 250));
//
//            primaryStage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//            // Handle FXML loading error
//        } catch (Exception e) {
//            e.printStackTrace();
//            // Handle other errors
//        }
        // End Update Team

///////////////////////////////// Delete Team  /////////////////////////////////
//        try {
//            Connection connection = MyDataBase.getInstace().getConnection();
//            TableCreator tableCreator = new TableCreator(connection);
//            tableCreator.createTables();
//            System.out.println("Tables created successfully.");
//
//            FXMLLoader deleteTeamLoader = new FXMLLoader(getClass().getResource("/DeleteTeam.fxml"));
//            Parent deleteTeamRoot = deleteTeamLoader.<Parent>load();
//            AddTeamController controller = deleteTeamLoader.getController();
//            TeamService teamService = new TeamService(connection);
//
//            ObservableList<Team> teams = FXCollections.observableArrayList(teamService.getAllTeams());
//
//            controller.populateTeamComboBox(teams);
//
//            primaryStage.setTitle("Delete Team");
//            primaryStage.setScene(new Scene(deleteTeamRoot, 400, 200));
//            primaryStage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//            // Handle FXML loading error
//        } catch (SQLException e) {
//            e.printStackTrace();
//            // Handle SQL error
//        } catch (Exception e) {
//            e.printStackTrace();
//            // Handle other errors
//        }
/////////////////////////////////  End delete TEam /////////////////////////////////


//        Extra code

//        try {
//            Scene manageMatchesScene = new Scene(manageMatchesRoot);
//            manageMatchesScene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm()); // Add CSS if needed
//
//            FXMLLoader addPlayerLoader = new FXMLLoader(getClass().getResource("/AddPlayer.fxml"));
//            Parent addPlayerRoot = addPlayerLoader.load();
//            AddPlayerController addPlayerController = addPlayerLoader.getController(); // Get the controller to interact with the interface
//
//            FXMLLoader addTeamLoader = new FXMLLoader(getClass().getResource("/AddTeam.fxml"));
//            Parent addTeamRoot = addTeamLoader.load();
//            AddTeamController addTeamController = addTeamLoader.getController(); // Get the controller to interact with the interface
//
//            primaryStage.setTitle("Systeme de gestion des matches");
//            primaryStage.getIcons().add(new Image("file:/assets/icon.png"));
//
//
//            primaryStage.setScene(manageMatchesScene);
//            primaryStage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//            // Handle FXML loading error
//        } catch (Exception e) {
//            e.printStackTrace();
//            // Handle other errors
//        }

/////////////////////////////////   STart update player  /////////////////////////////////
        try {
            Connection connection = MyDataBase.getInstace().getConnection();
            TableCreator tableCreator = new TableCreator(connection);
            tableCreator.createTables();
            System.out.println("Tables created successfully.");

            FXMLLoader updatePlayerLoader = new FXMLLoader(getClass().getResource("/UpdatePlayer.fxml"));
            Parent updatePlayerRoot = updatePlayerLoader.load();
            AddPlayerController updatePlayerController = updatePlayerLoader.getController();
            PlayerService playerService = new PlayerService(connection);

            // Populate the player ComboBox
            ObservableList<Player> players = FXCollections.observableArrayList(playerService.getAllPlayers());
            updatePlayerController.populatePlayerComboBox(players);

            primaryStage.setTitle("Update Player");
            primaryStage.setScene(new Scene(updatePlayerRoot, 500, 250));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle FXML loading error
        } catch (Exception e) {
            e.printStackTrace();
            // Handle other errors
        }
/////////////////////////////////   END update player  /////////////////////////////////

    }


    ///////////////////////////// Search Player  /////////////////////
//    private static void launchSearchPlayer(Stage primaryStage) {
//        try {
//            // Load search player UI
//            FXMLLoader searchPlayerLoader = new FXMLLoader(Main.class.getResource("/SearchPlayer.fxml"));
//            Parent searchPlayerRoot = searchPlayerLoader.load();
//            SearchPlayerController searchPlayerController = searchPlayerLoader.getController();
//            PlayerService playerService = new PlayerService(connection);
//            // For example:
//            // searchPlayerController.setPlayerService(playerService);
//
//            // Set up search player stage
//            Stage searchPlayerStage = new Stage();
//            searchPlayerStage.initOwner(primaryStage);
//            searchPlayerStage.setTitle("Search Player");
//            searchPlayerStage.setScene(new Scene(searchPlayerRoot, 500, 300));
//            searchPlayerStage.show();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            // Handle FXML loading error
//        } catch (Exception e) {
//            e.printStackTrace();
//            // Handle other errors
//        }
//    }
///////////////////////////////// End Search Player ///////////////////////

    public static void main(String[] args) {
        launch(args);
    }


    // Method to fetch data from the database (replace with your actual data retrieval logic)
//    private List<Team> fetchDataFromDatabase() {
//
//        // Create an instance of TeamServiceImpl
//        Connection connection = MyDataBase.getInstace().getConnection();
//        OldTeamServiceImpl teamService = new OldTeamServiceImpl(connection);
//
//        // Use the getAllTeams method to retrieve all teams from the database
//        return teamService.getAllTeams();
//        // Retrieve data from the database and return a list of Team objects
//    }
//
//    public static void addPlayer(String name, int age, String position) {
//        try {
//            Connection connection = MyDataBase.getInstace().getConnection();
//            PlayerService playerService = new PlayerService(connection);
//
//            // Create a new player
//            Player player = new Player(name, age, position);
//
//            // Add the player
//            playerService.addPlayer(player);
//            System.out.println("Player added successfully.");
//
//            // Close the connection
//            connection.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//            // Handle errors
//        }
//    }
}