package com.example.hrms.api.controllers;

import com.example.hrms.business.abstracts.JobService;
import com.example.hrms.core.concrete.DataResult;
import com.example.hrms.core.concrete.Result;
import com.example.hrms.entities.concrete.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    Result jobAdd(@RequestBody Job job) {
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

}