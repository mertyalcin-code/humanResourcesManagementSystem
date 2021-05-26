package com.example.hrms.core.abstracts;

public interface IdentityVerificationService {

    boolean NationalityIdValidOrNot(String nationalityId,String firstName,String lastName, String birthYear) throws Exception;
}
