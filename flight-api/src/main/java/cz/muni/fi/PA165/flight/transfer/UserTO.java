package cz.muni.fi.PA165.flight.transfer;

import cz.muni.fi.PA165.flight.enums.UserRole;

import java.util.HashSet;
import java.util.Set;

/**
 * User: PC
 * Date: 17. 1. 2015
 * Time: 21:25
 */
public class UserTO {

    private String username;

    private String password;

    private UserRole userRole;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserTO)) return false;

        UserTO userTO = (UserTO) o;

        return username.equals(userTO.username);

    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }
}