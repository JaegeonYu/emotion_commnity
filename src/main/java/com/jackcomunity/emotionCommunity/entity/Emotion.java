package com.jackcomunity.emotionCommunity.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Emotion {
    POSITIVE("POSITIVE"),
    NEUTRAL("NEUTRAL"),
    NEGATIVE("NEGATIVE");

    private final String emotion;
}
