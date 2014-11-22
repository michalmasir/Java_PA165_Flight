package cz.muni.fi.PA165.flight.dao;

import cz.muni.fi.PA165.flight.entity.Airport;
import cz.muni.fi.PA165.flight.entity.Flight;

import java.util.List;

/**
 * Created by Michal Mašír on 9. 10. 2014.
 */
public interface AirportDAO {
    /**
     * Returns the list of all airports in the system
     * @return list of all airports
     */
    public List<Airport> getAllAirports();

    /**
     * Returns a airport based on its ID
     * @param id ID of searched airport
     * @return airport
     */
    public Airport getAirportById(long id);

    /**
     * Deletes an airport
     * @param airport Airports reference we whant do delete
     */
    public void deleteAirport(Airport airport);

    /**
     * Adds a new airport
     * @param airport instance of the airport, we want to add
     */
    public void addAirport(Airport airport);

    /**
     * Ads a new flight
     * @param airport
     */
    public Airport updateAirport(Airport airport);

}
