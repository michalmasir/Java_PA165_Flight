package cz.muni.fi.PA165.flight.service;

import cz.muni.fi.PA165.flight.transfer.AirportTO;

import java.util.List;

/**
 * Created by Misko on 6.11.2014.
 */
public interface AirportService {
    //add airport
    public void addAirport(AirportTO airport);
    //remove airport
    public void removeAirport(AirportTO airport);
    //update airport
    public AirportTO updateAirport(AirportTO airportTO);
    //getAll airports
    public List<AirportTO> getAirportsList();

}
