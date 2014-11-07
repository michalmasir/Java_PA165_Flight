package cz.muni.fi.PA165.flight.transfer;

import java.util.HashSet;
import java.util.Set;

/**
 * User: PC
 * Date: 5. 11. 2014
 * Time: 20:38
 */
public class StewardTO {

    private long id;

    private String firstName;

    private String lastName;

    private Set<FlightTO> flights = new HashSet<FlightTO>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Set<FlightTO> getFlights() {
        return flights;
    }

    public void setFlights(Set<FlightTO> flights) {
        this.flights = flights;
    }
}
