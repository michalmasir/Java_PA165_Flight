package cz.muni.fi.PA165.flight.web.validation;

import cz.muni.fi.PA165.flight.transfer.PlaneTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Michal Galan
 */
public class PlaneValidation implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return PlaneTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PlaneTO planeTO = (PlaneTO) target;

        if(planeTO.getManufacturer().isEmpty()) {
            errors.rejectValue("manufacturer", "empty");
        }

       if(planeTO.getType().isEmpty()) {
            errors.rejectValue("name", "empty");
        }
    }
}
