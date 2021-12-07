package com.example.hrms.business.abstracts;

import com.example.hrms.core.concrete.DataResult;
import com.example.hrms.core.concrete.Result;
import com.example.hrms.core.entities.Employer;

import java.util.List;

public interface EmployerService {
    DataResult<List<Employer>> getAllEmployers();

    Result employerRegistration(Employer employer);

    DataResult<Employer> getEmployerById(int id);

    Result employerMailActivation(String activationCode);

    Result employerSystemActivation(int userId, boolean status);
}
