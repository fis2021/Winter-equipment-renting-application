package org.example.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CustomerRegistrationController {
    @FXML
    private TextField cName;

    @FXML
    private TextField cPhone;

    @FXML
    private TextField cEmail;

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
