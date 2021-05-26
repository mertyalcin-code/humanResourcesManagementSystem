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
    Loggers loggers;

    @Autowired
    public ProfessionManager(ProfessionDao professionDao, Loggers loggers) {
        this.professionDao = professionDao;
        this.loggers = loggers;
    }




    @Override
    public DataResult<List<Profession>> getAll() {
        loggers.log("All professions listed","getAllProfessions");
        return new SuccessDataResult(
                professionDao.findAll(),professionDao.findAll().size()+" professions listed");
    }

    @Override
    public Result add(Profession profession) {

        if(professionDao.findProfessionByTitle(profession.getTitle())==null){
            professionDao.save(profession);
            loggers.log("Profession added: "+profession.getTitle(),"addProfession");
            return new SuccessResult("Succesfully Added: "+profession.getTitle()) ;
        }
        else{
            return new ErrorResult("This profession is available in the system: "+profession.getTitle());
        }
    }

    @Override
    public Result delete(int id) {
       Profession professionToDelete= professionDao.findById(id).get();
       if(professionToDelete!=null){
           professionDao.delete(professionToDelete);
           loggers.log("Profession deleted: "+professionToDelete.getTitle(),"deleteProfession");
           return new SuccessResult("Deleted: "+professionToDelete.getTitle());

       }
       else{
           return new ErrorResult("Not found");
       }
    }

    @Override
    public DataResult<Profession> getById(int id) {
        loggers.log("Professions getted by id: "+professionDao.findById(id).get().getTitle(),"getByIdProfession");
        return  new SuccessDataResult(professionDao.findById(id).get(),"Data Listed");
    }
}
