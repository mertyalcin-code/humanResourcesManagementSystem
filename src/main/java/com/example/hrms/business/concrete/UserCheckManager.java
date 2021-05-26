package com.example.hrms.business.concrete;

import com.example.hrms.business.abstracts.UserCheckService;
import com.example.hrms.core.abstracts.IdentityVerificationService;
import com.example.hrms.dataAccess.abstracts.EmployeeDao;
import com.example.hrms.dataAccess.abstracts.UserDao;
import com.example.hrms.entities.abstracts.User;
import com.example.hrms.entities.concrete.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserCheckManager implements UserCheckService { //daha kullanılmadı

    IdentityVerificationService identityVerificationService;
    UserDao userDao;
    EmployeeDao employeeDao;

    @Autowired
    public UserCheckManager(IdentityVerificationService identityVerificationService, UserDao userDao, EmployeeDao employeeDao) {
        this.identityVerificationService = identityVerificationService;
        this.userDao = userDao;
        this.employeeDao = employeeDao;
    }






    @Override
    public boolean checkMailRegular(String email) {
        Pattern mailPattern = Pattern.compile("[a-z A-Z 0-9]+@[a-z A-Z 0-9]+\\.[a-z A-Z 0-9]+$");
        Matcher mailmatcher = mailPattern.matcher(email);
        return mailmatcher.matches();
        //com.tr olmuyor
    }

    @Override
    public boolean checkFirstNameRegular(String firstName) {
        Pattern namePattern = Pattern.compile(".{2,}");
        Matcher nameMatcher = namePattern.matcher(firstName);

        return nameMatcher.matches();

    }

    @Override
    public boolean checkLastNameRegular(String LastName) {
        Pattern lastNamePattern = Pattern.compile(".{2,}");
        Matcher lastNameMatcher = lastNamePattern.matcher(LastName);
        return  lastNameMatcher.matches();

    }

    @Override
    public boolean checkPasswordRegular(String password) {
        Pattern passwordPattern = Pattern.compile(".{6,}");
        Matcher passwordMatcher = passwordPattern.matcher(password);
        return passwordMatcher.matches();

    }

    @Override
    public boolean checkControlPasswordSame(String password, String controlPassword) {
        return password.equals(controlPassword);
    }

    @Override
    public boolean checkNationalityIdValid(String nationalityId, String firstName, String lastName, String birthYear) {

        try {
            return identityVerificationService.NationalityIdValidOrNot( nationalityId,  firstName,  lastName,  birthYear);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean checkNationalityIdAlreadyExist(String nationalityId) {
        List<Employee> allEmployees = employeeDao.findAll();
        boolean employeeMatch = false;
        for(Employee employee :allEmployees){
            if(employee.getNationalityId()==nationalityId){
                employeeMatch=true;
            }
        }
        return employeeMatch;
    }

    @Override
    public boolean checkWebsiteRegular(String website) {
        Pattern websitePattern =
                Pattern.compile(("^(https?:\\/\\/)?(www\\.)?([\\w]+\\.)+[\u200C\u200B\\w]{2,63}\\/?$"));
        Matcher websiteMatcher = websitePattern.matcher(website);
        return websiteMatcher.matches();
    }

    @Override
    public boolean checkEmailFromWebSite(String email, String website) {
        String domain=email.substring(email.indexOf("@")+1);
        return website.contains(domain);
    }

    @Override
    public boolean checkCompanyNameRegular(String companyName) {
        Pattern lastNamePattern = Pattern.compile(".{2,}");
        Matcher lastNameMatcher = lastNamePattern.matcher(companyName);
        return  lastNameMatcher.matches();
    }

    @Override
    public boolean checkPhoneNumberCorrect(String phoneNumber) {
        Pattern pattern = Pattern.compile("^\\d{10}$");
        Matcher matcher = pattern.matcher(phoneNumber);
       return matcher.matches();
    }

    @Override
    public boolean checkMailAlreadyExist(String email) {
        List<User> allUsers = userDao.findAll();
        boolean userMatch = false;
        for(User user :allUsers){
            if(user.getEmail()==email){
                userMatch=true;
            }
        }

        return userMatch;
    }
}


