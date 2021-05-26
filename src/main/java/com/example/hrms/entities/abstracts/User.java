package com.example.hrms.entities.abstracts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int userId;

    @Column(name = "email")
    String email;

    @Column(name = "password")
    String password;

    String controlPassword;

   }