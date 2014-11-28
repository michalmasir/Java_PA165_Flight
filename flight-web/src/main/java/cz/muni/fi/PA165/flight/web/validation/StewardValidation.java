package cz.muni.fi.PA165.flight.web.validation;

import cz.muni.fi.PA165.flight.transfer.StewardTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by M on 28.11.2014.
 */
public class StewardValidation implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return StewardTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors){
        StewardTO stewardTO = (StewardTO) target;

        if(stewardTO.getFirstName().isEmpty()){
            errors.rejectValue("firstName", "is.empty");
        }

        if(stewardTO.getLastName().isEmpty()){
            errors.rejectValue("lastName", "is.empty");
        }

    }

}
