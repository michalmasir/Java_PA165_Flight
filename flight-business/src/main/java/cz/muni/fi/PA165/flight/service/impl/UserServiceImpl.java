package cz.muni.fi.PA165.flight.service.impl;

import cz.muni.fi.PA165.flight.dao.UserDAO;
import cz.muni.fi.PA165.flight.service.UserService;
import cz.muni.fi.PA165.flight.transfer.UserRoleTO;
import cz.muni.fi.PA165.flight.transfer.UserTO;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public UserTO getUserByUsername(String username) {
        return dozerBeanMapper.map(userDAO.getUserByUsername(username), UserTO.class);
    }

    @Override
    @Transactional
    public void addUser(UserTO userTO) {
        cz.muni.fi.PA165.flight.entity.User user = dozerBeanMapper.map(userTO, cz.muni.fi.PA165.flight.entity.User.class);
        userDAO.addUser(user);
    }
}
