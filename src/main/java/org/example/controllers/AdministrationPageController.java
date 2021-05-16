package org.example.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import org.dizitart.no2.objects.Id;
import org.dizitart.no2.objects.ObjectRepository;
import org.example.Models.User;
import javafx.scene.Parent;


public class AdministrationPageController {
    @FXML
    private Button profileButton;
    private static ObjectRepository<User> userRep;
    String loggedUser=LogInController.getLoggedUser();

    @FXML
    public void handleMyProfileAction() throws Exception{
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("MyProfileAdminPage.fxml"));
        Parent parent = loader.load();
        //Parent parent = loader.load(getClass().getClassLoader().getResource("MyProfileAdminPage.fxml"));
        Scene scene = new Scene(parent);
        MyProfileAdminController controller = loader.getController();
        controller.set();
        //Stage stage = (Stage) (Stage) ((Node) myProfile.getSource()).getScene().getWindow();
        Stage stage = (Stage) (profileButton.getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void LogoffAction(javafx.event.ActionEvent out) throws Exception{
        Parent parentPage = FXMLLoader.load(getClass().getClassLoader().getResource("LoginPage.fxml"));
        Scene PageScene = new Scene(parentPage);
        Stage window = (Stage) ((Node) out.getSource()).getScene().getWindow();
        window.setScene(PageScene);
        window.show();
    }

    @FXML
    public void manageItemsAction(javafx.event.ActionEvent act) throws Exception{
        Parent parentPage = FXMLLoader.load(getClass().getClassLoader().getResource("ManageItemsPage.fxml"));
        Scene PageScene = new Scene(parentPage);
        Stage window = (Stage) ((Node) act.getSource()).getScene().getWindow();
        window.setScene(PageScene);
        window.show();
    }
}
