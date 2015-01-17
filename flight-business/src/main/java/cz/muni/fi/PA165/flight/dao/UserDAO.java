package cz.muni.fi.PA165.flight.dao;

import cz.muni.fi.PA165.flight.entity.User;

/**
 * User: PC
 * Date: 17. 1. 2015
 * Time: 21:41
 */
public interface UserDAO {

    public void addUser(User user);

    public User getUserByUsername(String username);
}
