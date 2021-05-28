package com.example.hrms.entities.concrete;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mail_activation")
public class ActivationCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activation_code_id")
    int activationCodeId;

    @Column(name = "user_id")
    int userId;

    @Column(name = "send_date")
    Timestamp sendDate;

    @Column(name = "activation_code")
    String activationCode;

    @Column(name = "activation_date")
    Timestamp activationDate;

    @Column(name = "is_active")
    boolean isActive = false;

    public ActivationCode(int userId, Timestamp sendDate, String activationCode) {
        this.userId = userId;
        this.sendDate = sendDate;
        this.activationCode = activationCode;
    }

}

