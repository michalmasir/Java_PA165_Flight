package cz.muni.fi.PA165.flight.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Vladimir Jurenka
 */
@Entity
public class Flight {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private Airport from;

    @ManyToOne
    private Airport to;

    @ManyToOne
    private Plane plane;

    @Temporal(TemporalType.TIMESTAMP)
    private Date departureTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date arrivalTime;

    @ManyToMany
    @JoinTable(
            name = "flight_stewards",
            joinColumns = {@JoinColumn(name = "flight_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "steward_id", referencedColumnName = "id")})
    private Set<Steward> stewards = new HashSet<Steward>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Airport getFrom() {
        return from;
    }

    public void setFrom(Airport from) {
        this.from = from;
    }

    public Airport getTo() {
        return to;
    }

    public void setTo(Airport to) {
        this.to = to;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
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

    public Set<Steward> getStewards() {
        return stewards;
    }

    public void setStewards(Set<Steward> stewards) {
        this.stewards = stewards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flight)) return false;

        Flight flight = (Flight) o;

        if (id != flight.id) return false;
        if (arrivalTime != null ? !arrivalTime.equals(flight.arrivalTime) : flight.arrivalTime != null) return false;
        if (departureTime != null ? !departureTime.equals(flight.departureTime) : flight.departureTime != null)
            return false;
        if (from != null ? !from.equals(flight.from) : flight.from != null) return false;
        if (plane != null ? !plane.equals(flight.plane) : flight.plane != null) return false;
        if (stewards != null ? !stewards.equals(flight.stewards) : flight.stewards != null) return false;
        if (to != null ? !to.equals(flight.to) : flight.to != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
