package cz.muni.fi.PA165.flight.transfer;

import java.util.HashSet;
import java.util.Set;

/**
 * User: PC
 * Date: 5. 11. 2014
 * Time: 20:38
 */
public class AirportTO {

    private long id;

    private String name;

    private String state;

    private String city;

    private Set<FlightTO> flightsFrom = new HashSet<FlightTO>();

    private Set<FlightTO> flightsTo = new HashSet<FlightTO>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Set<FlightTO> getFlightsFrom() {
        return flightsFrom;
    }

    public void setFlightsFrom(Set<FlightTO> flightsFrom) {
        this.flightsFrom = flightsFrom;
    }

    public Set<FlightTO> getFlightsTo() {
        return flightsTo;
    }

    public void setFlightsTo(Set<FlightTO> flightsTo) {
        this.flightsTo = flightsTo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AirportTO)) return false;

        AirportTO airportTO = (AirportTO) o;

        if (id != airportTO.id) return false;
        if (city != null ? !city.equals(airportTO.city) : airportTO.city != null) return false;
        if (flightsFrom != null ? !flightsFrom.equals(airportTO.flightsFrom) : airportTO.flightsFrom != null)
            return false;
        if (flightsTo != null ? !flightsTo.equals(airportTO.flightsTo) : airportTO.flightsTo != null) return false;
        if (name != null ? !name.equals(airportTO.name) : airportTO.name != null) return false;
        if (state != null ? !state.equals(airportTO.state) : airportTO.state != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
    
}
