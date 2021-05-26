package com.example.hrms.api.controllers;

import com.example.hrms.business.abstracts.EmployeeService;
import com.example.hrms.core.concrete.DataResult;
import com.example.hrms.core.concrete.Result;
import com.example.hrms.entities.concrete.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeesController {

    EmployeeService employeeService;

    @Autowired
    public EmployeesController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/getall")
    public DataResult<List<Employee>> getAll() {
        return employeeService.getAll();
    }

    @PostMapping("/add")
    public Result add(@RequestBody Employee employee) {
        return employeeService.add(employee);
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable int id) {
        return employeeService.delete(id);
    }

    @GetMapping("/getById/{id}")
    public DataResult<Employee> getById(@PathVariable int id) {
        return employeeService.getById(id);
    }

    @GetMapping("/activate/{activationCode}")
    public Result activator(@PathVariable String activationCode) {

        return employeeService.mailActivation(activationCode);

    }

}
