package cz.muni.fi.PA165.flight.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Michal Galan
 */
@Entity
public class Plane {

    @Id
    @GeneratedValue
    private long id;

    private String manufacturer;

    private String type;

    private int passangerSeatsCount;

    private int staffSeatsCount;

    /**
     * Capacity of the benzine tank.
     */
    private int tankCapacity;

    /**
     * How full the tank is.
     */
    private int fuelLeft;

    private Date lastRevision;

    private long flightTime;

    private long flightDistance;
}
