package org.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.dizitart.no2.objects.ObjectRepository;
import org.example.Models.User;
import org.example.services.UserServices;

import java.util.Objects;

import static org.dizitart.no2.objects.filters.ObjectFilters.eq;


public class EditProfileCustomerController {


    @FXML
    private TextField name;

    @FXML
    private TextField phone;

    @FXML
    private TextField email;

    @FXML
    private Button saveButton;

    private static ObjectRepository<User> userRep;

    @FXML
    public void handleSaveAction()throws Exception{
        String loggedUser=LogInController.getLoggedUser();
        userRep = UserServices.getUserRepository();
        for(User user:userRep.find())
            if(Objects.equals(loggedUser,user.getUsername())){
               if( !name.getText().trim().isEmpty())
                   user.setName(name.getText());
               if( !email.getText().trim().isEmpty())
                    user.setEmail(email.getText());
               if( !phone.getText().trim().isEmpty())
                    user.setPhone(phone.getText());
               userRep.update(eq("username",loggedUser),user);
            }

        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("MyProfileCustomerPage.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        MyProfileCustomerController controller = loader.getController();
        controller.set();
        Stage stage = (Stage) (saveButton.getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }

}