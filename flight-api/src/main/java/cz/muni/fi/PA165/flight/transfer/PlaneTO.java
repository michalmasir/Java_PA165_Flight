package cz.muni.fi.PA165.flight.transfer;

import java.util.Date;
import java.util.Set;

/**
 * @author Michal Galan
 */
public class PlaneTO {

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

    private Date lastRevisionTime;

    private long totalFlightTime;

    private long totalFlightDistance;

    public void setId(long id) {
        this.id = id;
    }

    public Set<FlightTO> getFlights() {
        return flights;
    }

    public void setFlights(Set<FlightTO> flights) {
        this.flights = flights;
    }

    private Set<FlightTO> flights = new java.util.HashSet<>();

    /* METHODS */

    /**
     * Sets plane manufacturer, ex. "Airbus".
     * @param manufacturer name of the plane manufacturer
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * Sets plane type or model, ex. "320".
     * @param type type or model of the plane
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Sets maximum of passanger seats.
     * @param passangerSeatsCount max passanger seats count in the plane
     */
    public void setPassangerSeatsCount(int passangerSeatsCount) {
        this.passangerSeatsCount = passangerSeatsCount;
    }

    /**
     * Sets maximum of staff seats, excluding pilot and navigator seats.
     * @param staffSeatsCount max seats count for staff, excludung pilot and navigator seats
     */
    public void setStaffSeatsCount(int staffSeatsCount) {
        this.staffSeatsCount = staffSeatsCount;
    }

    /**
     * Sets capacity of plane's tank.
     * @param tankCapacity capacity of the plane tank
     */
    public void setTankCapacity(int tankCapacity) {
        this.tankCapacity = tankCapacity;
    }

    /**
     * Sets fuel present in a plane.
     * @param fuelLeft fuel amount present in the plane
     */
    public void setFuelLeft(int fuelLeft){
        this.fuelLeft = fuelLeft;
    }

    /**
     * Add fuel to plane's tank.
     * @param fuel fuel amount to tank to the plane
     */
    public void tankFuel(int fuel) {
        this.fuelLeft += fuel;
    }

    /**
     * Sets the date of last plane revision.
     * @param lastRevisionTime Date of the last revision
     */
    public void setLastRevisionTime(Date lastRevisionTime) {
        this.lastRevisionTime = lastRevisionTime;
    }

    /**
     * Increases total flight time with specified time.
     * @param inc time
     */
    public void increaseTotalFlightTime(long inc) {
        this.totalFlightTime += inc;
    }

    /**
     * Increases total flight distance with specified distance.
     * @param inc distance
     */
    public void increaseTotalFlightDistance(long inc) {
        this.totalFlightDistance += inc;
    }

    public Date getLastRevisionTime() {
        return lastRevisionTime;
    }

    public long getTotalFlightDistance() {
        return totalFlightDistance;
    }

    public long getTotalFlightTime() {
        return totalFlightTime;
    }

    /**
     * Returns plane ID.
     * @return
     */
    public long getId() {
        return this.id;
    }

    /**
     * Returns plane manufacturer name.
     * @return
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * Returns plane type or model.
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * Returns plane passanger seats count.
     * @return
     */
    public int getPassangerSeatsCount() {
        return passangerSeatsCount;
    }

    /**
     * Returns plane staff count, excluding pilot and navigator seats.
     * @return
     */
    public int getStaffSeatsCount() {
        return staffSeatsCount;
    }

    /**
     * Returns capacity of plane tank.
     * @return
     */
    public int getTankCapacity() {
        return tankCapacity;
    }

    /**
     * Returns fuel present in a plane.
     * @return
     */
    public int getFuelLeft() {
        return fuelLeft;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlaneTO)) return false;

        PlaneTO plane = (PlaneTO) o;

        if (fuelLeft != plane.fuelLeft) return false;
        if (id != plane.id) return false;
        if (passangerSeatsCount != plane.passangerSeatsCount) return false;
        if (staffSeatsCount != plane.staffSeatsCount) return false;
        if (tankCapacity != plane.tankCapacity) return false;
        if (totalFlightDistance != plane.totalFlightDistance) return false;
        if (totalFlightTime != plane.totalFlightTime) return false;
        if (flights != null ? !flights.equals(plane.flights) : plane.flights != null) return false;
        if (lastRevisionTime != null ? !lastRevisionTime.equals(plane.lastRevisionTime) : plane.lastRevisionTime != null)
            return false;
        if (manufacturer != null ? !manufacturer.equals(plane.manufacturer) : plane.manufacturer != null) return false;
        if (type != null ? !type.equals(plane.type) : plane.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

}
