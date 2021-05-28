package com.example.hrms.business.abstracts;

import com.example.hrms.core.concrete.DataResult;
import com.example.hrms.core.concrete.Result;
import com.example.hrms.entities.concrete.City;

import java.util.List;
import java.util.Map;

public interface CityService {
    DataResult<List<City>> getAllByPlateNumber();
    DataResult<List<City>> getAllAsc();
    DataResult<List<City>> getAllByImportance();
    DataResult<City> getByCityId(int cityId);
    DataResult<City> getByCityName(String cityName);
    Result add(City city);
    Result addWithList(List<City> cities);
    Result delete(int cityId);

}
