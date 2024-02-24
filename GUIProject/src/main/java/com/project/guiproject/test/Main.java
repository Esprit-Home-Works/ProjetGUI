package com.project.guiproject.test;

import java.io.IOException;
import java.sql.Date;
import java.util.Objects;

import com.project.guiproject.migration.Init;
import com.project.guiproject.models.Match;
import com.project.guiproject.models.User;
import com.project.guiproject.services.MatchService;
import com.project.guiproject.services.UserService;
import com.project.guiproject.utils.MyDataBase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/*public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        MyDataBase myDataBase = new MyDataBase();
        // MatchService matchService = new MatchService();
        UserService user = new UserService();
        Init initDb = new Init();
        try {
            initDb.run(true);
            // new Date(2021, 1, 1)));
            // matchService.add(new Match(180, "match 2", "test Match",
            // "MT_001", new Date(2021, 1, 1),
            // new Date(2021, 1, 1)));
            // matchService.update(new Match(1, 180, "match Updated", "test Match",
            // "MT_001", new Date(2021, 1, 1),
            // new Date(2021, 1, 1)));
            // matchService.delete(1);
            // Match mt = matchService.getById(2);
            // System.out.println(mt);

            // System.out.println("the list of matches: ");

            // matchService.get().forEach(match -> {
            // System.out.println(match);
            // });

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}*/
/*public class Main extends Application {

    Init initDb = new Init();

    @Override
    public void start(Stage primaryStage) throws IOException {
        try {
            initDb.run(true);
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/project/guiproject/Dashboard.fxml")));
            primaryStage.setTitle("Systeme de gestion des matches");
            primaryStage.getIcons().add(new Image("file:/assets/icon.png"));
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch();
    }
}*/

public class Main extends Application {

    Init initDb = new Init();

    @Override
    public void start(Stage primaryStage) throws IOException {
        try {
            initDb.run(true);
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/project/guiproject/LoginPage.fxml")));
            primaryStage.setTitle("Systeme de gestion des Tournois");
            primaryStage.setHeight(410);
            primaryStage.setWidth(640);
            primaryStage.getIcons().add(new Image("file:/assets/icon.png"));
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch();
    }
}

