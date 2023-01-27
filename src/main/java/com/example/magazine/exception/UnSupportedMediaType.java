package com.example.magazine.exception;

public class UnSupportedMediaType extends RuntimeException{
    public UnSupportedMediaType(String message){
        super(message);
    }
}
