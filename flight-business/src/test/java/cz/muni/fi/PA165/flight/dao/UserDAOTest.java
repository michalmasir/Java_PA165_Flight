package cz.muni.fi.PA165.flight.dao;

import cz.muni.fi.PA165.flight.dao.impl.UserDAOImpl;
import cz.muni.fi.PA165.flight.entity.User;
import cz.muni.fi.PA165.flight.enums.UserRole;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

/**
 * User: PC
 * Date: 22. 1. 2015
 * Time: 13:40
 */
@ContextConfiguration(locations = {"classpath:/application-context.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserDAOTest extends AbstractTestNGSpringContextTests {

    @PersistenceUnit
    private EntityManagerFactory emf;

    private User dummyUser(){
        User u1 = new User();
        u1.setUsername("test1");
        u1.setPassword("test1");
        u1.setUserRole(UserRole.USER);
        return u1;
    }

    @Test
    public void testGetAllUsers() throws Exception {
        EntityManager em = emf.createEntityManager();
        UserDAO userDAO;
        
        User u1 = dummyUser();
        
        User u2 = new User();
        u2.setUsername("test2");
        u2.setPassword("test2");
        u2.setUserRole(UserRole.USER);
        
        em.getTransaction().begin();
        userDAO = new UserDAOImpl(em);
        userDAO.addUser(u1);
        userDAO = new UserDAOImpl(em);
        userDAO.addUser(u2);
        em.getTransaction().commit();

        userDAO = new UserDAOImpl(em);

        List<User> users = userDAO.getAllUsers();

        Assert.assertEquals(users.size(), 2);
    }
    
    @Test
    public void testDeleteUser() throws Exception {
        User user = dummyUser();

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        UserDAO userDAO = new UserDAOImpl(em);
        userDAO.addUser(user);
        String username = user.getUsername();
        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        userDAO = new UserDAOImpl(em);
        User testedUser = userDAO.getUserByUsername(username);
        em.close();

        Assert.assertNotNull(testedUser);

        em = emf.createEntityManager();
        em.getTransaction().begin();
        userDAO = new UserDAOImpl(em);
        testedUser = userDAO.getUserByUsername(testedUser.getUsername());
        userDAO.deleteUser(testedUser);
        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        userDAO = new UserDAOImpl(em);
        User deletedUser = userDAO.getUserByUsername(testedUser.getUsername());

        Assert.assertNull(deletedUser);
    }

    @Test
    public void testAddUser() throws Exception {
        User user = dummyUser();

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        UserDAO userDAO = new UserDAOImpl(em);
        userDAO.addUser(user);
        String username = user.getUsername();

        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        userDAO = new UserDAOImpl(em);
        User testedUser = userDAO.getUserByUsername(username);
        em.close();

        Assert.assertNotNull(testedUser);
    }
}
