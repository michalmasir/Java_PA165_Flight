package cz.muni.fi.PA165.flight.dao.impl;

import cz.muni.fi.PA165.flight.dao.UserDAO;
import cz.muni.fi.PA165.flight.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * User: PC
 * Date: 17. 1. 2015
 * Time: 21:41
 */

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    public EntityManager em;

    public UserDAOImpl() {
    }

    public UserDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void addUser(User user) {
        em.persist(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return em.find(User.class, username);
    }


    @Override
    public void deleteUser(User user) {
        em.remove(user);
    }

    @Override
    public List<User> getAllUsers() {
        return em.createQuery("SELECT f FROM User f", User.class).getResultList();
    }

}
