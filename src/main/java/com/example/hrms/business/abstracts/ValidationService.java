package com.example.hrms.business.abstracts;

import java.time.LocalDate;

public interface ValidationService {


    boolean checkMailRegular(String email);

    boolean checkMailAlreadyExist(String email);

    boolean checkFirstNameRegular(String firstName);

    boolean checkLastNameRegular(String LastName);

    boolean checkPasswordRegular(String password);

    boolean checkControlPasswordSame(String password, String controlPassword);

    boolean checkNationalityIdValid(String nationalityId, String firstName, String lastName, String birthYear);

    boolean checkNationalityIdAlreadyExist(String nationalityId);

    boolean checkWebsiteRegular(String website);

    boolean checkEmailFromWebSite(String email, String website);

    boolean checkCompanyNameRegular(String companyName);

    boolean checkPhoneNumberCorrect(String phoneNumber);

    boolean checkJobPositionValid(String jobPosition);

    boolean checkJobDescriptionValid(String jobDescription);

    boolean checkCityValid(String city);

    boolean checkSalaryPerMonthValid(Double salary);

    boolean checkQuanitityValid(int quantity);

    boolean checkDeadlineValid(LocalDate deadline);


}
