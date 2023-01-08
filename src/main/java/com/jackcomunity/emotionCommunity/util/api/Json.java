package com.jackcomunity.emotionCommunity.util.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Json {
    private Document document;

    @Override
    public String toString() {
        return this.document.getSentiment();
    }
}
