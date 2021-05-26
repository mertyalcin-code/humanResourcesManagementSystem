package com.example.hrms.dataAccess.abstracts;

import com.example.hrms.entities.concrete.ActivationCode;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;

public interface ActivationCodeDao extends JpaRepository<ActivationCode, Integer> {

    ActivationCode getByActivationCode(String activationCode);
}
