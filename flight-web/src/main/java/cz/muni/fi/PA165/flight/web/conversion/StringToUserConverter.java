package cz.muni.fi.PA165.flight.web.conversion;

import cz.muni.fi.PA165.flight.service.UserService;
import cz.muni.fi.PA165.flight.transfer.UserTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * User: PC
 * Date: 21. 1. 2015
 * Time: 11:31
 */
@Component
public class StringToUserConverter implements Converter<String, UserTO> {

    @Autowired
    private UserService userService;

    public UserTO convert(String username) {
        return userService.getUserByUsername(username);
    }

}
