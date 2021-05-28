package com.example.hrms.business.abstracts;

import org.apache.james.mime4j.dom.datetime.DateTime;

import java.time.LocalDate;
import java.util.Date;

public interface JobCheckService {
    boolean checkJobPositionValid(String jobPosition);
    boolean checkJobDescriptionValid(String jobDescription);
    boolean checkCityValid(String city);
    boolean checkSalaryPerMonthValid(Double salary);
    boolean checkQuanitityValid(int quantity);
    boolean checkDeadlineValid(LocalDate deadline);





}
