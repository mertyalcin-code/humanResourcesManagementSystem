package com.example.hrms.business.concrete;

import com.example.hrms.business.abstracts.ValidationService;
import com.example.hrms.core.abstracts.IdentityVerificationService;
import com.example.hrms.core.dataAccess.EmployeeDao;
import com.example.hrms.core.dataAccess.UserDao;
import com.example.hrms.dataAccess.abstracts.CityDao;
import com.example.hrms.dataAccess.abstracts.JobDao;
import com.example.hrms.dataAccess.abstracts.ProfessionDao;
import org.springframework.stereotype.Service;

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
    public boolean checkNationalityIdValid(String nationalityId, String firstName, String lastName, String birthYear) {

        try {
            return identityVerificationService.NationalityIdValidOrNot(nationalityId, firstName, lastName, birthYear);
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean checkEmailFromWebSite(String email, String website) {
        String domain = email.substring(email.indexOf("@") + 1);
        return website.contains(domain);
    }


    @Override
    public boolean checkJobPositionValid(String jobPosition) {
        return professionDao.findProfessionByTitle(jobPosition) != null;
    }


    @Override
    public boolean checkCityValid(String city) {
        return cityDao.getCityByCityName(city) != null;
    }


}


