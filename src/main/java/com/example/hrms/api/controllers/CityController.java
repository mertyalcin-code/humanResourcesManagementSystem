package com.example.hrms.api.controllers;

import com.example.hrms.business.abstracts.CityService;
import com.example.hrms.core.concrete.DataResult;
import com.example.hrms.core.concrete.Result;
import com.example.hrms.entities.concrete.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CityController {
    @Autowired
    CityService cityService;

    @GetMapping("/getAllCitiesByPlateNumber")
    DataResult<List<City>> getAllCitiesByPlateNumber() {
        return cityService.getAllCitiesByPlateNumber();
    }

    @GetMapping("/getAllCitiesAsc")
    DataResult<List<City>> getAllCitiesAsc() {
        return cityService.getAllCitiesAsc();
    }

    @GetMapping("/getAllCitiesByImportance")
    DataResult<List<City>> getAllCitiesByImportance() {
        return cityService.getAllCitiesByImportance();
    }

    @GetMapping("/getCityByCityId")
    Result getCityByCityId(@RequestParam int cityId) {
        return cityService.getCityByCityId(cityId);
    }

    @GetMapping("/getCityByCityName")
    Result getCityByCityName(@RequestParam String cityName) {
        return cityService.getCityByCityName(cityName);
    }

    @PostMapping("/cityAdd")
    Result cityAdd(@RequestBody City city) {
        return cityService.cityAdd(city);
    }

    @PostMapping("/citiesAdd")
    Result citiesAdd(@RequestBody List<City> cities) {
        return cityService.citiesAdd(cities);
    }

    @DeleteMapping("/cityDelete")
    Result cityDelete(@RequestParam int cityId) {
        return cityService.cityDelete(cityId);
    }


}
