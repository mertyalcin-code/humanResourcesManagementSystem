package com.example.hrms.core.dataAccess;

import com.example.hrms.core.entities.dtos.EmployeeWithEmployeeDetails;
import com.example.hrms.core.entities.employeeDetails.EmployeeDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeDetailsDao extends JpaRepository<EmployeeDetails,Integer> {

//EmployeeWithEmployeeDetails getEmployeeWithEmployeeDetails();//Query yaz
}
