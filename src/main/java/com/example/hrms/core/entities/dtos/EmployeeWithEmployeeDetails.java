package com.example.hrms.core.entities.dtos;

import com.example.hrms.core.entities.employeeDetails.EmployeeDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeWithEmployeeDetails {
    private int user_id;
    private EmployeeDetails employeeDetails;


}
