package com.example.hrms.api.controllers;


import com.example.hrms.business.abstracts.ProfessionService;
import com.example.hrms.core.concrete.DataResult;
import com.example.hrms.core.concrete.ErrorDataResult;
import com.example.hrms.core.concrete.Result;
import com.example.hrms.entities.Profession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    public Result professionAdd(@Valid @RequestBody Profession profession) {
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions) {
        Map<String, String> validationErrors = new HashMap<String, String>();
        for (FieldError fieldError : exceptions.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        ErrorDataResult<Object> errors = new ErrorDataResult<Object>(validationErrors, "Doğrulama hataları");

        return errors;

    }
}
