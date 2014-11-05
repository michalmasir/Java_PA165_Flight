package cz.muni.fi.PA165.flight.transfer;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * User: PC
 * Date: 5. 11. 2014
 * Time: 20:38
 */
public class FlightTO {

    private long id;

    private AirportTO from;

    private AirportTO to;

    private PlaneTO plane;

    private Date departureTime;

    private Date arrivalTime;

    private Set<StewardTO> stewards = new HashSet<StewardTO>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public AirportTO getFrom() {
        return from;
    }

    public void setFrom(AirportTO from) {
        this.from = from;
    }

    public AirportTO getTo() {
        return to;
    }

    public void setTo(AirportTO to) {
        this.to = to;
    }

    public PlaneTO getPlane() {
        return plane;
    }

    public void setPlane(PlaneTO plane) {
        this.plane = plane;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Set<StewardTO> getStewards() {
        return stewards;
    }

    public void setStewards(Set<StewardTO> stewards) {
        this.stewards = stewards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FlightTO flight = (FlightTO) o;

        if (id != flight.id || id == 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

}
