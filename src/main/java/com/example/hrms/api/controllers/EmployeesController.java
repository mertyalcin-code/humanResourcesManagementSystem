package com.example.hrms.api.controllers;

import com.example.hrms.business.abstracts.EmployeeService;
import com.example.hrms.core.concrete.DataResult;
import com.example.hrms.core.concrete.ErrorDataResult;
import com.example.hrms.core.concrete.Result;
import com.example.hrms.core.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<?> employeeRegistration(@Valid @RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.employeeRegistration(employee));
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

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorDataResult<Object> handleValidationException2(DataIntegrityViolationException exceptions) {

        ErrorDataResult<Object> errors = new ErrorDataResult<Object>(exceptions.getMessage(), "Doğrulama hataları");

        return errors;

    }
}
