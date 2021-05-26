package com.example.hrms.core.concrete;

import com.example.hrms.core.abstracts.IdentityVerificationService;
import org.springframework.stereotype.Service;

@Service
public class IdentityVerificationSimulator implements IdentityVerificationService {
    @Override
    public boolean NationalityIdValidOrNot(String nationalityId, String firstName, String lastName, String birthYear) throws Exception {

        return true;

    }
}
