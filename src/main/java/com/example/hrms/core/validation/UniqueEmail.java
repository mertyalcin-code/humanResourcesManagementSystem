package com.example.hrms.core.validation;
import com.example.hrms.core.dataAccess.UserDao;
import com.example.hrms.core.entities.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {UniqueEmail.UniqueEmailValidator.class})
public @interface UniqueEmail {
    String message() default "Email must be Unique";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class UniqueEmailValidator  implements ConstraintValidator<UniqueEmail,String> {

        @Autowired
        UserDao userDao;

        @Override
        public boolean isValid(String email, ConstraintValidatorContext context) {
            User user = userDao.getUserByEmail(email);
            return user == null;
        }
    }
}
