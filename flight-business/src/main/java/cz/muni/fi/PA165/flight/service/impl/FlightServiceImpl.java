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
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
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
    
    @Autowired
    private DozerBeanMapper dozerBeanMapper;

    @Override
    @Transactional
    @Secured("ROLE_ADMIN")
    public void addFlight(FlightTO flightTO) {
        Flight flight = dozerBeanMapper.map(flightTO, Flight.class);
        flightDAO.addFlight(flight);
    }

    @Override
    @Transactional
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public List<FlightTO> getFlightsList() {
        List<FlightTO> flightTOs = new ArrayList<>();

        for(Flight flight : flightDAO.getAllFlights()){
            flightTOs.add(dozerBeanMapper.map(flight, FlightTO.class));
        }
        return flightTOs;
    }

    @Override
    @Transactional
    @Secured("ROLE_ADMIN")
    public void removeFlight(FlightTO flightTO) {
        flightDAO.deleteFlight(flightDAO.getFlightById(flightTO.getId()));
    }

    @Override
    @Transactional
    @Secured("ROLE_ADMIN")
    public void updateFlight(FlightTO flightTO) {
        Flight flight = dozerBeanMapper.map(flightTO, Flight.class);
        flightDAO.updateFlight(flight);
    }

    @Override
    @Transactional
    @Secured("ROLE_ADMIN")
    public FlightTO getFlightById(long id) {
        Flight flight = flightDAO.getFlightById(id);
        if(flight == null){
            return null;
        }
        return dozerBeanMapper.map(flight, FlightTO.class);
    }
}

