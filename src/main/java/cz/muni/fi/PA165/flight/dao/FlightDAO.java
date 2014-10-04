package cz.muni.fi.PA165.flight.dao;


import cz.muni.fi.PA165.flight.entity.Flight;

import java.util.List;

public interface FlightDAO {

    public List<Flight> getAllFlights();

    public Flight getFlightById(long id);

    public void deleteFlight(Flight flight);

    public void addFlight(Flight flight);
}
