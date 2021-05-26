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

    @GetMapping("/getall")
    public DataResult<List<Employer>> getAll() {
        return employerService.getAll();
    }

    @PostMapping("/add")
    public Result add(@RequestBody Employer employer) {
        return employerService.add(employer);
    }

    @GetMapping("/{id}")
    public DataResult<Employer> getById(@PathVariable int id) {
        return employerService.getById(id);
    }

    @GetMapping("/activate/{activationCode}")
    public Result activator(@PathVariable String activationCode) {
        return employerService.mailActivation(activationCode);

    }
}
