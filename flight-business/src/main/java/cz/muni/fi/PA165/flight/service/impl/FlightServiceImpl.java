package cz.muni.fi.PA165.flight.service.impl;

import cz.muni.fi.PA165.flight.dao.FlightDAO;
import cz.muni.fi.PA165.flight.entity.Flight;
import cz.muni.fi.PA165.flight.entity.Steward;
import cz.muni.fi.PA165.flight.service.FlightService;
//import cz.muni.fi.PA165.flight.service.PlaneService;
import cz.muni.fi.PA165.flight.transfer.FlightTO;
import cz.muni.fi.PA165.flight.transfer.PlaneTO;
import cz.muni.fi.PA165.flight.transfer.StewardTO;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    private FlightDAO flightDAO;
    
    //@Autowired
    //private PlaneService planeService;

    @Autowired
    private DozerBeanMapper dozerBeanMapper;

    @Override
    @Transactional
    public void addFlight(FlightTO flightTO) {
        Flight flight = dozerBeanMapper.map(flightTO, Flight.class);
        flightDAO.addFlight(flight);
    }

    @Override
    @Transactional
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
        flightDAO.deleteFlight(flightDAO.getFlightById(flightTO.getId()));
    }

    @Override
    @Transactional
    public void updateFlight(FlightTO flightTO) {
        Flight flight = dozerBeanMapper.map(flightTO, Flight.class);
        flightDAO.updateFlight(flight);
    }

    @Override
    @Transactional
    public FlightTO getFlightById(long id) {
        Flight flight = flightDAO.getFlightById(id);
        if(flight == null){
            return null;
        }
        return dozerBeanMapper.map(flight, FlightTO.class);
    }

    @Override
    @Transactional
    public List<FlightTO> getFlightsByDepartureDate(Date from, Date to) {
        List<FlightTO> flightTOs = new ArrayList<>();

        for(Flight flight : flightDAO.getFlightsByDepartureDate(from, to)){
            flightTOs.add(dozerBeanMapper.map(flight, FlightTO.class));
        }
        return flightTOs;
    }

    @Override
    @Transactional
    public List<FlightTO> getFlightsByArrivalDate(Date from, Date to) {
        List<FlightTO> flightTOs = new ArrayList<>();

        for(Flight flight : flightDAO.getFlightsByArrivalDate(from, to)){
            flightTOs.add(dozerBeanMapper.map(flight, FlightTO.class));
        }
        return flightTOs;
    }

    @Override
    @Transactional
    public boolean assignStewardToFlight(FlightTO flightTO, StewardTO stewardTO) {
        Flight flight = dozerBeanMapper.map(flightTO, Flight.class);
        Steward steward = dozerBeanMapper.map(stewardTO, Steward.class);
        return flightDAO.safeAddSteward(flight, steward);
    }



}
