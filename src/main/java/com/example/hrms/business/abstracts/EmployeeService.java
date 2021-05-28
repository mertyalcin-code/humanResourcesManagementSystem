package com.example.hrms.business.abstracts;

import com.example.hrms.core.concrete.DataResult;
import com.example.hrms.core.concrete.Result;
import com.example.hrms.entities.concrete.Employee;

import java.util.List;

public interface EmployeeService {

    DataResult<List<Employee>> getAllEmployees();

    Result employeeRegistration(Employee employee);

    Result employeeDelete(int id);

    //updateler eklemek lazım

    DataResult<Employee> getEmployeeByUserId(int id);

    DataResult<Employee> getEmployeeByEmail(String email);

    DataResult<Employee> getEmployeeByEmailAndNationaliyId(String email, String nationalityId);

    Result employeeMailActivation(String activationCode);


}
