package com.example.hrms.business.abstracts;

import com.example.hrms.core.concrete.DataResult;
import com.example.hrms.core.concrete.Result;
import com.example.hrms.core.entities.employeeDetails.EmployeeDetails;

import java.util.List;

public interface EmployeeDetailsService {
    DataResult<List<EmployeeDetails>> getAllEmployeeDetails();
    Result addEmployeeDetails(EmployeeDetails employeeDetails);

}
