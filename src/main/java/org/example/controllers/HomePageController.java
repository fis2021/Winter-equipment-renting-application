package org.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.dizitart.no2.objects.ObjectRepository;
import org.example.Models.User;

public class HomePageController {
    @FXML
    private Button profileButton;
    @FXML
    private Button viewStoresButton;
    private static ObjectRepository<User> userRep;
    String loggedUser=LogInController.getLoggedUser();

    @FXML
    public void handleMyProfileAction() throws Exception{
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("MyProfileCustomerPage.fxml"));
        Parent parent = loader.load();
        //Parent parent = loader.load(getClass().getClassLoader().getResource("MyProfileAdminPage.fxml"));
        Scene scene = new Scene(parent);
        MyProfileCustomerController controller = loader.getController();
        controller.set();
        //Stage stage = (Stage) (Stage) ((Node) myProfile.getSource()).getScene().getWindow();
        Stage stage = (Stage) (profileButton.getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void handleViewStoresAction() throws Exception{
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("ViewStoresPage.fxml"));
        Parent parent = loader.load();
        //Parent parent = loader.load(getClass().getClassLoader().getResource("MyProfileAdminPage.fxml"));
        Scene scene = new Scene(parent);
        ViewStoresController controller = loader.getController();
        //controller();
        //Stage stage = (Stage) (Stage) ((Node) myProfile.getSource()).getScene().getWindow();
        Stage stage = (Stage) (viewStoresButton.getScene().getWindow());
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

}
