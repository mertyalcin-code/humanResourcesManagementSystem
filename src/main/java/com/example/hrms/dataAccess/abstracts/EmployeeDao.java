package com.example.hrms.dataAccess.abstracts;

import com.example.hrms.entities.concrete.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDao extends JpaRepository<Employee, Integer> {
    Employee getEmployeeByEmail(String email);

    Employee getEmployeeByUserId(int userId);

    Employee getEmployeeByNationalityId(String NationalityId);

    Employee getEmployeeByEmailAndNationalityId(String email, String NationalityId);
}
