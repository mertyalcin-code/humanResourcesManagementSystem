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

    @GetMapping("/getAllByPlateNumber")
    DataResult<List<City>> getAllByPlateNumber(){
        return cityService.getAllByPlateNumber();
    }
    @GetMapping("/getAllAsc")
    DataResult<List<City>> getAllAsc(){
        return cityService.getAllAsc();
    }
    @GetMapping("/getAllByImportance")
    DataResult<List<City>> getAllByImportance(){
        return cityService.getAllByImportance();
    }
    @GetMapping("/getByCityId")
    Result getByCityId(@RequestParam int cityId){
        return cityService.getByCityId(cityId);
    }
    @GetMapping("/getByCityName")
    Result getByCityName(@RequestParam String cityName){
        return cityService.getByCityName(cityName);
    }
    @PostMapping("/add")
    Result add(@RequestBody City city){
        return cityService.add(city);
    }
    @PostMapping("/addWithList")
    Result add(@RequestBody List<City> cities){
        return cityService.addWithList(cities);
    }
    @DeleteMapping("/delete")
    Result add(@RequestParam int cityId){
        return cityService.delete(cityId);
    }


}
