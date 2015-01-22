package cz.muni.fi.PA165.flight.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author  Michal Hruby
 */

@Entity
public class Steward {

    @Id
    @GeneratedValue
    private long id;

    private String firstName;

    private String lastName;

    @ManyToMany
    @JoinTable(
            name = "flight_stewards",
            joinColumns = {@JoinColumn(name = "steward_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "flight_id", referencedColumnName = "id")})
    private Set<Flight> flights = new HashSet<Flight>();

    public void setFirstName(String first_name) {
        this.firstName = first_name;
    }


    public String getFirstName() {
        return firstName;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String last_name) {
        this.lastName = last_name;
    }


    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }

    public Set<Flight> getFlights() {
        return flights;
    }

    public void setFlights(Set<Flight> flights) {
        this.flights = flights;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Steward)) return false;

        Steward steward = (Steward) o;

        return id == steward.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }


}
