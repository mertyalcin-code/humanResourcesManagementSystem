package com.example.hrms.api.controllers;

import com.example.hrms.business.abstracts.SystemUserService;
import com.example.hrms.core.concrete.DataResult;
import com.example.hrms.core.concrete.ErrorDataResult;
import com.example.hrms.core.concrete.Result;
import com.example.hrms.core.entities.SystemUser;
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
@RequestMapping("/api/system-users")
public class SystemUsersController {

    SystemUserService systemUserService;

    @Autowired
    public SystemUsersController(SystemUserService systemUserService) {
        this.systemUserService = systemUserService;
    }

    @GetMapping("/getAllSystemUsers")
    public DataResult<List<SystemUser>> getAllSystemUsers() {
        return systemUserService.getAllSystemUsers();
    }

    @PostMapping("/systemUserRegistration")
    public Result systemUserRegistration(@Valid @RequestBody SystemUser systemUser) {
        return systemUserService.systemUserRegistration(systemUser);
    }

    @GetMapping("/getSystemUserById")
    public DataResult<SystemUser> getSystemUserById(@RequestParam int userId) {
        return systemUserService.getSystemUserById(userId);
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
