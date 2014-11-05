package cz.muni.fi.PA165.flight.service;

import cz.muni.fi.PA165.flight.transfer.FlightTO;

import java.util.List;

/**
 * User: PC
 * Date: 5. 11. 2014
 * Time: 19:54
 */
public interface FlightService {

    public void addFlight(FlightTO flight);

    public List<FlightTO> getFlightsList();
}
