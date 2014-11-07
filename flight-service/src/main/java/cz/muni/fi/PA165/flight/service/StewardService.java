package cz.muni.fi.PA165.flight.service;

import cz.muni.fi.PA165.flight.transfer.FlightTO;
import cz.muni.fi.PA165.flight.transfer.StewardTO;

import java.util.List;
import java.util.Set;

/**
 * Created by M on 7.11.2014.
 */
public interface StewardService {

    public List<StewardTO> getAllStewards();

    public StewardTO getStewardById(long id );

    public void deleteSteward(StewardTO stewardTO);

    public void addSteward(StewardTO stewardTO);

}
