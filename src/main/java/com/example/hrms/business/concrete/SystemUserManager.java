package com.example.hrms.business.concrete;

import com.example.hrms.business.abstracts.SystemUserService;
import com.example.hrms.core.concrete.*;
import com.example.hrms.dataAccess.abstracts.SystemUserDao;
import com.example.hrms.entities.concrete.SystemUser;
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
    public DataResult<List<SystemUser>> getAllSysteUsers() {
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
        if (!userCheckManager.checkMailRegular(systemUser.getEmail())) {
            return new ErrorResult("Your E-mail is incorrect");
        }
        if (userCheckManager.checkMailAlreadyExist(systemUser.getEmail())) {
            return new ErrorResult("Previously registered with this email");
        }
        if (!userCheckManager.checkPasswordRegular(systemUser.getPassword())) {
            return new ErrorResult("Your password is incorrect");
        }
        if (!userCheckManager.checkControlPasswordSame(systemUser.getPassword(), systemUser.getControlPassword())) {
            return new ErrorResult("Your passwords do not match");
        } else {
            systemUserDao.save(systemUser);
            loggers.log("System user registration: " + systemUser.getEmail() + " " + systemUser.getPosition(),
                    "systemUsersRegistration");
            return new SuccessResult("Registered: " + systemUser.getEmail());
        }

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
