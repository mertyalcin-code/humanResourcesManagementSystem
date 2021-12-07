package com.example.hrms.business.concrete;

import com.example.hrms.business.abstracts.ProfessionService;
import com.example.hrms.core.concrete.*;
import com.example.hrms.dataAccess.abstracts.ProfessionDao;
import com.example.hrms.entities.Profession;
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
    public DataResult<List<Profession>> getAllProfessions() {
        loggers.log("All professions listed", "getAllProfessions");
        return new SuccessDataResult<>(
                professionDao.findAll(), professionDao.findAll().size() + " professions listed");
    }

    @Override
    public Result professionAdd(Profession profession) {

        if (professionDao.findProfessionByTitle(profession.getTitle()) == null) {
            professionDao.save(profession);
            loggers.log("Profession added: " + profession.getTitle(), "addProfession");
            return new SuccessResult("Succesfully Added: " + profession.getTitle());
        } else {
            return new ErrorResult("This profession is available in the system: " + profession.getTitle());
        }
    }

    @Override
    public Result professionDeleteWithId(int id) {
        Profession professionToDelete = professionDao.findProfessionById(id);
        if (professionToDelete == null) {
            return new ErrorResult("Not found");

        } else {
            professionDao.delete(professionToDelete);
            loggers.log("Profession deleted: " + professionToDelete.getTitle(), "deleteProfession");
            return new SuccessResult("Deleted: " + professionToDelete.getTitle());
        }
    }

    @Override
    public Result professionDeleteWithTitle(String title) {
        Profession profession = professionDao.findProfessionByTitle(title);
        if (profession == null) {

            return new ErrorResult("Not found");
        } else {
            professionDao.delete(profession);
            loggers.log("Profession deleted: " + profession.getTitle(), "deleteProfession");
            return new SuccessResult("Deleted: " + profession.getTitle());
        }
    }

    @Override
    public Result updateProfessionTitle(String oldTitle, String newTitle) {
        Profession profession = professionDao.findProfessionByTitle(oldTitle);
        if (profession == null) {
            return new ErrorResult("No professesion: " + oldTitle);
        }
        if (profession.getTitle().equals(newTitle)) {
            return new ErrorResult("new title cannot be same");
        } else {
            profession.setTitle(newTitle);
            professionDao.save(profession);
            return new SuccessResult(oldTitle + " updated to: " + newTitle);
        }

    }

    @Override
    public DataResult<Profession> getProfessionById(int id) {
        Profession profession = professionDao.findProfessionById(id);
        if (profession == null) {
            return new ErrorDataResult<>("Not found");
        } else {
            loggers.log("Professions getted by id: " + profession.getTitle(), "getByIdProfession");
            return new SuccessDataResult<>(profession, "Data Listed");
        }

    }

    @Override
    public DataResult<Profession> getProfessionByTitle(String title) {
        Profession profession = professionDao.findProfessionByTitle(title);
        if (profession != null) {
            loggers.log("Professions getted by title: " + title, "getByTitleProfession");
            return new SuccessDataResult<>(profession, "Listed");
        } else {
            return new ErrorDataResult<>("Not Found");
        }
    }
}
