package cz.muni.fi.PA165.flight.service.impl;

import cz.muni.fi.PA165.flight.dao.UserDAO;
import cz.muni.fi.PA165.flight.entity.User;
import cz.muni.fi.PA165.flight.service.UserService;
import cz.muni.fi.PA165.flight.transfer.UserTO;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * User: PC
 * Date: 17. 1. 2015
 * Time: 21:30
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private DozerBeanMapper dozerBeanMapper;


    @Override
    @Transactional
    /* No @Secured here or we can't login :)
     */
    public UserTO getUserByUsername(String username) {
        User user = userDAO.getUserByUsername(username);
        if(user == null){
            return null;
        }

        return dozerBeanMapper.map(user, UserTO.class);
    }

    @Override
    @Transactional
    @Secured("ROLE_ADMIN")
    public void addUser(UserTO userTO) {
        cz.muni.fi.PA165.flight.entity.User user = dozerBeanMapper.map(userTO, cz.muni.fi.PA165.flight.entity.User.class);
        user.setPassword(getPasswordEncoder().encode(user.getPassword()));
        userDAO.addUser(user);
    }


    @Override
    @Transactional
    @Secured("ROLE_ADMIN")
    public List<UserTO> getAllUsers() {
        List<UserTO> userTOs = new ArrayList<>();

        for (cz.muni.fi.PA165.flight.entity.User user : userDAO.getAllUsers()) {
            userTOs.add(dozerBeanMapper.map(user, UserTO.class));
        }
        return userTOs;
    }

    @Override
    @Transactional
    @Secured("ROLE_ADMIN")
    public void removeUser(UserTO userTO) {
        userDAO.deleteUser(userDAO.getUserByUsername(userTO.getUsername()));
    }


    protected PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
