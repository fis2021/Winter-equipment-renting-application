package org.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StorePageController {
    @FXML
    public void BackAction(javafx.event.ActionEvent back) throws Exception{
        Parent parentPage = FXMLLoader.load(getClass().getClassLoader().getResource("ViewStoresPage.fxml"));
        Scene PageScene = new Scene(parentPage);
        Stage window = (Stage) ((Node) back.getSource()).getScene().getWindow();
        window.setScene(PageScene);
        window.show();
    }
}
