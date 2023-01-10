package com.jackcomunity.emotionCommunity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EmotionCommunityApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmotionCommunityApplication.class, args);
	}
}
