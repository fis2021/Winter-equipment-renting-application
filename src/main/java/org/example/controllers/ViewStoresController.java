package org.example.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
//import jdk.vm.ci.meta.Value;
import org.example.Models.User;
import org.example.services.UserServices;
//import sun.jvm.hotspot.utilities.Observable;

import javax.swing.text.TabableView;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewStoresController implements Initializable {
    @FXML
    private TextField filtredField;
    @FXML
    private TableView<User> tableView;
    @FXML
    private TableColumn<User,String> name;
    @FXML
    private TableColumn<User,String> adress;
    @FXML
    private TableColumn<User,String> email;
    @FXML
    private TableColumn<User,String> phone;

    private final ObservableList<User> dataList= FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb){
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        adress.setCellValueFactory(new PropertyValueFactory<>("adress"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));

        for(User useri: UserServices.getUserRepository().find()){

            dataList.add(useri);
        }
        FilteredList<User> filteredData= new FilteredList<>(dataList, b->true);

        filtredField.textProperty().addListener((observable, oldValue, newValue)-> {
            filteredData.setPredicate(user -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (user.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else return false;
            } );
            } );
        SortedList<User> sortedData= new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedData);
    }

}
