package com.example.hrms.api.controllers;

import com.example.hrms.business.abstracts.EmployeeDetailsService;
import com.example.hrms.core.concrete.DataResult;
import com.example.hrms.core.concrete.Result;
import com.example.hrms.core.entities.employeeDetails.EmployeeDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/EmployeeDetails")
public class EmployeeDetailsController {
    @Autowired
    EmployeeDetailsService employeeDetailsService;
    @GetMapping("/getAllEmployeeDetails")
    public DataResult<?> getAllEmployeeDetails(){
        return employeeDetailsService.getAllEmployeeDetails();
    }
    @PostMapping("/Add")
    public Result addEmployeeDetails(@RequestBody EmployeeDetails employeeDetails){
        return employeeDetailsService.addEmployeeDetails(employeeDetails);
    }

}
