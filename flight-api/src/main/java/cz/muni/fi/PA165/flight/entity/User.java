package cz.muni.fi.PA165.flight.entity;

import cz.muni.fi.PA165.flight.enums.UserRole;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * User: PC
 * Date: 17. 1. 2015
 * Time: 21:20
 */
@Entity
@Table(name = "auth_user")
//Hibernate and Derby combination does not like "user" as table name, it throws SQLException :/
public class User {

    @Id
    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private UserRole userRole;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        return username.equals(user.username);

    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }
}
