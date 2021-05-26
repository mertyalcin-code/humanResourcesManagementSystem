package com.example.hrms.dataAccess.abstracts;

import com.example.hrms.entities.concrete.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployerDao extends JpaRepository<Employer,Integer> {
}
