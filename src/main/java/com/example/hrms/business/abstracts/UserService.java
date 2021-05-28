package com.example.hrms.business.abstracts;


import com.example.hrms.core.concrete.DataResult;
import com.example.hrms.core.concrete.Result;
import com.example.hrms.entities.abstracts.User;

import java.util.List;

public interface UserService {

    DataResult<List<User>> getAllUsers();

    DataResult<User> getUserByUserId(int id);

    DataResult<User> getUserByEmail(String email);

    Result userMailActivation(String activationCode);

}
