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

    @GetMapping("/getAll")
    DataResult<List<Job>> getAll(){
        return jobService.getAll();
    }

    @GetMapping("/getAllByDeadline")
    DataResult<List<Job>> getAllByDeadline(){
        return jobService.getAllByDeadline();
    }

    @GetMapping("/getAllActiveJobs")
    DataResult<List<Job>> getAllActiveJobs(){
        return jobService.getAllActiveJobs();
    }


    @PostMapping("/add")
    Result add(@RequestBody Job job){
        return jobService.add(job);
    }
    @DeleteMapping("/delete")
    Result add(@RequestParam int id){
        return jobService.delete(id);
    }
    @GetMapping("/activation")
    Result activation(@RequestParam int jobId,@RequestParam Boolean status){
        return jobService.activation(jobId,status);
    }

}