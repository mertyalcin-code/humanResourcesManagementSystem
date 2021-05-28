package com.example.hrms.business.concrete;

import com.example.hrms.business.abstracts.JobCheckService;
import com.example.hrms.dataAccess.abstracts.CityDao;
import com.example.hrms.dataAccess.abstracts.JobDao;
import com.example.hrms.dataAccess.abstracts.ProfessionDao;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class JobCheckManager implements JobCheckService {
    private final JobDao jobDao;
    private final ProfessionDao professionDao;
    private final CityDao cityDao;

    public JobCheckManager(JobDao jobDao, ProfessionDao professionDao, CityDao cityDao) {
        this.jobDao = jobDao;
        this.professionDao = professionDao;
        this.cityDao = cityDao;
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
        return cityDao.getByCityName(city) != null;
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

        return true;
    }
}
