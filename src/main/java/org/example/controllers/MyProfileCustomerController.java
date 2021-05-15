package org.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.example.Models.User;
import org.example.services.UserServices;

import java.util.Objects;

public class MyProfileCustomerController {
    @FXML
    private Text name;
    @FXML
    private Text phone;
    @FXML
    private Text email;

    private static ObjectRepository<User> userRep;
    private static String loggedUser;

    private static Nitrite database;

    public void set(){
        loggedUser=LogInController.getLoggedUser();
        userRep = UserServices.getUserRepository();
        for(User user:userRep.find())
            if(Objects.equals(loggedUser,user.getUsername())){
                this.setName(user.getName());
                this.setPhone(user.getPhone());
                this.setEmail(user.getEmail());
            }
    }

    @FXML
    public void handleEditAction(javafx.event.ActionEvent edit) throws Exception{
        Parent parentPage = FXMLLoader.load(getClass().getClassLoader().getResource("EditProfileCustomerPage.fxml"));
        Scene PageScene = new Scene(parentPage);
        Stage window = (Stage) ((Node) edit.getSource()).getScene().getWindow();;
        window.setScene(PageScene);
        window.show();
    }

    public void setName(String name) {
        this.name.setText(name);
    }

    public Text getName() {
        return name;
    }

    public void setPhone(String phone) {
        this.phone.setText(phone);
    }

    public Text getPhone() {
        return phone;
    }

    public void setEmail(String email) {
        this.email.setText(email);
    }

    public Text getEmail() {
        return email;
    }
}
