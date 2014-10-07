package cz.muni.fi.PA165.flight.entity;

import javax.persistence.*;

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

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastRevisionTime;

    private long totalFlightTime;

    private long totalFlightDistance;

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPassangerSeatsCount(int passangerSeatsCount) {
        this.passangerSeatsCount = passangerSeatsCount;
    }

    public void setStaffSeatsCount(int staffSeatsCount) {
        this.staffSeatsCount = staffSeatsCount;
    }

    public void setTankCapacity(int tankCapacity) {
        this.tankCapacity = tankCapacity;
    }

    public void setFuelLeft(int fuelLeft) {
        this.fuelLeft = fuelLeft;
    }

    public void setLastRevisionTime(Date lastRevisionTime) {
        this.lastRevisionTime = lastRevisionTime;
    }

    public void increaseTotalFlightTime(long inc) {
        this.totalFlightTime += inc;
    }

    public void increaseTotalFlightDistance(long inc) {
        this.totalFlightDistance += inc;
    }
}
