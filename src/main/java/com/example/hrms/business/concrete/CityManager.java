package com.example.hrms.business.concrete;

import com.example.hrms.business.abstracts.CityService;
import com.example.hrms.core.concrete.*;
import com.example.hrms.dataAccess.abstracts.CityDao;
import com.example.hrms.entities.City;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityManager implements CityService {

    private final CityDao cityDao;
    private final Loggers loggers;

    public CityManager(CityDao cityDao, Loggers loggers) {
        this.cityDao = cityDao;
        this.loggers = loggers;
    }

    @Override
    public DataResult<List<City>> getAllCitiesByPlateNumber() {
        Sort sort = Sort.by(Sort.Direction.ASC, "cityId");
        List<City> cities = cityDao.findAll(sort);
        if (cities.size() < 1) {
            return new ErrorDataResult<>("Not found");
        } else {
            loggers.log("Cities listed by plate number ", "getAllCitiesByPlateNumber");
            return new SuccessDataResult<>(cities, cityDao.findAll().size() + " cities listed");
        }

    }

    @Override
    public DataResult<List<City>> getAllCitiesAsc() {
        Sort sort = Sort.by(Sort.Direction.ASC, "cityName");
        List<City> cities = cityDao.findAll(sort);
        if (cities.size() < 1) {
            return new ErrorDataResult<>("Not found");
        } else {
            loggers.log("Cities listed by city name ", "getAllCitiesAsc");
            return new SuccessDataResult<>(cities, cityDao.findAll().size() + " cities listed");
        }
    }

    @Override
    public DataResult<List<City>> getAllCitiesByImportance() {
        Sort sort = Sort.by(Sort.Direction.ASC, "cityName");
        List<City> cities = cityDao.findAll(sort);
        //nasıl sıralamalar değiştirilir bak.
        if (cities.size() < 1) {
            return new ErrorDataResult<>("Not found");
        } else {
            loggers.log("Cities listed by importance ", "getAllCitiesByImportance");
            return new SuccessDataResult<>(cities, cityDao.findAll().size() + " cities listed");
        }
    }

    @Override
    public DataResult<City> getCityByCityId(int cityId) {
        City city = cityDao.getCityByCityId(cityId);
        if (city == null) {
            return new ErrorDataResult<>("Not found by id");
        } else {
            loggers.log("City called by id: " + cityId, "getCityByCityId");
            return new SuccessDataResult<>(city, " listed");
        }
    }


    @Override
    public DataResult<City> getCityByCityName(String cityName) {
        City city = cityDao.getCityByCityName(cityName);
        if (city == null) {
            return new ErrorDataResult<>("Not found by id");
        } else {
            loggers.log("City called by id: " + cityName, "getCityByCityName");
            return new SuccessDataResult<>(city, " listed");
        }
    }

    @Override
    public Result cityAdd(City city) {
        if (cityDao.getCityByCityName(city.getCityName()) != null) {
            return new ErrorResult("City Already exist");
        }
        if (cityDao.findById(city.getCityId()).isPresent()) {
            return new ErrorResult("City id already exist");
        } else {
            cityDao.save(city);
            loggers.log("City add: " + city.getCityName(), "cityAdd");
            return new SuccessResult(city.getCityName() + " : added");
        }
    }

    @Override
    public Result citiesAdd(List<City> cities) {
        for (City city : cities) {
            if (cityDao.getCityByCityName(city.getCityName()) != null) {
                return new ErrorResult("City name exist: " + city.getCityName());
            }
            if (cityDao.getCityByCityId(city.getCityId()) != null) {
                return new ErrorResult("City id exist: " + city.getCityId());
            }
        }

        cityDao.saveAll(cities);
        loggers.log("Cities added: " + cities, "cityAdd");
        return new SuccessResult(cities.size() + " :cities added");
    }

    @Override
    public Result cityDelete(int cityId) {
        City city = cityDao.getCityByCityId(cityId);
        if (city == null) {
            return new ErrorResult("No city with that name");
        } else {
            loggers.log("City deleted: " + city.getCityName(), "cityDelete");
            cityDao.deleteById(cityId);
            return new SuccessResult(city.getCityName() + " : deleted");
        }
    }
}
