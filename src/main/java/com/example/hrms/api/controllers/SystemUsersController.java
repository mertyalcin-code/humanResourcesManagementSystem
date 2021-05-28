package com.example.hrms.api.controllers;

import com.example.hrms.business.abstracts.SystemUserService;
import com.example.hrms.core.concrete.DataResult;
import com.example.hrms.core.concrete.Result;
import com.example.hrms.entities.concrete.SystemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/system-users")
public class SystemUsersController {

    SystemUserService systemUserService;

    @Autowired
    public SystemUsersController(SystemUserService systemUserService) {
        this.systemUserService = systemUserService;
    }

    @GetMapping("/getAllSysteUsers")
    public DataResult<List<SystemUser>> getAllSysteUsers() {
        return systemUserService.getAllSysteUsers();
    }

    @PostMapping("/systemUserRegistration")
    public Result systemUserRegistration(@RequestBody SystemUser systemUser) {
        return systemUserService.systemUserRegistration(systemUser);
    }

    @GetMapping("/getSystemUserById")
    public DataResult<SystemUser> getSystemUserById(@RequestParam int userId) {
        return systemUserService.getSystemUserById(userId);
    }

}
