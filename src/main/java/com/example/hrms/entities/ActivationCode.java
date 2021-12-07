package com.example.hrms.entities;


import com.example.hrms.core.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
    private int activationCodeId;

    @OneToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "mail_activation_users_id_fk"))
    private User user;

    @Column(name = "send_date")
    @NotEmpty
    @NotNull
    private Timestamp sendDate;

    @Column(name = "activation_code")
    @NotEmpty
    @NotNull
    private String activationCode;

    @Column(name = "activation_date")
    @NotEmpty
    @NotNull
    private Timestamp activationDate;

    @Column(name = "is_active")
    @NotEmpty
    @NotNull
    private boolean isActive = false;

    public ActivationCode(User user, Timestamp sendDate, String activationCode) {
        this.user = user;
        this.sendDate = sendDate;
        this.activationCode = activationCode;
    }

}

