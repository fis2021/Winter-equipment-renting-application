package org.example.controllers;

import org.dizitart.no2.objects.ObjectRepository;
import org.example.Models.User;

public class AdministrationPageController {
    private static ObjectRepository<User> userRep;
    String loggedUser=LogInController.getLoggedUser();
}
