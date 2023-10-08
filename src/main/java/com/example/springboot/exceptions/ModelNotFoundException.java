package com.example.springboot.exceptions;

public class ModelNotFoundException extends Exception {
    public static final String MENSAGEM_PADRAO = "Not found";
    public ModelNotFoundException(String message){
        super(message);
    }
    public ModelNotFoundException( Object model){
        super(String.format("%s %s", model.getClass().getSimpleName(), MENSAGEM_PADRAO));
    }
}
