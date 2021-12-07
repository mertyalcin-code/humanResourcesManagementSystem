package com.example.hrms.business.concrete;

import com.example.hrms.business.abstracts.EmployerService;
import com.example.hrms.core.concrete.*;
import com.example.hrms.core.dataAccess.EmployerDao;
import com.example.hrms.core.entities.Employer;
import com.example.hrms.dataAccess.abstracts.ActivationCodeDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployerManager implements EmployerService {

    EmployerDao employerDao;
    ValidationManager userCheckManager;
    ActivationMailSender activationMailSender;
    ActivationCodeDao activationCodeDao;
    UserManager userManager;
    Loggers loggers;

    public EmployerManager(EmployerDao employerDao, ValidationManager userCheckManager,
                           ActivationMailSender activationMailSender, ActivationCodeDao activationCodeDao, UserManager userManager, Loggers loggers) {
        this.employerDao = employerDao;
        this.userCheckManager = userCheckManager;
        this.activationMailSender = activationMailSender;
        this.activationCodeDao = activationCodeDao;
        this.userManager = userManager;
        this.loggers = loggers;
    }

    @Override
    public DataResult<List<Employer>> getAllEmployers() {
        List<Employer> employers = this.employerDao.findAll();
        if (employers.size() < 1) {
            return new ErrorDataResult<>("Not found");
        } else {
            loggers.log("All employers listed", "getAllEmployers");
            return new SuccessDataResult<>(employers, employerDao.findAll().size() + " people listed");
        }


    }

    @Override
    public Result employerRegistration(Employer employer) {

        if (!userCheckManager.checkEmailFromWebSite(employer.getEmail(), employer.getWebsite())) {
            return new ErrorResult("You must register with your corporate e-mail");
        } else {
            employerDao.save(employer);
            activationMailSender.SendActivationMail(employer);
            loggers.log("Registration: " + employer.getEmail() + " " + employer.getCompanyName(),
                    "employersRegistration");

            return new SuccessResult("Registration Successful: activation code has been sent to your e-mail address.");
        }
    }

    @Override
    public DataResult<Employer> getEmployerById(int id) {
        Employer employer = employerDao.getEmployerByUserId(id);
        if (employer == null) {
            return new ErrorDataResult<>("Not found");
        } else {
            loggers.log("Employee found by Id: " + employer.getEmail() + " " + employer.getCompanyName(),
                    "getEmployeeById");
            return new SuccessDataResult<>(employer, "Listed");
        }

    }

    @Override
    public Result employerMailActivation(String activationCode) {

        return userManager.userMailActivation(activationCode);
    }

    @Override
    public Result employerSystemActivation(int userId, boolean status) {
        Employer employer = employerDao.getEmployerByUserId(userId);
        if (employer == null) {
            return new ErrorResult("No employee with that Id");

        }
        if (employer.isSystemVerification() == status) {
            return new ErrorResult("Already in same status");
        } else {
            employer.setSystemVerification(status);
            employerDao.save(employer);
            loggers.log("Employer system activation change: " + employer.getEmail() + " " + employer.getCompanyName(),
                    "employerSystemActivation");
            return new SuccessResult("new employer System verification status:" + status);
        }
    }

}
