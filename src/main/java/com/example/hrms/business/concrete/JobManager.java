package com.example.hrms.business.concrete;

import com.example.hrms.business.abstracts.JobCheckService;
import com.example.hrms.business.abstracts.JobService;
import com.example.hrms.core.concrete.*;
import com.example.hrms.dataAccess.abstracts.JobDao;
import com.example.hrms.entities.concrete.Job;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobManager implements JobService {


    private final JobDao jobDao;
    private final JobCheckService jobCheckService;

    public JobManager(JobDao jobDao, JobCheckService jobCheckService) {
        this.jobDao = jobDao;
        this.jobCheckService = jobCheckService;
    }

    @Override
    public DataResult<List<Job>> getAllJobs() {
        List<Job> jobs = jobDao.findAll();
        if (jobs.size() < 1) {
            return new ErrorDataResult<>("No job available");
        } else {
            return new SuccessDataResult<>(jobs, "Jobs listed");
        }
    }

    @Override
    public DataResult<List<Job>> getAllActiveJobs() { //çalışıyor mu bak
        List<Job> jobs = jobDao.getAllActiveJobs();

        if (jobs.size() < 1) {
            return new ErrorDataResult<>("No job available");
        } else {
            return new SuccessDataResult<>(jobs, "Jobs listed");
        }
    }

    @Override
    public DataResult<List<Job>> getAllActiveJobsAndSortByDeadline() {
        Sort sort = Sort.by(Sort.Direction.DESC, "deadline");
        List<Job> jobs = jobDao.getAllActiveJobs();
        if (jobs.size() < 1) {
            return new ErrorDataResult<>("No job available");
        } else {
            return new SuccessDataResult<>(jobs, "Jobs listed");
        }
    }

    @Override
    public Result jobAdd(Job job) {
        if (!jobCheckService.checkCityValid(job.getCity())) {
            return new ErrorResult("City must be from the list");
        }
        if (!jobCheckService.checkJobPositionValid(job.getPosition())) {
            return new ErrorResult("Job position must be from the list");
        }
        if (!jobCheckService.checkJobDescriptionValid(job.getDescription())) {
            return new ErrorResult("Description must be between 50-500 characters.");
        }
        if (!jobCheckService.checkSalaryPerMonthValid(job.getSalaryPerMonth())) {
            return new ErrorResult("Salary should be between 2000-100000 lira.");
        }
        if (!jobCheckService.checkQuanitityValid(job.getQuantity())) {
            return new ErrorResult("The number of open positions must be between a minimum of one and a maximum of 50.");
        }
        if (!jobCheckService.checkDeadlineValid(job.getDeadline())) {
            return new ErrorResult("Incorrect date");
        } else {
            jobDao.save(job);
            return new SuccessResult("Job added to system");
        }

    }

    @Override
    public Result jobDelete(int jobId) {
        Job job = jobDao.getJobByJobId(jobId);
        if (job == null) {
            return new ErrorResult("No job with such id");
        } else {
            jobDao.delete(job);
            return new SuccessResult("Deleted id: " + jobId);
        }
    }

    @Override
    public Result update(Job job) {
        return null;
    }

    @Override
    public Result JobActivationStatusChange(int jobId, Boolean status) {
        Job job = jobDao.getJobByJobId(jobId);
        if (job == null) {
            return new ErrorResult("No jobs with that id");
        }
        if (job.isActive() == status) {
            return new ErrorResult("Already in same status");
        } else {
            job.setActive(status);
            jobDao.save(job);
            return new SuccessResult("new status:" + status);
        }
    }
}
