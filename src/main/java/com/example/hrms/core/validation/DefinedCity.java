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
@Constraint(validatedBy = {DefinedCity.DefinedCityValidator.class})
public @interface DefinedCity {
    String message() default "City must be from the list";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class DefinedCityValidator  implements ConstraintValidator<DefinedCity,String> {

        @Autowired
        CityDao cityDao;

        @Override
        public boolean isValid(String cityName, ConstraintValidatorContext context) {
            City city = cityDao.getCityByCityName(cityName);
            return city != null;
        }
    }
}
