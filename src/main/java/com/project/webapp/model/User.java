package com.project.webapp.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "password_hash")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "role")
    private String role;

}
