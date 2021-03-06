package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.services.FileSystemServices;
import org.example.services.UserServices;

import java.nio.file.Files;
import java.nio.file.Path;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        initDirectory();
        UserServices.initDatabase();
        //adminservice.initdb
        //customer.initdb
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Welcome.fxml"));
        primaryStage.setTitle("Winter Equipment Rental Application");
        primaryStage.setScene(new Scene(root, 521, 352.0));
        primaryStage.show();
    }

    private void initDirectory() {
        Path applicationHomePath = FileSystemServices.APPLICATION_HOME_PATH;
        if (!Files.exists(applicationHomePath))
            applicationHomePath.toFile().mkdirs();
    }
}
