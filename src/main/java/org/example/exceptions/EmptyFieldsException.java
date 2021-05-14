package org.example.exceptions;

public class EmptyFieldsException extends Exception{
    public EmptyFieldsException() {
        super(String.format("You must complete all fields! "));
    }
}