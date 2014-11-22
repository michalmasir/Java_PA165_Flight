package cz.muni.fi.PA165.flight.service.impl;

import cz.muni.fi.PA165.flight.dao.FlightDAO;
import cz.muni.fi.PA165.flight.entity.Flight;
import cz.muni.fi.PA165.flight.entity.Steward;
import cz.muni.fi.PA165.flight.service.FlightService;
import cz.muni.fi.PA165.flight.service.PlaneService;
import cz.muni.fi.PA165.flight.transfer.FlightTO;
import cz.muni.fi.PA165.flight.transfer.PlaneTO;
import cz.muni.fi.PA165.flight.transfer.StewardTO;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * User: PC
 * Date: 5. 11. 2014
 * Time: 19:54
 */
@Service
public class FlightServiceImpl implements FlightService {

    @Inject
    private FlightDAO flightDAO;
    
    @Inject
    private PlaneService planeService;

    @Inject
    private DozerBeanMapper dozerBeanMapper;

    @Override
    @Transactional
    public void addFlight(FlightTO flightTO) {
        Flight flight = dozerBeanMapper.map(flightTO, Flight.class);
        flightDAO.addFlight(flight);
    }

    @Override
    public List<FlightTO> getFlightsList() {
        List<FlightTO> flightTOs = new ArrayList<>();

        for(Flight flight : flightDAO.getAllFlights()){
            flightTOs.add(dozerBeanMapper.map(flight, FlightTO.class));
        }
        return flightTOs;
    }

    @Override
    @Transactional
    public void removeFlight(FlightTO flightTO) {
        Flight flight = dozerBeanMapper.map(flightTO, Flight.class);
        flightDAO.deleteFlight(flight);
    }

    @Override
    @Transactional
    public void updateFlight(FlightTO flightTO) {
        Flight flight = dozerBeanMapper.map(flightTO, Flight.class);
        flightDAO.updateFlight(flight);
    }

    @Override
    public FlightTO getFlightById(long id) {
        Flight flight = flightDAO.getFlightById(id);
        return dozerBeanMapper.map(flight, FlightTO.class);
    }

    @Override
    public List<FlightTO> getFlightsByDepartureDate(Date from, Date to) {
        List<FlightTO> flightTOs = new ArrayList<>();

        for(Flight flight : flightDAO.getFlightsByDepartureDate(from, to)){
            flightTOs.add(dozerBeanMapper.map(flight, FlightTO.class));
        }
        return flightTOs;
    }

    @Override
    public List<FlightTO> getFlightsByArrivalDate(Date from, Date to) {
        List<FlightTO> flightTOs = new ArrayList<>();

        for(Flight flight : flightDAO.getFlightsByArrivalDate(from, to)){
            flightTOs.add(dozerBeanMapper.map(flight, FlightTO.class));
        }
        return flightTOs;
    }

    @Override
    public boolean assignStewardToFlight(FlightTO flightTO, StewardTO stewardTO) {
        Flight flight = dozerBeanMapper.map(flightTO, Flight.class);
        Steward steward = dozerBeanMapper.map(stewardTO, Steward.class);
        return flightDAO.safeAddSteward(flight, steward);
    }

    /**
     * Updates the plane after landing
     * @param flightTO
     */
    @Override
    public void landingDone(FlightTO flightTO) {
        PlaneTO planeTO = flightTO.getPlane();
        
        int fuel = (int)(Math.random()*1000); // value from other system
        long distance = (long)(Math.random()*1000); // value from other system
        long time = Math.abs(
                flightTO.getDepartureTime().getTime() - flightTO.getArrivalTime().getTime()
        );
        planeTO.increaseTotalFlightDistance(distance);
        planeTO.increaseTotalFlightTime(time);
        try {
            planeTO.setFuelLeft(fuel);
        } catch (Exception ex) {
            Logger.getLogger(FlightServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        planeService.updatePlane(planeTO);
    }


}
