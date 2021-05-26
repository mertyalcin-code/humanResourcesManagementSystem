package com.example.hrms.entities.concrete;

import com.example.hrms.entities.abstracts.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@Table(name="employers")
@PrimaryKeyJoinColumn(name="user_id", referencedColumnName="id")
@JsonIgnoreProperties("id")
public class Employer extends User {


    @Column(name="company_name")
    private String companyName;

    @Column(name="website")
    private String website;

    @Column(name="phone_number")
    private String phone;

    @Column(name="system_approval")
    private boolean systemVerification=false; //swagger da çıkmasın ???
}
