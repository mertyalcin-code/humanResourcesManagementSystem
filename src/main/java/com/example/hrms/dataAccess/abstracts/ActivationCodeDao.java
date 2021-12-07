package com.example.hrms.dataAccess.abstracts;

import com.example.hrms.entities.ActivationCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivationCodeDao extends JpaRepository<ActivationCode, Integer> {

    ActivationCode getByActivationCode(String activationCode);
}
