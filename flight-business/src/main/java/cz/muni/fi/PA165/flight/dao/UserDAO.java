package cz.muni.fi.PA165.flight.dao;

import cz.muni.fi.PA165.flight.entity.User;

import java.util.List;

/**
 * User: PC
 * Date: 17. 1. 2015
 * Time: 21:41
 */
public interface UserDAO {

    public void addUser(User user);

    public User getUserByUsername(String username);

    /**
     * Returns the list of all users in the application.
     *
     * @return list of all users
     */
    public List<User> getAllUsers();

    /**
     * Deletes a User with the specified id.
     *
     * @param user User to delete
     */
    public void deleteUser(User user);
}
