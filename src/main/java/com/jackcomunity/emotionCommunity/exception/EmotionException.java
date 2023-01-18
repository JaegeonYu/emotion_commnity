package com.jackcomunity.emotionCommunity.exception;

import java.util.HashMap;
import java.util.Map;

public abstract class EmotionException extends RuntimeException{
    public final Map<String, String> validation = new HashMap<>();
    public EmotionException(String message){super(message);}
    public void addValidation(String fieldName, String message){validation.put(fieldName, message);}
}
