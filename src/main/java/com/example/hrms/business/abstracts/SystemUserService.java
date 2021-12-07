package com.example.hrms.business.abstracts;

import com.example.hrms.core.concrete.DataResult;
import com.example.hrms.core.concrete.Result;
import com.example.hrms.core.entities.SystemUser;

import java.util.List;

public interface SystemUserService {

    DataResult<List<SystemUser>> getAllSystemUsers();

    Result systemUserRegistration(SystemUser systemUser);

    DataResult<SystemUser> getSystemUserById(int id);
}
