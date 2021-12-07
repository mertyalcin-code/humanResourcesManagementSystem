package com.example.hrms.core.entities.employeeDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name="employee_details_education_info")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EducationInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne()
    @JoinColumn(name="employee_details_id")
    @JsonIgnore
    private EmployeeDetails employeeDetails;

    private String schoolName;

    private String department;

    private int startYear;

    private int finishedYear; //boş geçebilmeli

    private boolean isGraduated;



}
