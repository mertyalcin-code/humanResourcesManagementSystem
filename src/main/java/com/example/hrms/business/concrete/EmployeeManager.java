package com.example.hrms.business.concrete;

import com.example.hrms.business.abstracts.EmployeeService;
import com.example.hrms.core.concrete.*;
import com.example.hrms.core.dataAccess.EmployeeDao;
import com.example.hrms.core.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeManager implements EmployeeService {

    private final EmployeeDao employeeDao;
    private final ValidationManager userCheckManager;
    private final ActivationMailSender activationMailSender;

    private final UserManager userManager;
    private final Loggers loggers;

    @Autowired
    public EmployeeManager(EmployeeDao employeeDao, ValidationManager userCheckManager, ActivationMailSender activationMailSender,
                           UserManager userManager, Loggers loggers) {
        this.employeeDao = employeeDao;
        this.userCheckManager = userCheckManager;
        this.activationMailSender = activationMailSender;

        this.userManager = userManager;
        this.loggers = loggers;
    }

    @Override
    public DataResult<List<Employee>> getAllEmployees() {
        List<Employee> employees = this.employeeDao.findAll();
        if (employees.size() < 1) {
            return new ErrorDataResult<>("Not found");
        } else {
            loggers.log("All employees listed", "getAllEmployees");
            return new SuccessDataResult<>(employees, employeeDao.findAll().size() + " people Listed");
        }


    }

    @Override
    public Result employeeRegistration(Employee employee) {

        if (!userCheckManager.checkNationalityIdValid(
                employee.getNationalityId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getBirthYear())) {
            return new ErrorResult("Your Nationality ID number is incorrect");
        } else {
            employeeDao.save(employee);

            loggers.log(employee,
                    "Registration: " + employee.getEmail() + " " + employee.getFirstName() + " " + employee.getLastName(),
                    "employeeRegistration");

            activationMailSender.SendActivationMail(employee);//şuan için veri tabanına aktivasyon kodu yazıyor.
            return new SuccessResult("Registration Successful: activation code has been sent to your e-mail address.");
        }


    }

    @Override
    public Result employeeDelete(int id) {
        Employee employee = employeeDao.getEmployeeByUserId(id);
        if (employee == null) {
            return new ErrorResult("No employee with this id: " + id);
        } else {
            employeeDao.delete(employee);
            loggers.log(employee,
                    "Delete Registration: " + employee.getEmail() + " " + employee.getFirstName() + " " + employee.getLastName(),
                    "employeeDelete");
            return new SuccessResult("Deleted: " + employee.getFirstName() + " " + employee.getLastName());
        }


    }


    @Override
    public DataResult<Employee> getEmployeeByUserId(int id) {
        Employee employee = employeeDao.getEmployeeByUserId(id);
        if (employee == null) {
            return new ErrorDataResult<>("No employee with this id");
        } else {
            loggers.log(
                    employee,
                    "Found by Id: "
                            + employee.getEmail()
                            + " "
                            + employee.getFirstName()
                            + " "
                            + employee.getLastName(),
                    "getEmployeeById");
            return new SuccessDataResult<>(employee, "Employee data has been fetched successfully.");
        }

    }

    @Override
    public DataResult<Employee> getEmployeeByEmail(String email) {
        Employee employee = employeeDao.getEmployeeByEmail(email);
        if (employee == null) {
            return new ErrorDataResult<>("No employee with this email");
        } else {
            loggers.log(
                    employee,
                    "Found by email: "
                            + employee.getEmail()
                            + " "
                            + employee.getFirstName()
                            + " "
                            + employee.getLastName(),
                    "getEmployeeByEmail");
            return new SuccessDataResult<>(employee, "Listed");
        }

    }

    @Override
    public DataResult<Employee> getEmployeeByEmailAndNationaliyId(String email, String nationalityId) {
        Employee employee = employeeDao.getEmployeeByEmailAndNationalityId(email, nationalityId);
        if (employee == null) {
            return new ErrorDataResult<>("Not Found");
        } else {
            loggers.log(
                    employee,
                    "Found by email and NationalityId: "
                            + employee.getEmail()
                            + " "
                            + employee.getFirstName()
                            + " "
                            + employee.getLastName(),
                    "getEmployeeByEmailAndNationaliyId");
            return new SuccessDataResult<>(employee, "Listed");
        }

    }

    @Override
    public Result employeeMailActivation(String activationCode) {
        return userManager.userMailActivation(activationCode);
    }
}