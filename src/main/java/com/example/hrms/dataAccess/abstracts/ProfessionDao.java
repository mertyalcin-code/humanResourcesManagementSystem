package com.example.hrms.dataAccess.abstracts;

import com.example.hrms.entities.Profession;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProfessionDao extends JpaRepository<Profession, Integer> {

    Profession findProfessionByTitle(String title);

    Profession findProfessionById(int id);

}
