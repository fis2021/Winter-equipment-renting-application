package org.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class WelcomeController {

    @FXML
    public void handleWSignUpButton(javafx.event.ActionEvent wSignUp) throws IOException {
        Parent signUpPageParent = FXMLLoader.load(getClass().getClassLoader().getResource("SignUpPage.fxml"));
        Scene signUpPageScene = new Scene(signUpPageParent);

        Stage window = (Stage)((Node)wSignUp.getSource()).getScene().getWindow();
        window.setScene(signUpPageScene);
        window.show();
    }

    @FXML
    public void handleWLogInButton(javafx.event.ActionEvent wLogin) throws IOException {
        Parent logInPageParent = FXMLLoader.load(getClass().getClassLoader().getResource("LoginPage.fxml"));
        Scene logInPageScene = new Scene(logInPageParent);

        Stage window = (Stage)((Node)wLogin.getSource()).getScene().getWindow();
        window.setScene(logInPageScene);
        window.show();
    }

}
