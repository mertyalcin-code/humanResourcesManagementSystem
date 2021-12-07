package com.example.hrms.core.validation;

import com.example.hrms.core.dataAccess.EmployeeDao;
import com.example.hrms.core.dataAccess.UserDao;
import com.example.hrms.core.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {UniqueNationalityId.UniqueNationalityIdValidator.class})
public @interface UniqueNationalityId {
    String message() default "Nationality Id must be Unique";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class UniqueNationalityIdValidator  implements ConstraintValidator<UniqueNationalityId,String> {

        UserDao userDao;
        EmployeeDao employeeDao;

        @Autowired
        public UniqueNationalityIdValidator(UserDao userDao, EmployeeDao employeeDao) {
            this.userDao = userDao;
            this.employeeDao = employeeDao;
        }

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            Employee employee = employeeDao.getEmployeeByNationalityId(value);
            return employee == null;
        }
    }
}
