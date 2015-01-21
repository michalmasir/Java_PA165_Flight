package cz.muni.fi.PA165.flight.service;

import cz.muni.fi.PA165.flight.transfer.UserTO;

import java.util.List;

/**
 * User: PC
 * Date: 17. 1. 2015
 * Time: 21:25
 */
public interface UserService {

    public UserTO getUserByUsername(String username);

    public void addUser(UserTO userTO);

    public List<UserTO> getAllUsers();

    public void removeUser(UserTO userTO);

}
