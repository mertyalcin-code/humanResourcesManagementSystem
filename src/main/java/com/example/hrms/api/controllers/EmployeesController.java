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

    @GetMapping("/getAllEmployees")
    public DataResult<List<Employee>> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @PostMapping("/employeeRegistration")
    public Result employeeRegistration(@RequestBody Employee employee) {
        return employeeService.employeeRegistration(employee);
    }

    @DeleteMapping("/employeeDelete/{id}")
    public Result employeeDelete(@PathVariable int id) {
        return employeeService.employeeDelete(id);
    }

    @GetMapping("/getEmployeeByUserId/{id}")
    public DataResult<Employee> getEmployeeByUserId(@PathVariable int id) {
        return employeeService.getEmployeeByUserId(id);
    }

    @GetMapping("/employeeMailActivation/{activationCode}")
    public Result employeeMailActivation(@PathVariable String activationCode) {

        return employeeService.employeeMailActivation(activationCode);

    }

    @GetMapping("/getEmployeeByEmailAndNationaliyId")
    public DataResult<Employee> getEmployeeByEmailAndNationaliyId(@RequestParam String email, @RequestParam String nationalityId) {
        System.out.println(email + " " + nationalityId);
        return employeeService.getEmployeeByEmailAndNationaliyId(email, nationalityId);
    }

}
