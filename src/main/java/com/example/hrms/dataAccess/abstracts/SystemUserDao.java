package com.example.hrms.dataAccess.abstracts;

import com.example.hrms.entities.concrete.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemUserDao extends JpaRepository<SystemUser, Integer> {
    SystemUser getSystemUserByUserId(int userId);
}
