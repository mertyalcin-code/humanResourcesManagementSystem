package com.example.hrms.business.concrete;

import com.example.hrms.business.abstracts.EmployeeDetailsService;
import com.example.hrms.core.concrete.DataResult;
import com.example.hrms.core.concrete.Result;
import com.example.hrms.core.concrete.SuccessDataResult;
import com.example.hrms.core.concrete.SuccessResult;
import com.example.hrms.core.dataAccess.EmployeeDetailsDao;
import com.example.hrms.core.entities.employeeDetails.EmployeeDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeDetailsManager implements EmployeeDetailsService {
    @Autowired
    EmployeeDetailsDao employeeDetailsDao;

    public DataResult<List<EmployeeDetails>> getAllEmployeeDetails(){
        return new SuccessDataResult<>(employeeDetailsDao.findAll(),"Listed");
    }

    @Override
    public Result addEmployeeDetails(EmployeeDetails employeeDetails) {
        employeeDetailsDao.save(employeeDetails);
        return new SuccessResult("saved");
    }
}
