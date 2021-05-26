package com.example.hrms.business.abstracts;

public interface UserCheckService {


    boolean checkMailRegular(String email);

    boolean checkMailAlreadyExist(String email);

    boolean checkFirstNameRegular(String firstName);

    boolean checkLastNameRegular(String LastName);

    boolean checkPasswordRegular(String password);

    boolean checkControlPasswordSame(String password,String controlPassword);

    boolean checkNationalityIdValid(String nationalityId, String firstName, String lastName, String birthYear);

    boolean checkNationalityIdAlreadyExist(String nationalityId);

    boolean checkWebsiteRegular(String website);

    boolean checkEmailFromWebSite(String email,String website);

    boolean checkCompanyNameRegular(String companyName);



}
