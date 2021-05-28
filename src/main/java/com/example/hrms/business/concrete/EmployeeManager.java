package com.example.hrms.business.concrete;

import com.example.hrms.business.abstracts.EmployeeService;
import com.example.hrms.core.concrete.*;
import com.example.hrms.dataAccess.abstracts.EmployeeDao;
import com.example.hrms.entities.concrete.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeManager implements EmployeeService {

    private final EmployeeDao employeeDao;
    private final UserCheckManager userCheckManager;
    private final ActivationMailSender activationMailSender;

    private final UserManager userManager;
    private final Loggers loggers;

    @Autowired
    public EmployeeManager(EmployeeDao employeeDao, UserCheckManager userCheckManager, ActivationMailSender activationMailSender,
                           UserManager userManager, Loggers loggers) {
        this.employeeDao = employeeDao;
        this.userCheckManager = userCheckManager;
        this.activationMailSender = activationMailSender;

        this.userManager = userManager;
        this.loggers = loggers;
    }

    @Override
    public DataResult<List<Employee>> getAllEmployees() {
        try {
            loggers.log("All employees listed", "getAllEmployees");
            return new SuccessDataResult<>(
                    this.employeeDao.findAll(), employeeDao.findAll().size() + " people Listed");

        } catch (Exception exception) {
            return new ErrorDataResult<>(exception.getMessage());
        }
    }

    @Override
    public Result employeeRegistration(Employee employee) {
        if (!userCheckManager.checkMailRegular(employee.getEmail())) {
            return new ErrorResult("Incorrect E-mail");
        }
        if (userCheckManager.checkMailAlreadyExist(employee.getEmail())) {
            return new ErrorResult("Previously registered with this email");
        }
        if (!userCheckManager.checkNationalityIdValid(
                employee.getNationalityId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getBirthYear())) {
            return new ErrorResult("Your Nationality ID number is incorrect");
        }
        if (!userCheckManager.checkFirstNameRegular(employee.getFirstName())) {
            return new ErrorResult("Your First name is Incorrect");
        }
        if (!userCheckManager.checkLastNameRegular(employee.getLastName())) {
            return new ErrorResult("Your Last Name is Incorrect");
        }
        if (userCheckManager.checkNationalityIdAlreadyExist((employee.getNationalityId()))) {
            return new ErrorResult("Previously registered with this Nationality ID number");
        }
        if (!userCheckManager.checkPasswordRegular(employee.getPassword())) {
            return new ErrorResult("Your password is incorrect");
        }
        if (!userCheckManager.checkControlPasswordSame(employee.getPassword(), employee.getControlPassword())) {
            return new ErrorResult("Your passwords do not match");
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