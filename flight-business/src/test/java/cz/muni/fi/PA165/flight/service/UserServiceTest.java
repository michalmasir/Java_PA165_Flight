package cz.muni.fi.PA165.flight.service;

import cz.muni.fi.PA165.flight.dao.UserDAO;
import cz.muni.fi.PA165.flight.entity.User;
import cz.muni.fi.PA165.flight.service.impl.UserServiceImpl;
import cz.muni.fi.PA165.flight.transfer.UserTO;
import org.dozer.DozerBeanMapper;
import org.mockito.*;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * User: PC
 * Date: 22. 1. 2015
 * Time: 13:48
 */
@ContextConfiguration(locations = {"classpath:/application-context.xml"})
public class UserServiceTest {

    @Spy
    DozerBeanMapper dozerBeanMapper;

    @Mock
    UserDAO userDAO;

    @InjectMocks
    UserServiceImpl userService;

    UserTO userTO1;
    User user1;
    User user2;
    User user3;


    @BeforeMethod
    public void setup() {
        userTO1 = new UserTO();
        userTO1.setUsername("Anna");
        userTO1.setPassword("l33tH4x0r");
        user1 = new User();
        user1.setUsername("Anna");
        user1.setPassword("l33tH4x0r");
        user2 = new User();
        user2.setUsername("Bob");
        user3 = new User();
        user3.setUsername("Carol");
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddUser() {
        userService.addUser(userTO1);
        Mockito.verify(userDAO).addUser(user1);
    }


    @Test
    public void testRemoveUser() {
        Mockito.when(userDAO.getUserByUsername(userTO1.getUsername())).thenReturn(user1);
        userService.removeUser(userTO1);
        Mockito.verify(userDAO).deleteUser(user1);
    }


    @Test
    public void testGetUsersList() {
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);

        Mockito.when(userDAO.getAllUsers()).thenReturn(users);

        List<UserTO> userTOS = userService.getAllUsers();
        Mockito.verify(userDAO).getAllUsers();

        Assert.assertEquals(userTOS.get(0).getUsername(), users.get(0).getUsername());
        Assert.assertEquals(userTOS.get(1).getUsername(), users.get(1).getUsername());
        Assert.assertEquals(userTOS.get(2).getUsername(), users.get(2).getUsername());
        Assert.assertEquals(userTOS.size(), users.size());
    }
}
