package cz.muni.fi.PA165.flight.service;

import cz.muni.fi.PA165.flight.transfer.PlaneTO;

import java.util.Date;

/**
 * @author Michal Galan
 */
public interface PlaneService {

    public void addPlane(PlaneTO planeTO);
    
    public void updatePlane(PlaneTO planeTO);
    
    public void revisionDone(PlaneTO planeTO, Date date);

}
