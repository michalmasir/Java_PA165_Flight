package cz.muni.fi.PA165.flight.web.validation;

import cz.muni.fi.PA165.flight.transfer.AirportTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by MICHAL on 28. 11. 2014.
 */
public class AirportValidation implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return AirportTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AirportTO airportTO = (AirportTO) target;

        if(airportTO.getState().isEmpty()) {
            errors.rejectValue("state", "empty");
        }

       if(airportTO.getName().isEmpty()) {
            errors.rejectValue("name", "empty");
        }

       if(airportTO.getCity().isEmpty()) {
            errors.rejectValue("city", "empty");
        }

    }
}
