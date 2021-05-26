package com.example.hrms.dataAccess;

import com.example.hrms.entities.concrete.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoggerDao extends JpaRepository<Log,Integer> {
}
