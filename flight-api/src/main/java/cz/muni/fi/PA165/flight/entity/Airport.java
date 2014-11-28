package cz.muni.fi.PA165.flight.entity;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * This class represents an airport, or destination from which planes start and end their flights
 * @author Michal Mašír
 */

@Entity
public class Airport {

    @Id
    @GeneratedValue
    private long id;

    /**
     * Destination name
     */
    @Column
    private String name;
    /**
     * State, where the airport is located
     */
    @Column
    private String state;
    /**
     * City in a state where the airport is located
     */
    @Column
    private String city;

    /**
     * List of destination with arriving flights
     */
    @OneToMany(mappedBy = "from", cascade = CascadeType.REMOVE)
    private Set<Flight> flightsFrom = new HashSet<Flight>();

    /**
     * List of destination with departing flights
     */
    @OneToMany(mappedBy = "to", cascade = CascadeType.REMOVE)
    private Set<Flight> flightsTo = new HashSet<Flight>();

    /**
     * Returns airport ID
     * @return
     */
    public long getId() {
        return id;
    }

    /**
     * Sets airports ID
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets airports city location
     * @return
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets airports city location
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Returns list of arriving flights
     * @return
     */
    public Set<Flight> getFlightsFrom() {
        return flightsFrom;
    }

    /**
     * Sets List of arriving flights
     * @param flightsFrom
     */
    public void setFlightsFrom(Set<Flight> flightsFrom) {
        this.flightsFrom = flightsFrom;
    }

    /**
     * Returns list of departing flights
     * @return
     */
    public Set<Flight> getFlightsTo() {
        return flightsTo;
    }

    /**
     * Sets list of departing flights
     * @return
     */
    public void setFlightsTo(Set<Flight> flightsTo) {
        this.flightsTo = flightsTo;
    }

    /**
     * Returns airports name
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Sets airports name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns airports state
     * @return
     */
    public String getState() {
        return state;
    }

    /**
     * Sets airports state
     * @param state
     */
    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Airport)) return false;

        Airport airport = (Airport) o;

        if (id != airport.id) return false;
        if (city != null ? !city.equals(airport.city) : airport.city != null) return false;
        if (flightsFrom != null ? !flightsFrom.equals(airport.flightsFrom) : airport.flightsFrom != null) return false;
        if (flightsTo != null ? !flightsTo.equals(airport.flightsTo) : airport.flightsTo != null) return false;
        if (name != null ? !name.equals(airport.name) : airport.name != null) return false;
        if (state != null ? !state.equals(airport.state) : airport.state != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Airport{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}