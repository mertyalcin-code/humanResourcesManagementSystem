package com.example.hrms.business.concrete;

import com.example.hrms.business.abstracts.JobCheckService;
import com.example.hrms.dataAccess.abstracts.CityDao;
import com.example.hrms.dataAccess.abstracts.JobDao;
import com.example.hrms.dataAccess.abstracts.ProfessionDao;
import org.apache.james.mime4j.dom.datetime.DateTime;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ChronoLocalDateTime;
import java.util.Date;
@Service
public class JobCheckManager implements JobCheckService {
    private JobDao jobDao;
    private ProfessionDao professionDao;
    private CityDao cityDao;

    public JobCheckManager(JobDao jobDao, ProfessionDao professionDao, CityDao cityDao) {
        this.jobDao = jobDao;
        this.professionDao = professionDao;
        this.cityDao = cityDao;
    }

    @Override
    public boolean checkJobPositionValid(String jobPosition) {
        if(professionDao.findProfessionByTitle(jobPosition)==null){
            return false;
        }
        return true;
    }

    @Override
    public boolean checkJobDescriptionValid(String jobDescription) {
       if(jobDescription.length()<50 || jobDescription.length()>500){
           return false;
       }
       return true;
    }

    @Override
    public boolean checkCityValid(String city) {
        if(cityDao.getByCityName(city)==null){
            return false;
        }
        return true;
    }

    @Override
    public boolean checkSalaryPerMonthValid(Double salary) {
        if(salary<2000 || salary>100000){
            return false;
        }
        return true;
    }

    @Override
    public boolean checkQuanitityValid(int quantity) {
        if(quantity<1 || quantity>50){
            return false;
        }
        return true;
    }

    @Override
    public boolean checkDeadlineValid(LocalDate deadline) {

        return true;
    }
}
