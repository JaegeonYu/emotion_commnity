package com.jackcomunity.emotionCommunity.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Roles {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private final String auth;
}
