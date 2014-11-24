package cz.muni.fi.PA165.flight.transfer;

import org.springframework.format.annotation.DateTimeFormat;

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

    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
    private Date departureTime;

    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
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
        if (!(o instanceof FlightTO)) return false;

        FlightTO flightTO = (FlightTO) o;

        if (id != flightTO.id) return false;
        if (arrivalTime != null ? !arrivalTime.equals(flightTO.arrivalTime) : flightTO.arrivalTime != null)
            return false;
        if (departureTime != null ? !departureTime.equals(flightTO.departureTime) : flightTO.departureTime != null)
            return false;
        if (from != null ? !from.equals(flightTO.from) : flightTO.from != null) return false;
        if (plane != null ? !plane.equals(flightTO.plane) : flightTO.plane != null) return false;
        if (stewards != null ? !stewards.equals(flightTO.stewards) : flightTO.stewards != null) return false;
        if (to != null ? !to.equals(flightTO.to) : flightTO.to != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

}
