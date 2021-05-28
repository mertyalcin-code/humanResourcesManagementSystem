package com.example.hrms.entities.concrete;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="jobs")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int jobId;

    private String position;//not null listed

    private String description;//not null

    private String city;//not null listed

    private Double salaryPerMonth;

    private int quantity;// not null

    private LocalDate deadline;

    private boolean isActive=true;

}
