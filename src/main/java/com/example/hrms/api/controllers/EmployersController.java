package com.example.hrms.api.controllers;


import com.example.hrms.business.abstracts.EmployerService;
import com.example.hrms.core.concrete.DataResult;
import com.example.hrms.core.concrete.Result;
import com.example.hrms.entities.concrete.Employer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employers")
public class EmployersController {
    EmployerService employerService;

    @Autowired
    public EmployersController(EmployerService employerService) {
        this.employerService = employerService;
    }

    @GetMapping("/getAllEmployers")
    public DataResult<List<Employer>> getAllEmployers() {
        return employerService.getAllEmployers();
    }

    @PostMapping("/employerRegistration")
    public Result employerRegistration(@RequestBody Employer employer) {
        return employerService.employerRegistration(employer);
    }

    @GetMapping("/getEmployerById")
    public DataResult<Employer> getEmployerById(@RequestParam int userId) {
        return employerService.getEmployerById(userId);
    }

    @GetMapping("/employerMailActivation")
    public Result employerMailActivation(@RequestParam String activationCode) {
        return employerService.employerMailActivation(activationCode);

    }
}
