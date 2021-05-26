package com.example.hrms.entities.concrete;

import com.example.hrms.entities.abstracts.User;
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
public class Employer extends User {


    @Column(name="company_name")
    private String companyName;

    @Column(name="website")
    private String website;

    @Column(name="phone_number")
    private String phone;

    @Column(name="system_approval")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private boolean systemVerification=false; //swagger da çıkmasın ???


    public Employer(int userId, String email, String password, String controlPassword, String companyName, String website, String phone) {
        super(userId, email, password, controlPassword);
        this.companyName = companyName;
        this.website = website;
        this.phone = phone;
    }
}
