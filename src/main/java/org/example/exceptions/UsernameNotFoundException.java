package org.example.exceptions;

public class UsernameNotFoundException extends Exception{

    public UsernameNotFoundException(){
        super(String.format("There is no account with this username!"));
    }

}
