package com.jackcomunity.emotionCommunity.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class    WebSecurityConfig {
    @Bean
    public SecurityFilterChain
    securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests((request)->request
                        .antMatchers("/posts","/posts/read/**","/posts/search/**","/account/**","/css/**").permitAll()
                        .anyRequest().authenticated()).
                formLogin((form)->form
                        .loginPage("/account/login").permitAll()
                        .defaultSuccessUrl("/posts")
                        .failureUrl("/account/login?error"))
                .logout().permitAll()
                .logoutSuccessUrl("/posts");

        return http.build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}