package com.example.hrms.core.dataAccess;

import com.example.hrms.core.entities.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployerDao extends JpaRepository<Employer, Integer> {
    Employer getEmployerByUserId(int id);

}
