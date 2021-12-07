package com.example.hrms.business.concrete;

import com.example.hrms.business.abstracts.SystemUserService;
import com.example.hrms.core.concrete.*;
import com.example.hrms.core.dataAccess.SystemUserDao;
import com.example.hrms.core.entities.SystemUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemUserManager implements SystemUserService {

    SystemUserDao systemUserDao;
    ValidationManager userCheckManager;
    Loggers loggers;

    public SystemUserManager(SystemUserDao systemUserDao, ValidationManager userCheckManager, Loggers loggers) {
        this.systemUserDao = systemUserDao;
        this.userCheckManager = userCheckManager;
        this.loggers = loggers;
    }

    @Override
    public DataResult<List<SystemUser>> getAllSystemUsers() {
        List<SystemUser> systemUsers = this.systemUserDao.findAll();


        if (systemUsers.size() < 1) {
            return new ErrorDataResult<>("No system user available");
        } else {
            loggers.log("All system users listed", "getAllSystemUsers");
            return new SuccessDataResult<>(systemUsers, systemUserDao.findAll().size() + " people listed");
        }


    }

    @Override
    public Result systemUserRegistration(SystemUser systemUser) {
        systemUserDao.save(systemUser);
        loggers.log("System user registration: " + systemUser.getEmail() + " " + systemUser.getPosition(),
                "systemUsersRegistration");
        return new SuccessResult("Registered: " + systemUser.getEmail());


    }

    @Override
    public DataResult<SystemUser> getSystemUserById(int id) {
        SystemUser systemUser = systemUserDao.getSystemUserByUserId(id);
        if (systemUser == null) {
            return new ErrorDataResult<>("Not Found");
        } else {
            loggers.log("System user found by id: " + systemUser.getEmail() + " " + systemUser.getPosition(),
                    "getSystemUserById");
            return new SuccessDataResult<>(systemUser, "Listed");
        }


    }
}
