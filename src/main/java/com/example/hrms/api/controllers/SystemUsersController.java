package com.example.hrms.api.controllers;

import com.example.hrms.business.abstracts.EmployeeService;
import com.example.hrms.business.abstracts.SystemUserService;
import com.example.hrms.core.concrete.DataResult;
import com.example.hrms.core.concrete.Result;
import com.example.hrms.entities.abstracts.User;
import com.example.hrms.entities.concrete.Employee;
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

    @GetMapping("/getall")
    public DataResult<List<SystemUser>> getAll(){
        return systemUserService.getAll();
    }

    @PostMapping("/add")
    public Result add(@RequestBody SystemUser systemUser){
        return systemUserService.add(systemUser);
    }
    @GetMapping("/{id}")
    public DataResult<SystemUser> getById(@PathVariable int id){
        return systemUserService.getById(id);
    }

}
