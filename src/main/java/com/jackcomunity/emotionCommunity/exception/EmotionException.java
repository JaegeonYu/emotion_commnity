package com.jackcomunity.emotionCommunity.exception;

public abstract class EmotionException extends RuntimeException {

    public EmotionException(String message) {
        super(message);
    }

    public abstract int getStatusCode();
}
