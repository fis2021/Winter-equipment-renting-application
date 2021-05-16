package org.example.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.dizitart.no2.objects.ObjectRepository;
import org.example.Models.Item;
import org.example.Models.User;
import org.example.services.UserServices;
import javafx.geometry.Insets;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.ResourceBundle;

import static org.dizitart.no2.objects.filters.ObjectFilters.eq;

public class ManageItemsController implements Initializable {

    @FXML
    private TableView<Item> itemsTable;
    @FXML
    private TableColumn<Item, String> name;
    @FXML
    private TableColumn<Item, String> size;
    @FXML
    private TableColumn<Item, String> price;
    @FXML
    private TableColumn<Item, String> editCol;

    private String loggedUser = LogInController.getLoggedUser();
    private static ObjectRepository<User> userRep;


    ObservableList<Item> ItemsList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadDate();
    }
    Item sel_item=null;

    private void loadDate() {

        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        size.setCellValueFactory(new PropertyValueFactory<>("size"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        for (User useri : UserServices.getUserRepository().find()) {
            if (Objects.equals(useri.getUsername(), loggedUser) ) {
                ArrayList<Item> items_aux = useri.getItems();
                Iterator<Item> it = items_aux.iterator();

                while(it.hasNext()) {
                    Item i = it.next();
                    ItemsList.add(i);

                }


            }
        }

        //add cell of button edit
        Callback<TableColumn<Item, String>, TableCell<Item, String>> cellFoctory = (TableColumn<Item, String> param) -> {
            // make cell containing buttons
            final TableCell<Item, String> cell = new TableCell<Item, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        Button deleteIcon = new Button("Delete");
                        Button editIcon = new Button("Edit");

                        deleteIcon.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
                            if(itemsTable.getSelectionModel().getSelectedItem() != null) {
                                sel_item = itemsTable.getSelectionModel().getSelectedItem();

                                userRep = UserServices.getUserRepository();
                                for (User user : userRep.find()) {
                                    if (Objects.equals(user.getUsername(), loggedUser)) {
                                        ArrayList<Item> items_aux = user.getItems();
                                        Iterator<Item> it = items_aux.iterator();

                                        while (it.hasNext()) {
                                            Item i = it.next();
                                            if (Objects.equals(i.getName(), sel_item.getName())) {
                                                items_aux.remove(i);
                                                user.setItems(items_aux);
                                                userRep.update(eq("username", loggedUser), user);

                                            }
                                        }


                                    }
                                }
                            }
                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {

                            sel_item = itemsTable.getSelectionModel().getSelectedItem();
                            Parent parentPage = null;
                            try {
                                parentPage = FXMLLoader.load(getClass().getClassLoader().getResource("ItemForm.fxml"));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Scene PageScene = new Scene(parentPage);
                            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            window.setScene(PageScene);
                            window.show();



                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
        editCol.setCellFactory(cellFoctory);
        itemsTable.setItems(ItemsList);


    }
    @FXML
    public void addItemAction(javafx.event.ActionEvent addForm) throws Exception {
        Parent parentPage = FXMLLoader.load(getClass().getClassLoader().getResource("ItemForm.fxml"));
        Scene PageScene = new Scene(parentPage);
        Stage window = (Stage) ((Node) addForm.getSource()).getScene().getWindow();
        window.setScene(PageScene);
        window.show();
    }
}
