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

import static org.example.services.FileSystemServices.getPathToFile;

public class MyProfileAdminController {
    @FXML
    private Text name;
    @FXML
    private Text phone;
    @FXML
    private Text email;
    @FXML
    private Text adress;

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
                this.setAdress(user.getAddress());
            }
    }

    @FXML
    public void handleEditAction(javafx.event.ActionEvent edit) throws Exception{
        Parent parentPage = FXMLLoader.load(getClass().getClassLoader().getResource("EditProfileAdminPage.fxml"));
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

    public void setAdress(String adress) {
        this.adress.setText(adress);
    }

    public Text getAdress() {
        return adress;
    }
}
