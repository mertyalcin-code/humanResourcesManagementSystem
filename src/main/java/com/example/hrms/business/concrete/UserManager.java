package com.example.hrms.business.concrete;

import com.example.hrms.business.abstracts.UserService;
import com.example.hrms.core.concrete.*;
import com.example.hrms.dataAccess.abstracts.ActivationCodeDao;
import com.example.hrms.dataAccess.abstracts.UserDao;
import com.example.hrms.entities.abstracts.User;
import com.example.hrms.entities.concrete.ActivationCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class UserManager implements UserService {

    UserDao userDao;
    ActivationCodeDao activationCodeDao;
    Loggers loggers;

    @Autowired
    public UserManager(UserDao userDao, ActivationCodeDao activationCodeDao, Loggers loggers) {
        this.userDao = userDao;
        this.activationCodeDao = activationCodeDao;
        this.loggers = loggers;
    }

    @Override
    public DataResult<List<User>> getAllUsers() {
        List<User> users = userDao.findAll();
        if (users.size() < 1) {
            return new ErrorDataResult<>("Not Found");
        } else {
            loggers.log("All users listed", "getAllUsers");
            return new SuccessDataResult<>(users, userDao.findAll().size() + " people listed");
        }
    }

    @Override
    public DataResult<User> getUserByUserId(int userId) {
        User user = userDao.getUserByUserId(userId);
        if (user == null) {
            return new ErrorDataResult<>("Not Found");
        } else {
            loggers.log(user, "user found by id", "getUserByUserId");
            return new SuccessDataResult<>(user, "Listed");
        }

    }

    @Override
    public DataResult<User> getUserByEmail(String email) {
        User user = userDao.getUserByEmail(email);
        if (user == null) {
            return new ErrorDataResult<>("Not Found");
        } else {
            loggers.log(user, "user found by email", "getUserByEmail");
            return new SuccessDataResult<>(userDao.getUserByEmail(email), "Listed");
        }
    }

    @Override
    public Result userMailActivation(String activationCode) {
        ActivationCode code = activationCodeDao.getByActivationCode(activationCode);
        if (code == null) {
            return new ErrorResult("Invalid Code");
        }
        if (code.isActive()) {
            return new ErrorResult("Already activated");
        } else {
            code.setActivationDate(new Timestamp(System.currentTimeMillis()));
            code.setActive(true);
            activationCodeDao.save(code);
            User user = userDao.getUserByUserId(code.getUserId());
            loggers.log(user,
                    "Mail Activation: " + user.getEmail() + " ",
                    "mailActivation");
            return new SuccessResult("Activation succesful");
        }


    }

}
