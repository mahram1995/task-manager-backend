package com.example.dailytask.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "USERS")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
    @SequenceGenerator(name = "users_seq", sequenceName = "USERS_SEQ", allocationSize = 1)
    private Long id;
    @Column(name = "USER_NAME", nullable = false, unique = true, length = 100)
    private String userName;
    @Column(name = "PASSWORD", nullable = false, length = 255)
    private String password;
}
