package com.example.hrms.business.concrete;

import com.example.hrms.business.abstracts.ProfessionService;
import com.example.hrms.core.concrete.*;
import com.example.hrms.dataAccess.abstracts.ProfessionDao;
import com.example.hrms.entities.concrete.Profession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessionManager implements ProfessionService {

    ProfessionDao professionDao;

    @Autowired
    public ProfessionManager(ProfessionDao professionDao) {
        this.professionDao = professionDao;
    }

    @Override
    public DataResult<List<Profession>> getAll() {

        return new SuccessDataResult(professionDao.findAll(),professionDao.findAll().size()+" professions listed");
    }

    @Override
    public Result add(Profession profession) {

        if(professionDao.findProfessionByTitle(profession.getTitle())==null){
            professionDao.save(profession);

            return new SuccessResult("Succesfully Added: "+profession.getTitle()) ;
        }
        else{
            return new ErrorResult("This profession is available in the system: "+profession.getTitle());
        }
    }

    @Override
    public DataResult<Profession> getById(int id) {

        return  new SuccessDataResult(professionDao.findById(id).get(),"Data Listed");
    }
}
