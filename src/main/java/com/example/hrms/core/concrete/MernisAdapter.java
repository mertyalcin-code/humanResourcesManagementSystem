package com.example.hrms.core.concrete;

import com.example.hrms.core.abstracts.IdentityVerificationService;
import com.example.hrms.mernisService.FLGKPSPublicSoap;

import org.springframework.stereotype.Service;

//@Service
public class MernisAdapter implements IdentityVerificationService {

   FLGKPSPublicSoap publicSoap = new FLGKPSPublicSoap();
    @Override
    public boolean NationalityIdValidOrNot(String nationalityId, String firstName, String lastName, String birthYear)
            throws Exception {



       return publicSoap.TCKimlikNoDogrula(Long.parseLong(nationalityId),firstName,lastName,Integer.parseInt(birthYear));


    }
}
