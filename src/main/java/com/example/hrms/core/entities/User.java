package com.example.hrms.core.entities;

import com.example.hrms.core.validation.UniqueEmail;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int userId;

    @Column(name = "email")
    @NotNull
    @NotEmpty
    @Email
    @UniqueEmail
    private String email;

    @Column(name = "password")
    @NotNull
    @NotEmpty
    @Size(min = 8, max = 20)
    private String password;

}