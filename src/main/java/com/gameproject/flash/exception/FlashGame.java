package com.gameproject.flash.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class FlashGame extends RuntimeException{

    public Map<String, String> validaion = new HashMap<>();

    public FlashGame(String message) {
        super(message);
    }

    public FlashGame(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract int getStatusCode();

    public void addValidation(String fieldName, String message){
        validaion.put(fieldName, message);
    }
}
