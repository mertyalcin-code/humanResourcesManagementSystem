package com.example.hrms.api.controllers;


import com.example.hrms.business.abstracts.ProfessionService;
import com.example.hrms.core.concrete.DataResult;
import com.example.hrms.core.concrete.Result;
import com.example.hrms.entities.concrete.Profession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/professions")
public class ProfessionsController {

    private final ProfessionService professionService;

    @Autowired
    public ProfessionsController(ProfessionService professionService) {
        this.professionService = professionService;
    }

    @GetMapping("/getall")
    public DataResult<List<Profession>> getAll() {
        return professionService.getAllProfessions();
    }

    @PostMapping("/professionAdd")
    public Result professionAdd(@RequestBody Profession profession) {
        return this.professionService.professionAdd(profession);
    }

    @DeleteMapping("/professionDeleteWithId")
    public Result professionDeleteWithId(@RequestParam int id) {
        return professionService.professionDeleteWithId(id);
    }

    @DeleteMapping("/professionDeleteWithTitle/")
    public Result professionDeleteWithTitle(@RequestParam(value = "title") String title) {
        System.out.println(title);
        return professionService.professionDeleteWithTitle(title);
    }

    @GetMapping("/getProfessionByTitle")
    public DataResult<Profession> getProfessionByTitle(@RequestParam(value = "title") String title) {

        return professionService.getProfessionByTitle(title);
    }

    @GetMapping("/updateProfessionTitle")
    Result updateProfessionTitle(@RequestParam String oldTitle, @RequestParam String newTitle) {
        return professionService.updateProfessionTitle(oldTitle, newTitle);
    }

    @GetMapping("/getProfessionById}")
    public DataResult<Profession> getProfessionById(@RequestParam int id) {

        return professionService.getProfessionById(id);
    }


}
