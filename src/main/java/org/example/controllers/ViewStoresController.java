package org.example.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
//import jdk.vm.ci.meta.Value;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.dizitart.no2.objects.ObjectRepository;
import org.example.Models.Item;
import org.example.Models.User;
import org.example.services.UserServices;
//import sun.jvm.hotspot.utilities.Observable;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.function.Predicate;

import static org.dizitart.no2.objects.filters.ObjectFilters.eq;

public class ViewStoresController implements Initializable {
    @FXML
    private TextField filterField;
    @FXML
    private TableView<User> tableView;
    @FXML
    private TableColumn<User, String> name;
    @FXML
    private TableColumn<User, String> col_adr;
    @FXML
    private TableColumn<User, String> email;
    @FXML
    private TableColumn<User, String> phone;

    private final ObservableList<User> dataList = FXCollections.observableArrayList();
    private String loggedUser = LogInController.getLoggedUser();
    private static ObjectRepository<User> userRep;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_adr.setCellValueFactory(new PropertyValueFactory<>("address"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));

        for (User useri : UserServices.getUserRepository().find()) {
            if (Objects.equals(useri.getRole(), "Admin") && !Objects.equals(useri.getName(), "")) {

                User u = new User();
                u.setName(useri.getName());
                u.setAddress(useri.getAddress());
                u.setEmail(useri.getEmail());
                u.setPhone(useri.getPhone());
                dataList.add(u);
            }
        }
    /*    filterField.textProperty().addListener((observable, oldValue, newValue)-> {
            filteredData.setPredicate((Predicate<? super User>) (User user) -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                else if(user.getName().contains(newValue)){
                    return true;
                }
                return false;
            });
        });
        SortedList<User> sort= new SortedList<>(filteredData);
        sort.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sort);
*/
      /*  filterField.textProperty().addListener((observable, oldValue, newValue)-> {
            filteredData.setPredicate(user -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (user.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else return false;
            } );
            } );
        SortedList<User> sortedData= new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());*/
        tableView.setItems(dataList);
        //tableView.setItems(sortedData);




        //@FXML
   /* public void search(javafx.scene.input.KeyEvent keyEvent) {
        filterField.textProperty().addListener((observable, oldValue, newValue)-> {
            filteredData.setPredicate((Predicate<? super User>) (User user) -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                else if(user.getName().contains(newValue)){
                    return true;
                }
                return false;
            });
        });
        SortedList sort= new SortedList(filteredData);
        sort.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sort);
    }*/



    }
    FilteredList<User> filteredData = new FilteredList<>(dataList, b -> true);
    public void search(KeyEvent keyEvent) {

            filterField.textProperty().addListener((observable, oldValue, newValue)-> {
                filteredData.setPredicate((Predicate<? super User>) (User user) -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter= newValue.toLowerCase();
                    if(user.getName().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }
                    return false;
                });
            });
            SortedList sort= new SortedList(filteredData);
            sort.comparatorProperty().bind(tableView.comparatorProperty());
            tableView.setItems(sort);

    }

    @FXML
    public void HomeAction(javafx.event.ActionEvent home) throws Exception{
        Parent parentPage = FXMLLoader.load(getClass().getClassLoader().getResource("HomePage.fxml"));
        Scene PageScene = new Scene(parentPage);
        Stage window = (Stage) ((Node) home.getSource()).getScene().getWindow();;
        window.setScene(PageScene);
        window.show();
    }

    @FXML
    public void selectStoreAction(javafx.event.ActionEvent select) throws Exception{
        Parent parentPage = FXMLLoader.load(getClass().getClassLoader().getResource("StorePage.fxml"));
        Scene PageScene = new Scene(parentPage);
        Stage window = (Stage) ((Node) select.getSource()).getScene().getWindow();;
        window.setScene(PageScene);
        window.show();

    }

}

