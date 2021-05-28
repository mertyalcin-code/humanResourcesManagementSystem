package com.example.hrms.business.abstracts;

import com.example.hrms.core.concrete.DataResult;
import com.example.hrms.core.concrete.Result;
import com.example.hrms.entities.concrete.Employee;

import java.util.List;

public interface EmployeeService {

    DataResult<List<Employee>> getAll();

    Result add(Employee employee);

    Result delete(int id);

    //updateler eklemek lazım

    DataResult<Employee> getById(int id);

    DataResult<Employee> getByEmail(String email);

    DataResult<Employee> getByEmailAndNationaliyId(String email,String nationalityId);

    Result mailActivation(String activationCode);


}
