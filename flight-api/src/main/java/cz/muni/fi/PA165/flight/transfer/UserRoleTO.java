package cz.muni.fi.PA165.flight.transfer;


/**
 * User: PC
 * Date: 17. 1. 2015
 * Time: 21:25
 */
public class UserRoleTO {

    private long id;

    private UserTO user;

    private String role;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserTO getUser() {
        return user;
    }

    public void setUser(UserTO user) {
        this.user = user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRoleTO)) return false;

        UserRoleTO userRole = (UserRoleTO) o;

        return id == userRole.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
