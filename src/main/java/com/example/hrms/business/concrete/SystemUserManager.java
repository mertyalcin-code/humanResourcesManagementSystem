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
    UserCheckManager userCheckManager;
    Loggers loggers;

    public SystemUserManager(SystemUserDao systemUserDao, UserCheckManager userCheckManager, Loggers loggers) {
        this.systemUserDao = systemUserDao;
        this.userCheckManager = userCheckManager;
        this.loggers = loggers;
    }

    @Override
    public DataResult<List<SystemUser>> getAll() {
        try {
            loggers.log("All system users listed", "getAllSystemUsers");
            return new SuccessDataResult<>(this.systemUserDao.findAll(), systemUserDao.findAll().size() + " people listed");

        } catch (Exception exception) {
            return new ErrorDataResult<>(exception.getMessage());
        }
    }

    @Override
    public Result add(SystemUser systemUser) {
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
            return new SuccessResult("Registered");
        }

    }

    @Override
    public DataResult<SystemUser> getById(int id) {
        if (systemUserDao.findById(id).get() != null) {
            return new SuccessDataResult<>(systemUserDao.findById(id).get(), "Listed");
        } else {
            return new ErrorDataResult<>("Not Found");
        }


    }
}
