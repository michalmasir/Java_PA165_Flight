package cz.muni.fi.PA165.flight.service;

import cz.muni.fi.PA165.flight.transfer.AirportTO;
import cz.muni.fi.PA165.flight.transfer.FlightTO;
import cz.muni.fi.PA165.flight.transfer.StewardTO;

import java.util.List;

/**
 * Created by Misko on 6.11.2014.
 */
public interface AirportService {
    //add airport
    public void addAirpot(AirportTO airport);
    //remove airport
    public void removeAirpot(AirportTO airport);
    //update airport
    public AirportTO updateAirpot(AirportTO airportTO);
    //getAll airports
    public List<AirportTO> getAirportsList();

}