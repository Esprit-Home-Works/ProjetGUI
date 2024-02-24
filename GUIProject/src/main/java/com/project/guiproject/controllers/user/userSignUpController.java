package com.project.guiproject.controllers.user;

import com.project.guiproject.helpers.NavigationHelpers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import com.project.guiproject.services.UserService;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class userSignUpController {
    @FXML
    private TextField newUsernameField;

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private TextField newEmailField;

    @FXML
    private Label newErrorMessageLabel;

    @FXML
    private Hyperlink BackLogin;

    private UserService userService;

    NavigationHelpers nh = new NavigationHelpers();

    public userSignUpController() {
        userService = new UserService();
    }

    @FXML
    private void createAccountButtonClicked(ActionEvent event) {
        String newUsername = newUsernameField.getText();
        String newPassword = newPasswordField.getText();
        String newEmail = newEmailField.getText();

        try {
            userService.signup(newUsername, newPassword, newEmail);
            newErrorMessageLabel.setText("Account created successfully.");
        } catch (SQLException e) {
            newErrorMessageLabel.setText("Error creating account.");
            e.printStackTrace();
        }
    }

    @FXML
    private void backToLoginClicked(ActionEvent event) {
        Pane ctrl;
        try {
            ctrl = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/project/guiproject/LoginPage.fxml")));
            nh.navigate(BackLogin, "Login", ctrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
