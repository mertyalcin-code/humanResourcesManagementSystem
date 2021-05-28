package com.example.hrms.business.concrete;

import com.example.hrms.business.abstracts.EmployerService;
import com.example.hrms.core.concrete.*;
import com.example.hrms.dataAccess.abstracts.ActivationCodeDao;
import com.example.hrms.dataAccess.abstracts.EmployerDao;
import com.example.hrms.entities.concrete.Employer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployerManager implements EmployerService {

    EmployerDao employerDao;
    UserCheckManager userCheckManager;
    ActivationMailSender activationMailSender;
    ActivationCodeDao activationCodeDao;
    UserManager userManager;
    Loggers loggers;

    public EmployerManager(EmployerDao employerDao, UserCheckManager userCheckManager,
                           ActivationMailSender activationMailSender, ActivationCodeDao activationCodeDao, UserManager userManager, Loggers loggers) {
        this.employerDao = employerDao;
        this.userCheckManager = userCheckManager;
        this.activationMailSender = activationMailSender;
        this.activationCodeDao = activationCodeDao;
        this.userManager = userManager;
        this.loggers = loggers;
    }

    @Override
    public DataResult<List<Employer>> getAll() {
        try {
            loggers.log("All employers listed", "getAllEmployers");
            return new SuccessDataResult<>(this.employerDao.findAll(), employerDao.findAll().size() + " people listed");

        } catch (Exception exception) {
            return new ErrorDataResult<>(exception.getMessage());
        }
    }

    @Override
    public Result add(Employer employer) {
        if (userCheckManager.checkMailAlreadyExist(employer.getEmail())) {
            return new ErrorResult("Previously registered with this email");
        }
        if (!userCheckManager.checkMailRegular(employer.getEmail())) {
            return new ErrorResult("Incorrect E-mail");
        }
        if (!userCheckManager.checkEmailFromWebSite(employer.getEmail(), employer.getWebsite())) {
            return new ErrorResult("You must register with your corporate e-mail");
        }
        if (!userCheckManager.checkCompanyNameRegular(employer.getCompanyName())) {
            return new ErrorResult("Your company name is incorrect");
        }
        if (!userCheckManager.checkPasswordRegular(employer.getPassword())) {
            return new ErrorResult("Password is incorrect");
        }
        if (!userCheckManager.checkControlPasswordSame(employer.getPassword(), employer.getControlPassword())) {
            return new ErrorResult("Your passwords do not match");
        }
        if (!userCheckManager.checkWebsiteRegular(employer.getWebsite())) {
            return new ErrorResult("Corporate Website is incorrect");
        }
        if (!userCheckManager.checkPhoneNumberCorrect(employer.getPhone())) {
            return new ErrorResult("Phone number is incorrect");
        } else {
            employerDao.save(employer);
            activationMailSender.SendActivationMail(employer);

            return new SuccessResult("Registration Successful: activation code has been sent to your e-mail address.");
        }
    }

    @Override
    public DataResult<Employer> getById(int id) {
        if (employerDao.findById(id).get() != null) {
            return new SuccessDataResult<>(employerDao.findById(id).get(), "Listed");
        } else {
            return new ErrorDataResult<>("Not found");
        }

    }

    @Override
    public Result mailActivation(String activationCode) {

        return userManager.mailActivation(activationCode);
    }

}
