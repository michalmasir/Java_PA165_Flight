package cz.muni.fi.PA165.flight.service.impl;

import cz.muni.fi.PA165.flight.dao.AirportDAO;
import cz.muni.fi.PA165.flight.entity.Airport;
import cz.muni.fi.PA165.flight.service.AirportService;
import cz.muni.fi.PA165.flight.transfer.AirportTO;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Misko on 6.11.2014.
 */
@Service
public class AirportServiceImpl implements AirportService {

    @Autowired
    private AirportDAO airportDao;

    @Autowired
    private DozerBeanMapper dozerBeanMapper;

    @Override
    @Transactional
    @Secured("ROLE_ADMIN")
    public void addAirport(AirportTO airportTo) {
        Airport airport = dozerBeanMapper.map(airportTo, Airport.class);
        airportDao.addAirport(airport);
    }

    @Override
    @Transactional
    @Secured("ROLE_ADMIN")
    public void removeAirport(AirportTO airportTo) {
        Airport airport = airportDao.getAirportById(airportTo.getId());
        airportDao.deleteAirport(airport);
    }

    @Override
    @Transactional
    @Secured("ROLE_ADMIN")
    public AirportTO updateAirport(AirportTO airportTO) {
        Airport targetAirport = dozerBeanMapper.map(airportTO, Airport.class);
        targetAirport = airportDao.updateAirport(targetAirport);
        return dozerBeanMapper.map(targetAirport, AirportTO.class);
    }

    @Override
    @Transactional
    @Secured("ROLE_ADMIN")
    public List<AirportTO> getAirportsList() {
        List<AirportTO> airportTOs = new ArrayList<>();
        for (Airport airport : airportDao.getAllAirports()) {
            airportTOs.add(dozerBeanMapper.map(airport, AirportTO.class));
        }
        return airportTOs;
    }

    @Override
    @Transactional
    @Secured("ROLE_ADMIN")
    public AirportTO getAirportById(long id) {
        Airport airport = airportDao.getAirportById(id);
        if (airport == null) {
            return null;
        }
        return dozerBeanMapper.map(airport, AirportTO.class);
    }


}
