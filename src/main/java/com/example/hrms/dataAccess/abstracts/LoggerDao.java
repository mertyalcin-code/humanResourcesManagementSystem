package com.example.hrms.dataAccess.abstracts;

import com.example.hrms.entities.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoggerDao extends JpaRepository<Log, Integer> {
}
