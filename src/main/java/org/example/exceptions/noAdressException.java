package org.example.exceptions;

public class noAdressException extends Exception{
    public noAdressException(){
        super(String.format("Please complete the adress field!"));
    }
}
