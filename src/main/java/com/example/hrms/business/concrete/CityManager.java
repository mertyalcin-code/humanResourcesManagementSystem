package com.example.hrms.business.concrete;

import com.example.hrms.business.abstracts.CityService;
import com.example.hrms.core.concrete.*;
import com.example.hrms.dataAccess.abstracts.CityDao;
import com.example.hrms.entities.concrete.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityManager implements CityService {
    @Autowired
    CityDao cityDao;

    @Override
    public DataResult<List<City>> getAllCitiesByPlateNumber() {
        Sort sort = Sort.by(Sort.Direction.ASC, "cityId");
        return new SuccessDataResult<>(cityDao.findAll(sort), cityDao.findAll().size() + " cities listed");
    }

    @Override
    public DataResult<List<City>> getAllCitiesAsc() {
        Sort sort = Sort.by(Sort.Direction.ASC, "cityName");
        return new SuccessDataResult<>(cityDao.findAll(sort), cityDao.findAll().size() + " cities listed");
    }

    @Override
    public DataResult<List<City>> getAllCitiesByImportance() {
        Sort sort = Sort.by(Sort.Direction.ASC, "cityId");
        List<City> importantCities = cityDao.findAll(sort);
        //nasıl yaparız düşün
        return new SuccessDataResult<>(importantCities, importantCities.size() + " cities listed");
    }

    @Override
    public DataResult<City> getCityByCityId(int cityId) {
        City city = cityDao.getCityByCityId(cityId);
        if (city != null) {
            return new SuccessDataResult<>(city, " listed");
        } else {
            return new ErrorDataResult<>("Not found by id");
        }

    }

    @Override
    public DataResult<City> getCityByCityName(String cityName) {
        City city = cityDao.getCityByCityName(cityName);
        if (city != null) {
            return new SuccessDataResult<>(city, " listed");
        } else {
            return new ErrorDataResult<>("Not found by id");
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
            return new SuccessResult(city.getCityName() + " : added");
        }
    }

    @Override
    public Result citiesAdd(List<City> cities) {
        cityDao.saveAll(cities);
        return new SuccessResult(cities.size() + " :cities added");
    }

    @Override
    public Result cityDelete(int cityId) {
        City city = cityDao.getCityByCityId(cityId);
        if (city == null) {
            return new ErrorResult("No city with that name");
        } else {
            cityDao.deleteById(cityId);
            return new SuccessResult(city.getCityName() + " : deleted");
        }
    }
}
