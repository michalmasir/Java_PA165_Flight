package cz.muni.fi.PA165.flight.dao;


import cz.muni.fi.PA165.flight.entity.Flight;

import java.util.List;

/**
 * This interfaces specifies the methods that a {@link cz.muni.fi.PA165.flight.entity.Flight} data access object
 * must implement.
 *
 * @author Vladimir Jurenka
 */
public interface FlightDAO {

    /**
     * Returns the list of all flights in the application.
     *
     * @return list of all flights
     */
    public List<Flight> getAllFlights();

    /**
     * Finds and returns a Flight with the specified id.
     *
     * @param id id of the Flight to find
     * @return the found Flight or null if no such flight exists
     */
    public Flight getFlightById(long id);

    /**
     * Deletes a Flight with the specified id.
     *
     * @param flight Flight to delete
     */
    public void deleteFlight(Flight flight);

    /**
     * Saves a Flight object.
     *
     * @param flight Flight to save
     */
    public void addFlight(Flight flight);
}
