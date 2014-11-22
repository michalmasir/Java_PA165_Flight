package cz.muni.fi.PA165.flight.service;

import cz.muni.fi.PA165.flight.transfer.FlightTO;
import cz.muni.fi.PA165.flight.transfer.StewardTO;

import java.util.Date;
import java.util.List;

/**
 * User: PC
 * Date: 5. 11. 2014
 * Time: 19:54
 */
public interface FlightService {

    public void addFlight(FlightTO flight);

    public List<FlightTO> getFlightsList();

    public void removeFlight(FlightTO flightTO);

    public void updateFlight(FlightTO flightTO);

    public FlightTO getFlightById(long id);

    public List<FlightTO> getFlightsByDepartureDate(Date from, Date to);

    public List<FlightTO> getFlightsByArrivalDate(Date from, Date to);

    public boolean assignStewardToFlight(FlightTO flightTO, StewardTO stewardTO);
    
    public void landingDone(FlightTO flightTO);

}
