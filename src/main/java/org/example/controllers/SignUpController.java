package org.example.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.dizitart.no2.objects.Id;
import org.example.Models.User;
import org.example.exceptions.EmptyFieldsException;
import org.example.exceptions.InvalidRoleException;
import org.example.exceptions.UsernameAlreadyExistsException;
import org.example.services.UserServices;


import java.io.IOException;

public class SignUpController {
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

    private static String loggedUser;
    private String choosedRole;

    public static String getLoggedUser() {
        return loggedUser;
    }

    @FXML
    public void handleSignUpAction(javafx.event.ActionEvent SignUp) throws Exception {
        try {
            UserServices.addUser(username.getText(), password.getText(), (String)role.getValue());
            choosedRole=(String) role.getValue();
            loggedUser=UserServices.getLoggedUser(username.getText());

            if(choosedRole.equals("Admin")){
                Parent adminPage = FXMLLoader.load(getClass().getClassLoader().getResource("AdminRegistrationPage.fxml"));
                Scene adminPageScene = new Scene(adminPage);
                Stage window = (Stage) ((Node) SignUp.getSource()).getScene().getWindow();;
                window.setScene(adminPageScene);
                window.show();

            }
            else{
                Parent adminPage = FXMLLoader.load(getClass().getClassLoader().getResource("CustomerRegistrationPage.fxml"));
                Scene adminPageScene = new Scene(adminPage);
                Stage window = (Stage) ((Node) SignUp.getSource()).getScene().getWindow();;
                window.setScene(adminPageScene);
                window.show();
            }
        } catch (UsernameAlreadyExistsException e) {
            exepMessage.setText("Username already exists!");
        } catch (EmptyFieldsException e){
               exepMessage.setText("There are empty fields, please complete!");
        }
    }
}
