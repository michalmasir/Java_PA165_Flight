package cz.muni.fi.PA165.flight.service.impl;

import cz.muni.fi.PA165.flight.dao.FlightDAO;
import cz.muni.fi.PA165.flight.entity.Flight;
import cz.muni.fi.PA165.flight.entity.Steward;
import cz.muni.fi.PA165.flight.service.FlightService;
import cz.muni.fi.PA165.flight.transfer.FlightTO;
import cz.muni.fi.PA165.flight.transfer.StewardTO;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public void removeFlight(FlightTO flightTO) {
        Flight flight = dozerBeanMapper.map(flightTO, Flight.class);
        flightDAO.deleteFlight(flight);
    }

    @Override
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


}
