package com.example.hrms.business.abstracts;


import com.example.hrms.core.concrete.DataResult;
import com.example.hrms.core.concrete.Result;
import com.example.hrms.entities.abstracts.User;
import com.example.hrms.entities.concrete.Employee;
import com.example.hrms.entities.concrete.Profession;

import java.util.List;

public interface UserService {

    DataResult<List<User>> getAll();
    DataResult<User> getById(int id);
    DataResult<User> getByEmail(String email);
    Result mailActivation(String activationCode);

}
