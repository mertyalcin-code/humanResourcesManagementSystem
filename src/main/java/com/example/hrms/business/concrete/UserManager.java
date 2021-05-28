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
    public DataResult<List<User>> getAll() {

        try {
            loggers.log("All users listed", "getAllUsers");
            return new SuccessDataResult<>(this.userDao.findAll(), userDao.findAll().size() + " people listed");

        } catch (Exception exception) {
            return new ErrorDataResult<>(exception.getMessage());
        }
    }

    @Override
    public DataResult<User> getById(int id) {
        if (userDao.findById(id).get() != null) {
            loggers.log(userDao.findById(id).get(), "user found by id", "getByIdUser");
            return new SuccessDataResult<>(userDao.findById(id).get(), "Listed");
        } else {
            return new ErrorDataResult<>("Not Found");
        }

    }

    @Override
    public DataResult<User> getByEmail(String email) {
        if (userDao.findByEmail(email) != null) {
            return new SuccessDataResult<>(userDao.findByEmail(email), "Listed");
        } else {
            return new ErrorDataResult<>("Not Found");
        }
    }

    @Override
    public Result mailActivation(String activationCode) {
        ActivationCode code = activationCodeDao.getByActivationCode(activationCode);
        if (code != null && !code.isActive()) {
            code.setActivationDate(new Timestamp(System.currentTimeMillis()));
            code.setActive(true);
            activationCodeDao.save(code);

            loggers.log(userDao.findById(code.getUserId()).get(),
                    "Mail Activation: " + userDao.findById(code.getUserId()).get().getEmail() + " ",
                    "mailActivation");
            return new SuccessResult("Activation succesful");
        } else if (code != null && code.isActive()) {
            return new ErrorResult("Already activated");
        } else {
            return new ErrorResult("Something went wrong");
        }


    }

}
