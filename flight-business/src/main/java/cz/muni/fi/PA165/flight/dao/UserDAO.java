package cz.muni.fi.PA165.flight.dao;

import cz.muni.fi.PA165.flight.entity.User;

import java.util.List;

/**
 * This interfaces specifies the methods that a {@link cz.muni.fi.PA165.flight.entity.User} data access object
 * must implement.
 *
 * @author Vladimir Jurenka
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
