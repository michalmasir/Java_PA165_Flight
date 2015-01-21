package cz.muni.fi.PA165.flight.web.validation;

import cz.muni.fi.PA165.flight.service.UserService;
import cz.muni.fi.PA165.flight.transfer.UserTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * User: PC
 * Date: 21. 1. 2015
 * Time: 11:51
 */
@Component
public class UserValidation implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors){
        UserTO userTO = (UserTO) target;

        if(userTO.getUsername() == null || userTO.getUsername().isEmpty()){
            errors.rejectValue("username", "null");
        }
        else{
            if(userService.getUserByUsername(userTO.getUsername()) != null){
                errors.rejectValue("username", "taken");
            }
        }
        if(userTO.getPassword() == null || userTO.getPassword().isEmpty()){
            errors.rejectValue("password", "null");
        }

    }
}
