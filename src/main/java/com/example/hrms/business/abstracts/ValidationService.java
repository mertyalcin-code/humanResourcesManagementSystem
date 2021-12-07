package com.example.hrms.business.abstracts;

public interface ValidationService {


    boolean checkNationalityIdValid(String nationalityId, String firstName, String lastName, String birthYear);


    boolean checkEmailFromWebSite(String email, String website);


    boolean checkJobPositionValid(String jobPosition);


    boolean checkCityValid(String city);


}
