package com.example.hrms.business.abstracts;

import com.example.hrms.core.concrete.DataResult;
import com.example.hrms.core.concrete.Result;
import com.example.hrms.entities.concrete.SystemUser;

import java.util.List;

public interface SystemUserService {
    DataResult<List<SystemUser>> getAll();

    Result add(SystemUser systemUser);

    DataResult<SystemUser> getById(int id);
}
