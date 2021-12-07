package com.example.hrms.api.controllers;

import com.example.hrms.business.abstracts.UserService;
import com.example.hrms.core.concrete.DataResult;
import com.example.hrms.core.concrete.Result;
import com.example.hrms.core.entities.User;
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

    @GetMapping("/getAllUsers")
    public DataResult<List<User>> getAll() {
        return userService.getAllUsers();
    }

    @GetMapping("/getUserByUserId")
    public DataResult<User> getById(@RequestParam int userId) {
        return userService.getUserByUserId(userId);
    }

    @GetMapping("/activate/{activationCode}")
    public Result activatorWithLink(@PathVariable String activationCode) {
        return userService.userMailActivation(activationCode);
    }

    @GetMapping("/activate")
    public Result activator(@RequestParam String activationCode) {
        return userService.userMailActivation(activationCode);

    }

    @GetMapping("/getUserByEmail")
    public DataResult<User> getByEmail(@RequestParam(value = "email") String email) {

        return userService.getUserByEmail(email);
    }

}
