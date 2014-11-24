package cz.muni.fi.PA165.flight.web.validation;

import cz.muni.fi.PA165.flight.entity.Flight;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * User: PC
 * Date: 22. 11. 2014
 * Time: 18:45
 */
public class FlightValidation  implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Flight.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Flight flight = (Flight) target;
    }
}
