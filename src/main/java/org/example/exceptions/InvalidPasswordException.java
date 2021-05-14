package org.example.exceptions;

public class InvalidPasswordException extends Exception{

    public InvalidPasswordException(){
        super(String.format("Invalid password!"));
    }
}
