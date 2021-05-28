package com.example.hrms.business.abstracts;

import com.example.hrms.core.concrete.DataResult;
import com.example.hrms.core.concrete.Result;
import com.example.hrms.entities.concrete.Employer;

import java.util.List;

public interface EmployerService {
    DataResult<List<Employer>> getAll();

    Result add(Employer employer);

    DataResult<Employer> getById(int id);

    Result mailActivation(String activationCode);
}
