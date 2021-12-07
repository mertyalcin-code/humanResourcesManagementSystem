package com.example.hrms.api.controllers;

import com.example.hrms.business.abstracts.JobService;
import com.example.hrms.core.concrete.DataResult;
import com.example.hrms.core.concrete.ErrorDataResult;
import com.example.hrms.core.concrete.Result;
import com.example.hrms.entities.Job;
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
@RequestMapping("/api/jobs")
public class JobController {
    @Autowired
    JobService jobService;

    @GetMapping("/getAllJobs")
    DataResult<List<Job>> getAllJobs() {
        return jobService.getAllJobs();
    }

    @GetMapping("/getAllActiveJobsAndSortByDeadline")
    DataResult<List<Job>> getAllActiveJobsAndSortByDeadline() {
        return jobService.getAllActiveJobsAndSortByDeadline();
    }

    @GetMapping("/getAllActiveJobs")
    DataResult<List<Job>> getAllActiveJobs() {
        return jobService.getAllActiveJobs();
    }


    @PostMapping("/jobAdd")
    Result jobAdd(@Valid @RequestBody Job job) {
        return jobService.jobAdd(job);
    }

    @DeleteMapping("/jobDelete")
    Result jobDelete(@RequestParam int id) {
        return jobService.jobDelete(id);
    }

    @GetMapping("/JobActivationStatusChange")
    Result JobActivationStatusChange(@RequestParam int jobId, @RequestParam Boolean status) {
        return jobService.JobActivationStatusChange(jobId, status);
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