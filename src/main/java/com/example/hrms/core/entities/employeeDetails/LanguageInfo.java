package com.example.hrms.core.entities.employeeDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name="employee_details_language_info")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LanguageInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne()
    @JoinColumn(name="employee_details_id")
    @JsonIgnore
    private EmployeeDetails employeeDetails;

    private String language;

    private int languageLevel;


}
