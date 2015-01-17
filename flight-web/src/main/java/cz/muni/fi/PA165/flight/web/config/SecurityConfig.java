package cz.muni.fi.PA165.flight.web.config;

import cz.muni.fi.PA165.flight.service.UserService;
import cz.muni.fi.PA165.flight.transfer.UserRoleTO;
import cz.muni.fi.PA165.flight.transfer.UserTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * User: PC
 * Date: 17. 1. 2015
 * Time: 18:59
 */
@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userDetailsService;

    @Autowired
    /**
     * I would love to provide UserService instead of an this anonymous class, but
     * that would require UserService to import UserDetails.
     * And that's impossible as UserService is in API layer and we may not import what we need there :(
     */
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                UserTO user = userDetailsService.getUserByUsername(username);
                if (user == null) {
                    throw new UsernameNotFoundException("Username not found");
                }

                Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
                for (UserRoleTO userRole : user.getUserRoles()) {
                    grantedAuthorities.add(new SimpleGrantedAuthority(userRole.getRole()));
                }
                List<GrantedAuthority> authorities = new ArrayList<>(grantedAuthorities);
                return new User(user.getUsername(), user.getPassword(), true, true, true, true, authorities);
            }
        }).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                //.antMatchers("/**").permitAll()
                .antMatchers("/static/**").permitAll()
                .and()
                .formLogin()
        .and().authorizeRequests().anyRequest().authenticated();
    }
}
