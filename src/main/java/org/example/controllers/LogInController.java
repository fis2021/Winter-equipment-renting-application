package org.example.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class LogInController {

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private ChoiceBox role;

    @FXML
    private Text exepMessage;

    @FXML
    public void initialize() {
        role.getItems().addAll("Customer", "Admin");
    }

    private String choosedRole;


}
