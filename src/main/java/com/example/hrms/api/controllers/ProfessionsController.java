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
        return professionService.getAll();
    }

    @PostMapping("/add")
    public Result add(@RequestBody Profession profession) {
        return this.professionService.add(profession);
    }

    @GetMapping("/{id}")
    public DataResult<Profession> getById(@PathVariable int id) {

        return professionService.getById(id);
    }


}
