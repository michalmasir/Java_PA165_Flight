package cz.muni.fi.PA165.flight.web.conversion;

import cz.muni.fi.PA165.flight.enums.UserRole;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * User: PC
 * Date: 21. 1. 2015
 * Time: 11:39
 */

@Component
public class StringToUserRoleConverter implements Converter<String, UserRole> {

    public UserRole convert(String role) {
        return UserRole.valueOf(role);
    }

}

