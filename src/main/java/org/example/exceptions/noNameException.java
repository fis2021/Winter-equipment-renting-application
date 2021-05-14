package org.example.exceptions;

public class noNameException extends Exception {
    public noNameException(){
        super(String.format("Please complete the name field!"));
    }
}
