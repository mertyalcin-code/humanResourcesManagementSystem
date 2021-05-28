package com.example.hrms.dataAccess.abstracts;

import com.example.hrms.entities.concrete.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityDao extends JpaRepository<City,Integer> {
    City getByCityName(String cityName);
}
