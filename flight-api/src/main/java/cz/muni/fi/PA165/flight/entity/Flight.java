package cz.muni.fi.PA165.flight.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @NotNull
    private Airport from;

    @ManyToOne
    @NotNull
    private Airport to;

    @ManyToOne
    @NotNull
    private Plane plane;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date departureTime;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
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

        return id == flight.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
