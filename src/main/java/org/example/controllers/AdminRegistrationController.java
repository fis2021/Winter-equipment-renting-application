package org.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.dizitart.no2.objects.ObjectRepository;
import org.example.Models.User;
import org.example.exceptions.noAdressException;
import org.example.exceptions.noNameException;
import org.example.services.UserServices;

import java.util.Objects;

import static org.dizitart.no2.objects.filters.ObjectFilters.eq;


public class AdminRegistrationController {

    @FXML
    private TextField storename;

    @FXML
    private TextField phone;

    @FXML
    private TextField email;

    @FXML
    private TextField sadress;

    @FXML
    private Text exepMessage;

    private static ObjectRepository<User> userRep;

    public void handleDoneAction(javafx.event.ActionEvent done) throws Exception{
        try {
            String loggedUser=SignUpController.getLoggedUser();
            UserServices.checkNamefield(storename.getText());
            UserServices.checkAdressField(sadress.getText());
            userRep = UserServices.getUserRepository();
            for(User user:userRep.find())
                if(Objects.equals(loggedUser,user.getUsername())){
                    user.setName(storename.getText());
                    user.setEmail(email.getText());
                    user.setPhone(phone.getText());
                    user.setAddress(sadress.getText());
                    userRep.update(eq("username",loggedUser),user);
                }

            Parent logInPage = FXMLLoader.load(getClass().getClassLoader().getResource("LoginPage.fxml"));
            Scene logInPageScene = new Scene(logInPage);
            Stage window = (Stage) ((Node) done.getSource()).getScene().getWindow();
            window.setScene(logInPageScene);
            window.show();
        } catch (noNameException e) {
            exepMessage.setText(e.getMessage());
        } catch (noAdressException e) {
            exepMessage.setText(e.getMessage());
        }
    }

    public String getName(){
        String loggedUser=SignUpController.getLoggedUser();
        userRep = UserServices.getUserRepository();
        for(User user:userRep.find())
            if(Objects.equals(loggedUser,user.getUsername())){
                return user.getName();
            }
        return "";
    }

    public String getPhone(){
        String loggedUser=SignUpController.getLoggedUser();
        userRep = UserServices.getUserRepository();
        for(User user:userRep.find())
            if(Objects.equals(loggedUser,user.getUsername())){
                return user.getPhone();
            }
        return "";
    }

    public String getEmail(){
        String loggedUser=SignUpController.getLoggedUser();
        userRep = UserServices.getUserRepository();
        for(User user:userRep.find())
            if(Objects.equals(loggedUser,user.getUsername())){
                return user.getEmail();
            }
        return "";
    }

    public String getAdress(){
        String loggedUser=SignUpController.getLoggedUser();
        userRep = UserServices.getUserRepository();
        for(User user:userRep.find())
            if(Objects.equals(loggedUser,user.getUsername())){
                return user.getAddress();
            }
        return "";
    }
}
