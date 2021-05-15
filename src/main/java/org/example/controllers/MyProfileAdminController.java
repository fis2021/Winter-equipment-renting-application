package org.example.controllers;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class MyProfileAdminController {
    @FXML
    private Text name;
    @FXML
    private Text phone;
    @FXML
    private Text email;
    @FXML
    private Text adress;

    public void setName(Text name) {
        this.name = name;
    }

    public Text getName() {
        return name;
    }

    public void setPhone(Text phone) {
        this.phone = phone;
    }

    public Text getPhone() {
        return phone;
    }

    public void setEmail(Text email) {
        this.email = email;
    }

    public Text getEmail() {
        return email;
    }

    public void setAdress(Text adress) {
        this.adress = adress;
    }

    public Text getAdress() {
        return adress;
    }
}
