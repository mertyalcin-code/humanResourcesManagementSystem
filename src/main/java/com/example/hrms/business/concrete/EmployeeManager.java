package com.example.hrms.business.concrete;

import com.example.hrms.business.abstracts.EmployeeService;
import com.example.hrms.business.abstracts.Logger;
import com.example.hrms.core.concrete.*;
import com.example.hrms.dataAccess.abstracts.ActivationCodeDao;
import com.example.hrms.dataAccess.abstracts.EmployeeDao;
import com.example.hrms.entities.concrete.ActivationCode;
import com.example.hrms.entities.concrete.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeManager implements EmployeeService {

    private EmployeeDao employeeDao;
    private UserCheckManager userCheckManager;
    private ActivationMailSender activationMailSender;
    private ActivationCodeDao activationCodeDao;
    private UserManager userManager;
    private List<Logger> loggers = new ArrayList<>();


    public EmployeeManager(EmployeeDao employeeDao, UserCheckManager userCheckManager, ActivationMailSender activationMailSender
            , ActivationCodeDao activationCodeDao, UserManager userManager, @Qualifier("postgreSqlLogger") Logger dbLogger, @Qualifier("fileLogger") Logger fileLogger) {
        this.employeeDao = employeeDao;
        this.userCheckManager = userCheckManager;
        this.activationMailSender = activationMailSender;
        this.activationCodeDao = activationCodeDao;
        this.userManager = userManager;
        this.loggers.add(dbLogger);
        this.loggers.add(fileLogger);
    }

    @Override
    public DataResult<List<Employee>> getAll() {

        return new SuccessDataResult<List<Employee>>( this.employeeDao.findAll(),employeeDao.findAll().size()+" people Listed");

    }

    @Override
    public Result add(Employee employee) {
        if (!userCheckManager.checkMailRegular(employee.getEmail())) {
            return new ErrorResult("Incorrect E-mail");
        }
        if (userCheckManager.checkMailAlreadyExist(employee.getEmail())) {
            return new ErrorResult("Previously registered with this email");
        }
        if (!userCheckManager.checkNationalityIdValid(
                employee.getNationalityId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getBirthYear())) {
            return new ErrorResult("Your Nationality ID number is incorrect");
        }
        if (!userCheckManager.checkFirstNameRegular(employee.getFirstName())) {
            return new ErrorResult("Your First name is Incorrect");
        }
        if (!userCheckManager.checkLastNameRegular(employee.getLastName())) {
            return new ErrorResult("Your Last Name is Incorrect");
        }
        if (userCheckManager.checkNationalityIdAlreadyExist((employee.getNationalityId()))) {
            return new ErrorResult("Previously registered with this Nationality ID number");
        }
        if (!userCheckManager.checkPasswordRegular(employee.getPassword())) {
            return new ErrorResult("Your password is incorrect");
        }
        if (!userCheckManager.checkControlPasswordSame(employee.getPassword(),employee.getControlPassword())) {
            return new ErrorResult("Your passwords do not match");
        }


        else {
            employeeDao.save(employee);
            loggers.forEach(logger -> logger.log(
                    employee,
                    "Registration: "
                            +employee.getEmail()
                            +" "
                            +employee.getFirstName()
                            +" "
                            +employee.getLastName(),
                    "Registration"));
            activationMailSender.SendActivationMail(employee);//şuan için veri tabanına aktivasyon kodu yazıyor.
            return new SuccessResult("Registration Successful: activation code has been sent to your e-mail address.");
        }


    }

    @Override
    public Result delete(int id) {
        try{
            Employee employee=employeeDao.findById(id).get();
            employeeDao.delete(employee);
            loggers.forEach(logger -> logger.log(
                    employee,
                    "Delete Registration: "
                            +employee.getEmail()
                            +" "
                            +employee.getFirstName()
                            +" "
                            +employee.getLastName(),
                    "delete"));
            return new SuccessResult("Deleted: "+employee.getFirstName()+" "+employee.getLastName());
        }
        catch (Exception exception){
            return new ErrorResult(exception.getMessage());
        }

    }


    @Override
    public DataResult<Employee> getById(int id) {
        Employee employee = employeeDao.findById(id).get();
        loggers.forEach(logger -> logger.log(
                employee,
                "Found by Id: "
                        +employee.getEmail()
                        +" "
                        +employee.getFirstName()
                        +" "
                        +employee.getLastName(),
                "getById"));
        return new SuccessDataResult<Employee>(employee,"Employee data has been fetched successfully.");
    }

    @Override
    public Result mailActivation(String activationCode) {
       return userManager.mailActivation(activationCode);
    }
}