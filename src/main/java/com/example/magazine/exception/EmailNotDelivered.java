package com.example.magazine.exception;

public class EmailNotDelivered extends RuntimeException{
    public EmailNotDelivered(String message){
        super(message);
    }
}
