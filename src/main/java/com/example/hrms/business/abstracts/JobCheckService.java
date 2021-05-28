package com.example.hrms.business.abstracts;

import java.time.LocalDate;

public interface JobCheckService {
    boolean checkJobPositionValid(String jobPosition);

    boolean checkJobDescriptionValid(String jobDescription);

    boolean checkCityValid(String city);

    boolean checkSalaryPerMonthValid(Double salary);

    boolean checkQuanitityValid(int quantity);

    boolean checkDeadlineValid(LocalDate deadline);


}
