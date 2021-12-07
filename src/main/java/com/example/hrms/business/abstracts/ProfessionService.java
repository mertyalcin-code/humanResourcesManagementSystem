package com.example.hrms.business.abstracts;

import com.example.hrms.core.concrete.DataResult;
import com.example.hrms.core.concrete.Result;
import com.example.hrms.entities.Profession;

import java.util.List;

public interface ProfessionService {
    DataResult<List<Profession>> getAllProfessions();

    Result professionAdd(Profession profession);

    Result professionDeleteWithId(int id);

    Result professionDeleteWithTitle(String title);

    Result updateProfessionTitle(String oldTitle, String newTitle);

    DataResult<Profession> getProfessionById(int id);

    DataResult<Profession> getProfessionByTitle(String title);


}
