package com.example.hrms.core.dataAccess;

import com.example.hrms.core.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDao extends JpaRepository<User, Integer> {
    User getUserByEmail(String email);

    User getUserByUserId(int userId);
}
