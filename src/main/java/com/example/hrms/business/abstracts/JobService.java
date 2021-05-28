package com.example.hrms.business.abstracts;

import com.example.hrms.core.concrete.DataResult;
import com.example.hrms.core.concrete.Result;
import com.example.hrms.entities.concrete.Job;

import java.util.List;

public interface JobService {
    DataResult<List<Job>> getAllJobs();

    DataResult<List<Job>> getAllActiveJobs();

    DataResult<List<Job>> getAllActiveJobsAndSortByDeadline();

    Result jobAdd(Job job);

    Result jobDelete(int jobId);

    Result update(Job job);//değiştir

    Result JobActivationStatusChange(int jobId, Boolean active);

}
