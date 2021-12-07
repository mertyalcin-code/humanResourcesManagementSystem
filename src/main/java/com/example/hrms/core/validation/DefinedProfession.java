package com.example.hrms.core.validation;

import com.example.hrms.dataAccess.abstracts.CityDao;
import com.example.hrms.dataAccess.abstracts.ProfessionDao;
import com.example.hrms.entities.City;
import com.example.hrms.entities.Profession;
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
@Constraint(validatedBy = {DefinedProfession.DefinedProfessionValidator.class})
public @interface DefinedProfession {
    String message() default "Position must be from the list";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class DefinedProfessionValidator  implements ConstraintValidator<DefinedProfession,String> {

        @Autowired
        ProfessionDao professionDao;

        @Override
        public boolean isValid(String title, ConstraintValidatorContext context) {
            Profession profession = professionDao.findProfessionByTitle(title);
            return profession != null;
        }
    }
}
