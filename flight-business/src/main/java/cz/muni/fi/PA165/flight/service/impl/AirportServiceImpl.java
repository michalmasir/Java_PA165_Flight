package cz.muni.fi.PA165.flight.service.impl;

import cz.muni.fi.PA165.flight.dao.AirportDAO;
import cz.muni.fi.PA165.flight.entity.Airport;
import cz.muni.fi.PA165.flight.service.AirportService;
import cz.muni.fi.PA165.flight.transfer.AirportTO;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Misko on 6.11.2014.
 */
@Service
public class AirportServiceImpl implements AirportService{

    @Inject
    private AirportDAO airportDao;

    @Inject
    private DozerBeanMapper dozerBeanMapper;

    @Override
    @Transactional
    public void addAirport(AirportTO airportTo) {
        Airport airport = dozerBeanMapper.map(airportTo,Airport.class);
        airportDao.addAirport(airport);
    }

    @Override
    @Transactional
    public void removeAirport(AirportTO airportTo) {
        Airport airport = dozerBeanMapper.map(airportTo,Airport.class);
        airportDao.deleteAirport(airport);
    }

    @Override
    @Transactional
    public AirportTO updateAirport(AirportTO airportTO) {
        Airport targetAirport = dozerBeanMapper.map(airportTO, Airport.class);
        targetAirport = airportDao.updateAirport(targetAirport);
        return dozerBeanMapper.map(targetAirport, AirportTO.class);
    }

    @Override
    public List<AirportTO> getAirportsList() {
       List<AirportTO> airportTOs = new ArrayList<>();
        for(Airport airport: airportDao.getAllAirports()){
            airportTOs.add(dozerBeanMapper.map(airport, AirportTO.class));
        }
        return airportTOs;
    }


}
