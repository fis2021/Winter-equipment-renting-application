package org.example.controllers;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import org.dizitart.no2.objects.ObjectRepository;
import org.example.Models.Item;
import org.example.Models.User;
import org.example.exceptions.noNameException;
import org.example.services.UserServices;
import javafx.scene.control.TextField;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

import static org.dizitart.no2.objects.filters.ObjectFilters.eq;

public class ItemFormController {
    @FXML
    private TextField name;
    @FXML
    private TextField size;
    @FXML
    private TextField price;
    @FXML
    private Text exepMessage;

    private String loggedUser;
    private static ObjectRepository<User> userRep;

    @FXML
    public void handleUpdateItemArrayAction(javafx.event.ActionEvent update) throws Exception{
        try {
            int found=0;
            loggedUser = LogInController.getLoggedUser();
            userRep = UserServices.getUserRepository();
            for (User user:userRep.find()){
                if(Objects.equals(user.getUsername(),loggedUser)){
                    ArrayList<Item> items_aux = user.getItems();
                    Iterator<Item> it = items_aux.iterator();
                    UserServices.checkNamefield(name.getText());
                    while(it.hasNext()){
                        Item i = it.next();
                        if(Objects.equals(i.getName(),name)){
                            found =1;
                            if(!size.getText().trim().isEmpty()){
                                i.setSize(size.getText());
                            }
                            if(!price.getText().trim().isEmpty())
                                i.setPrice(price.getText());
                        }
                    }
                    if(found==0){
                        UserServices.checkNamefield(name.getText());
                        UserServices.checkNamefield(size.getText());
                        UserServices.checkNamefield(price.getText());
                        Item i = new Item(name.getText(),size.getText(), price.getText());
                        items_aux.add(i);
                    }
                    user.setItems(items_aux);
                    userRep.update(eq("username",loggedUser),user);
                }
            }
        } catch (noNameException e) {
            exepMessage.setText("Please complete all fields!");
        }

    }
}
