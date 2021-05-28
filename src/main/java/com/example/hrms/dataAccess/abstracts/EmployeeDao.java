package com.example.hrms.dataAccess.abstracts;

import com.example.hrms.entities.concrete.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDao extends JpaRepository<Employee, Integer> {
    Employee findEmployeeByEmail(String email);
    Employee findByEmailAndAndNationalityId(String email,String NationalityId);
}
