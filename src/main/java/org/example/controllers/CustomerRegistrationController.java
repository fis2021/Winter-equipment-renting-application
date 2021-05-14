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

public class CustomerRegistrationController {
    @FXML
    private TextField cName;

    @FXML
    private TextField cPhone;

    @FXML
    private TextField cEmail;

    @FXML
    private Text exepMessage;

    private static ObjectRepository<User> userRep;


    public void handleDoneAction(javafx.event.ActionEvent done) throws Exception{
        try {
            String loggedUser=SignUpController.getLoggedUser();
            UserServices.checkNamefield(cName.getText());
            userRep = UserServices.getUserRepository();
            for(User user:userRep.find())
                if(Objects.equals(loggedUser,user.getUsername())){
                    user.setName(cName.getText());
                    user.setEmail(cEmail.getText());
                    user.setPhone(cPhone.getText());
                }

            Parent homePage = FXMLLoader.load(getClass().getClassLoader().getResource("HomePage.fxml"));
            Scene homePageScene = new Scene(homePage);
            Stage window = (Stage) ((Node) done.getSource()).getScene().getWindow();;
            window.setScene(homePageScene);
            window.show();
        } catch (noNameException e) {
            exepMessage.setText(e.getMessage());
        }
    }

    public TextField getcName() {
        return cName;
    }

    public void setcName(TextField cName) {
        this.cName = cName;
    }

    public void setcPhone(TextField cPhone) {
        this.cPhone = cPhone;
    }

    public TextField getcPhone() {
        return cPhone;
    }

    public void setcEmail(TextField cEmail) {
        this.cEmail = cEmail;
    }

    public TextField getcEmail() {
        return cEmail;
    }
}
