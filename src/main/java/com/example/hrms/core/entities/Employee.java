package com.example.hrms.core.entities;

import com.example.hrms.core.validation.UniqueNationalityId;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@Entity
@Table(name = "employees")
@PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "id")

public class Employee extends User {

    @Column(name = "first_name")
    @NotEmpty
    @NotNull
    @Size(min = 2, max = 50)
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty
    @NotNull
    @Size(min = 2, max = 50)
    private String lastName;

    @Column(name = "birth_year")
    @NotEmpty
    @NotNull
    @Size(min = 4, max = 4)
    private String birthYear;

    @Column(name = "nationality_id")
    @NotEmpty
    @NotNull
    @UniqueNationalityId
    @Size(min = 11, max = 11)
    private String nationalityId;


}