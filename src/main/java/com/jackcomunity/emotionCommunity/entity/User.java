package com.jackcomunity.emotionCommunity.entity;

import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;

@Entity
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false, length = 100)
    private String password;

    private boolean enabled;
}
