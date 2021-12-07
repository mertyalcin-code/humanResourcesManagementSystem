package com.example.hrms.entities;

import com.example.hrms.core.validation.DefinedCity;
import com.example.hrms.core.validation.DefinedProfession;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "jobs")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotEmpty
    @NotNull
    private int jobId;

    @NotEmpty
    @NotNull
    @DefinedProfession
    private String position;

    @NotEmpty
    @NotNull
    @Size(min = 50, max = 500)
    private String description;

    @NotEmpty
    @NotNull
    @DefinedCity
    private String city;

    @Size(min = 2000, max = 100000)
    private Double salaryPerMonth;

    @NotEmpty
    @NotNull
    @Size(min = 1, max = 50)
    private int quantity;

    @Future()
    private LocalDate deadline;

    @NotNull
    private boolean isActive = true;

}
