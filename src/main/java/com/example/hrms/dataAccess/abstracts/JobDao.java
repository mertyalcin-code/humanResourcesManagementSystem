package com.example.hrms.dataAccess.abstracts;

import com.example.hrms.core.concrete.DataResult;
import com.example.hrms.core.concrete.Result;
import com.example.hrms.entities.concrete.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobDao extends JpaRepository<Job,Integer> {
    @Query("From Job where isActive=true")
    List<Job> getAllActiveJobs();



}
