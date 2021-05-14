package org.example.exceptions;
public class InvalidRoleException extends Exception{

    public InvalidRoleException() {
        super(String.format("Invalid role! "));
    }
}