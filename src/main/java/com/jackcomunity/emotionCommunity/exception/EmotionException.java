package com.jackcomunity.emotionCommunity.exception;

import java.util.HashMap;
import java.util.Map;

public abstract class EmotionException extends RuntimeException {

    public EmotionException(String message) {
        super(message);
    }

    public abstract int getStatusCode();


}
