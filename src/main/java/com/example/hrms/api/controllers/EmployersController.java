package com.example.hrms.api.controllers;


import com.example.hrms.business.abstracts.EmployerService;
import com.example.hrms.core.concrete.DataResult;
import com.example.hrms.core.concrete.ErrorDataResult;
import com.example.hrms.core.concrete.Result;
import com.example.hrms.core.entities.Employer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Result employerRegistration(@Valid @RequestBody Employer employer) {
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
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions) {
        Map<String, String> validationErrors = new HashMap<String, String>();
        for (FieldError fieldError : exceptions.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        ErrorDataResult<Object> errors = new ErrorDataResult<Object>(validationErrors, "Doğrulama hataları");

        return errors;

    }
}
