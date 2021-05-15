package org.example.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.dizitart.no2.objects.ObjectRepository;
import org.example.Models.User;

public class HomePageController {
    @FXML
    private Button profileButton;
    private static ObjectRepository<User> userRep;
    String loggedUser=LogInController.getLoggedUser();


}
