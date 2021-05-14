package org.example.controllers;

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
import org.example.exceptions.*;
import org.example.services.UserServices;

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

    @FXML
    public void handleLogInAction(javafx.event.ActionEvent LogIn) throws Exception {
        try {
            UserServices.verifyUserCredentials(username.getText(),password.getText(),(String)role.getValue());
            choosedRole=(String) role.getValue();

            if(choosedRole.equals("Admin")){
                Parent administrationPage = FXMLLoader.load(getClass().getClassLoader().getResource("AdministrationPage.fxml"));
                Scene administrationPageScene = new Scene(administrationPage);
                Stage window = (Stage) ((Node) LogIn.getSource()).getScene().getWindow();;
                window.setScene(administrationPageScene);
                window.show();

            }
            else{
                Parent homePage = FXMLLoader.load(getClass().getClassLoader().getResource("HomePage.fxml"));
                Scene homePageScene = new Scene(homePage);
                Stage window = (Stage) ((Node) LogIn.getSource()).getScene().getWindow();;
                window.setScene(homePageScene);
                window.show();
            }
        } catch (UsernameNotFoundException e) {
            exepMessage.setText(e.getMessage());
        } catch (InvalidRoleException e){
            exepMessage.setText(e.getMessage());
        } catch (InvalidPasswordException e){
            exepMessage.setText(e.getMessage());
        }
    }


}
