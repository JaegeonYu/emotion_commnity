package com.jackcomunity.emotionCommunity.util.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
@Slf4j
public class TemplateCreate {
    private final ObjectMapper objectMapper;
    
    @Value("${external.api.key-id}")
    private String keyId;

    @Value("${external.api.key}")
    private String key;
    @Value("${external.api.id-value}")
    private String idValue;

    @Value("${external.api.key-value}")
    private String keyValue;

    @Value("${external.api.url}")
    private String url;


    public Json createTemplate (EmotionDiscrimination emotionDiscrimination) throws JsonProcessingException {
        //header api id, key 추가
        HttpHeaders header = new HttpHeaders();
        header.add(keyId, idValue);
        header.add(key, keyValue);
        header.setContentType(MediaType.APPLICATION_JSON);
        StringBuilder json = new StringBuilder();
        // json 변환
        try {
            json.append(objectMapper.writeValueAsString(emotionDiscrimination));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        // api 호출
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<?> entity = new HttpEntity<>(json.toString(), header);
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        // api 호출 값 반환
        log.info("sentiment api value : {}", exchange);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

       Json jsonMapper = objectMapper.readValue(exchange.getBody(), Json.class);

        return jsonMapper;
    }
}
