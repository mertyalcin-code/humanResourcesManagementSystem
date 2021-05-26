package com.example.hrms.business.concrete;

import com.example.hrms.business.abstracts.UserService;

import com.example.hrms.core.concrete.*;
import com.example.hrms.dataAccess.abstracts.ActivationCodeDao;
import com.example.hrms.dataAccess.abstracts.UserDao;
import com.example.hrms.entities.abstracts.User;

import com.example.hrms.entities.concrete.ActivationCode;
import com.example.hrms.entities.concrete.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.sql.Timestamp;
import java.util.List;

@Service
public class UserManager implements UserService{

    UserDao userDao;
    ActivationCodeDao activationCodeDao;

    @Autowired
    public UserManager(UserDao userDao, ActivationCodeDao activationCodeDao) {
        this.userDao = userDao;
        this.activationCodeDao = activationCodeDao;
    }




    @Override
    public DataResult<List<User>> getAll() {

        return new SuccessDataResult<>(userDao.findAll(),userDao.findAll().size()+" people listed");
    }
    @Override
    public DataResult<User> getById(int id) {
        if(userDao.findById(id).get()!=null){
            return new SuccessDataResult<>( userDao.findById(id).get(),"Listed");
        }
        else{
            return new ErrorDataResult<>("Not Found");
        }

    }

    @Override
    public DataResult<User> getByEmail(String email) {
        if(userDao.findByEmail(email)!=null){
            return new SuccessDataResult<>( userDao.findByEmail(email),"Listed");
        }
        else{
            return new ErrorDataResult<>("Not Found");
        }
    }

    @Override
    public Result mailActivation(String activationCode) {
        ActivationCode code = activationCodeDao.getByActivationCode(activationCode);
            if(code!=null){
                code.setActivationDate(new Timestamp(System.currentTimeMillis()));
                code.setActive(true);
                activationCodeDao.save(code);
                return new SuccessResult("Activation mail has been sent");
            }
            else{
                return new ErrorResult("Something went wrong");
            }



    }

}
