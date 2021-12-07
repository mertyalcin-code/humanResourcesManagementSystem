package com.example.hrms.core.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Entity
@Data
@NoArgsConstructor
@Table(name = "employers")
@PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "id")
public class Employer extends User {


    @Column(name = "company_name")
    @NotEmpty
    @NotNull
    private String companyName;

    @Column(name = "website")
    @NotEmpty
    @NotNull
    @Pattern(regexp = "^(https?:\\/\\/)?(www\\.)?([\\w]+\\.)+[\u200C\u200B\\w]{2,63}\\/?$", message = "Incorrect Web-site")
    private String website;

    @Column(name = "phone_number")
    @NotEmpty
    @NotNull
    @Pattern(regexp = "^\\d{10}$", message = "5xx xxx xx xx")
    private String phone;

    @Column(name = "system_approval", insertable = false)
    @NotEmpty
    @NotNull
    @JsonIgnore
    private boolean systemVerification = false; //swagger da çıkmasın ???

}
