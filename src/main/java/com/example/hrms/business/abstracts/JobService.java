package com.example.hrms.business.abstracts;

import com.example.hrms.core.concrete.DataResult;
import com.example.hrms.core.concrete.Result;
import com.example.hrms.entities.abstracts.User;
import com.example.hrms.entities.concrete.Job;

import java.util.List;

public interface JobService {
    DataResult<List<Job>> getAll();
    DataResult<List<Job>> getAllActiveJobs();
    DataResult<List<Job>> getAllByDeadline();
    Result add(Job job);
    Result delete(int jobId);
    Result update(Job job);
    Result activation(int jobId,Boolean active);

}
