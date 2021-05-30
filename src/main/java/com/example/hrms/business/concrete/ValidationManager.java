package com.example.hrms.business.concrete;

import com.example.hrms.business.abstracts.ValidationService;
import com.example.hrms.core.abstracts.IdentityVerificationService;
import com.example.hrms.dataAccess.abstracts.*;
import com.example.hrms.entities.abstracts.User;
import com.example.hrms.entities.concrete.Employee;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ValidationManager implements ValidationService {

    private final IdentityVerificationService identityVerificationService;
    private final UserDao userDao;
    private final EmployeeDao employeeDao;
    private final ProfessionDao professionDao;
    private final CityDao cityDao;
    private final JobDao jobDao;

    public ValidationManager(IdentityVerificationService identityVerificationService, UserDao userDao,
                             EmployeeDao employeeDao, ProfessionDao professionDao, CityDao cityDao, JobDao jobDao) {
        this.identityVerificationService = identityVerificationService;
        this.userDao = userDao;
        this.employeeDao = employeeDao;
        this.professionDao = professionDao;
        this.cityDao = cityDao;
        this.jobDao = jobDao;
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
        Pattern namePattern = Pattern.compile(".{2,50}");
        Matcher nameMatcher = namePattern.matcher(firstName);
        return nameMatcher.matches();

    }

    @Override
    public boolean checkLastNameRegular(String LastName) {
        Pattern lastNamePattern = Pattern.compile(".{2,50}");
        Matcher lastNameMatcher = lastNamePattern.matcher(LastName);
        return lastNameMatcher.matches();

    }

    @Override
    public boolean checkPasswordRegular(String password) {
        Pattern passwordPattern = Pattern.compile(".{6,20}");
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
            return identityVerificationService.NationalityIdValidOrNot(nationalityId, firstName, lastName, birthYear);
        } catch (Exception exception) {
            System.out.println(exception);
            exception.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean checkNationalityIdAlreadyExist(String nationalityId) {
        Employee employee = employeeDao.getEmployeeByNationalityId(nationalityId);
        return employee != null;
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
        String domain = email.substring(email.indexOf("@") + 1);
        return website.contains(domain);
    }

    @Override
    public boolean checkCompanyNameRegular(String companyName) {
        Pattern lastNamePattern = Pattern.compile(".{2,}");
        Matcher lastNameMatcher = lastNamePattern.matcher(companyName);
        return lastNameMatcher.matches();
    }

    @Override
    public boolean checkPhoneNumberCorrect(String phoneNumber) {
        Pattern pattern = Pattern.compile("^\\d{10}$");
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    @Override
    public boolean checkMailAlreadyExist(String email) {
        User user = userDao.getUserByEmail(email);
        return user != null;
    }

    @Override
    public boolean checkJobPositionValid(String jobPosition) {
        return professionDao.findProfessionByTitle(jobPosition) != null;
    }

    @Override
    public boolean checkJobDescriptionValid(String jobDescription) {
        return jobDescription.length() >= 50 && jobDescription.length() <= 500;
    }

    @Override
    public boolean checkCityValid(String city) {
        return cityDao.getCityByCityName(city) != null;
    }

    @Override
    public boolean checkSalaryPerMonthValid(Double salary) {
        return salary >= 2000 && salary <= 100000;
    }

    @Override
    public boolean checkQuanitityValid(int quantity) {
        return quantity >= 1 && quantity <= 50;
    }

    @Override
    public boolean checkDeadlineValid(LocalDate deadline) {

        return deadline.isAfter(LocalDate.now());
    }
}


