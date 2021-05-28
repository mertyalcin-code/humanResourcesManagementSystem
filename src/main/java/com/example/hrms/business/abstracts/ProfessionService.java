package com.example.hrms.business.abstracts;

import com.example.hrms.core.concrete.DataResult;
import com.example.hrms.core.concrete.Result;
import com.example.hrms.entities.concrete.Profession;

import java.util.List;

public interface ProfessionService {
    DataResult<List<Profession>> getAll();

    Result add(Profession profession);

    Result delete(int id);

    Result delete(String title);

    DataResult<Profession> getById(int id);

    DataResult<Profession> getByTitle(String title);


}
