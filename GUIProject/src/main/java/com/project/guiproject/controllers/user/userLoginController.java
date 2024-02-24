package com.project.guiproject.controllers.user;

import com.project.guiproject.controllers.HomeController;
import com.project.guiproject.helpers.NavigationHelpers;
import com.project.guiproject.models.User;
import com.project.guiproject.services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.sql.SQLException;
import java.util.prefs.Preferences;

import java.io.IOException;
import java.util.Objects;

public class userLoginController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorMessageLabel;

    private UserService userService;

    @FXML
    private Hyperlink GoSignUp;

    @FXML
    private AnchorPane rootPane;

    NavigationHelpers nh = new NavigationHelpers();

    private static final String USER_KEY = "loggedInUser";

    public userLoginController() {
        userService = new UserService();
    }

    @FXML
    private void loginButtonClicked(ActionEvent event) throws SQLException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        UserService userService = new UserService();
        User user = userService.login(username, password);

        if (user != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/project/guiproject/HomePage.fxml"));
                AnchorPane homePane = loader.load();
                HomeController homeController = loader.getController();
                homeController.initializeButtons(user.getRole());
                rootPane.getChildren().setAll(homePane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Show error message for invalid credentials
        }
    }


    @FXML
    private void createAccountClicked(ActionEvent event) {
        Pane ctrl;
        try {
            ctrl = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/project/guiproject/SignUpPage.fxml")));
            nh.navigate(GoSignUp, "Login", ctrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveLoggedInUser(User user) {
        Preferences prefs = Preferences.userNodeForPackage(userLoginController.class);
        prefs.put(USER_KEY, String.valueOf(user.getId()));
        prefs.put("role", user.getRole());
    }
}
