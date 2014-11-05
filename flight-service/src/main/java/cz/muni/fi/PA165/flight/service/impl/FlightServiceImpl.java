package cz.muni.fi.PA165.flight.service.impl;

import cz.muni.fi.PA165.flight.dao.FlightDAO;
import cz.muni.fi.PA165.flight.entity.Flight;
import cz.muni.fi.PA165.flight.service.FlightService;
import cz.muni.fi.PA165.flight.transfer.FlightTO;
import org.dozer.DozerBeanMapper;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * User: PC
 * Date: 5. 11. 2014
 * Time: 19:54
 */
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


}
