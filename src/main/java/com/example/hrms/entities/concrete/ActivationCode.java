package com.example.hrms.entities.concrete;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="mail_activation")
public class ActivationCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "user_id")
    int userId;

    @Column(name = "send_date")
    Timestamp sendDate;

    @Column(name="activation_code")
    String activationCode;

    @Column(name = "activation_date")
    Timestamp activationDate;

    @Column(name="is_active")
    boolean isActive=false;

    public ActivationCode(int userId, Timestamp sendDate, String activationCode) {
        this.userId = userId;
        this.sendDate = sendDate;
        this.activationCode = activationCode;
    }

}

