package com.example.hrms.api.controllers;

import com.example.hrms.business.abstracts.UserService;
import com.example.hrms.core.concrete.DataResult;
import com.example.hrms.core.concrete.Result;
import com.example.hrms.entities.abstracts.User;
import com.example.hrms.entities.concrete.Profession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        super();
        this.userService = userService;
    }

    @GetMapping("/getall")
    public DataResult<List<User>> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public DataResult<User> getById(@PathVariable int id) {
        return userService.getById(id);
    }

    @GetMapping("/activate/{activationCode}")
    public Result activator(@PathVariable String activationCode) {
        return userService.mailActivation(activationCode);
    }

    @GetMapping("/byemail")
    public DataResult<User> getByEmail(@RequestParam(value = "email") String email) {

        return userService.getByEmail(email);
    }

}