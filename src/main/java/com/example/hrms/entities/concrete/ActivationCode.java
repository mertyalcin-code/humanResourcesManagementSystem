package com.example.hrms.entities.concrete;


import com.example.hrms.entities.abstracts.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;


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

    @OneToOne
    @JoinColumn(name="user_id", foreignKey =@ForeignKey(name="mail_activation_users_id_fk") )
    User user;

    @Column(name = "send_date")
    Timestamp sendDate;

    @Column(name = "activation_code")
    String activationCode;

    @Column(name = "activation_date")
    Timestamp activationDate;

    @Column(name = "is_active")
    boolean isActive = false;

    public ActivationCode(User user, Timestamp sendDate, String activationCode) {
        this.user = user;
        this.sendDate = sendDate;
        this.activationCode = activationCode;
    }

}

