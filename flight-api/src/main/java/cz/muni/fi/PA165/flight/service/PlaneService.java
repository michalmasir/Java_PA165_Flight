package cz.muni.fi.PA165.flight.service;

import cz.muni.fi.PA165.flight.transfer.FlightTO;
import cz.muni.fi.PA165.flight.transfer.PlaneTO;

import java.util.Date;
import java.util.List;

/**
 * @author Michal Galan
 */
public interface PlaneService {

    public void addPlane(PlaneTO planeTO);
    
    public void updatePlane(PlaneTO planeTO);
    
    public void revisionDone(PlaneTO planeTO, Date date);

    public List<PlaneTO> planeList();

    public PlaneTO getPlaneById(long id);

    public void removePlane(PlaneTO planeTO);

    public void landingDone(FlightTO flightTO);
}
