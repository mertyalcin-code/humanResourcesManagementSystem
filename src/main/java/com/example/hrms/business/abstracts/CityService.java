package com.example.hrms.business.abstracts;

import com.example.hrms.core.concrete.DataResult;
import com.example.hrms.core.concrete.Result;
import com.example.hrms.entities.City;

import java.util.List;

public interface CityService {
    DataResult<List<City>> getAllCitiesByPlateNumber();

    DataResult<List<City>> getAllCitiesAsc();

    DataResult<List<City>> getAllCitiesByImportance();

    DataResult<City> getCityByCityId(int cityId);

    DataResult<City> getCityByCityName(String cityName);

    Result cityAdd(City city);

    Result citiesAdd(List<City> cities);

    Result cityDelete(int cityId);

}
