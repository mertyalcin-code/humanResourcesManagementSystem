package com.example.hrms.core.dataAccess;

import com.example.hrms.core.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDao extends JpaRepository<Employee, Integer> {
    Employee getEmployeeByEmail(String email);

    Employee getEmployeeByUserId(int userId);

    Employee getEmployeeByNationalityId(String NationalityId);

    Employee getEmployeeByEmailAndNationalityId(String email, String NationalityId);
}
