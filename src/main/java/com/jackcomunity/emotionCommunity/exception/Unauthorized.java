package com.jackcomunity.emotionCommunity.exception;

public class Unauthorized extends EmotionException{
    private static final String MESSAGE = "권한이 필요합니다.";

    public Unauthorized() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 401;
    }
}
