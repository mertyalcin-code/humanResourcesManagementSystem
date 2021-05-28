package com.example.hrms.entities.concrete;

import com.example.hrms.entities.abstracts.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "employees")
@PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "id")
public class Employee extends User {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birth_year")
    private String birthYear;

    @Column(name = "nationality_id")
    private String nationalityId;







}