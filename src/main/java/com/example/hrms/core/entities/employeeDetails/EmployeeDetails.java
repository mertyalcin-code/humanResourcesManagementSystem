package com.example.hrms.core.entities.employeeDetails;

import com.example.hrms.core.entities.Employee;
import com.example.hrms.core.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="employee_details")
//@JsonIgnoreProperties({"hinernateLazyInitializer","handler","experienceInfos","educationInfos","languageInfos","skillInfos"})
public class EmployeeDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeDetailsId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Employee employee;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "employee_details_id")
    private List<ExperienceInfo> experienceInfos;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "employee_details_id")
    private List<EducationInfo> educationInfos;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "employee_details_id")
    private List<LanguageInfo> languageInfos;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "employee_details_id")
    private List<SkillInfo> skillInfos;

    private String githubLink;

    private String linkedinLink;

    private String cover;











}
