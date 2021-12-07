package com.example.hrms.core.dataAccess;

import com.example.hrms.core.entities.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemUserDao extends JpaRepository<SystemUser, Integer> {
    SystemUser getSystemUserByUserId(int userId);
}
