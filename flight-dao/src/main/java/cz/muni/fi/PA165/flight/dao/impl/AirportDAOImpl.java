package cz.muni.fi.PA165.flight.dao.impl;

import cz.muni.fi.PA165.flight.dao.AirportDAO;
import cz.muni.fi.PA165.flight.entity.Airport;
import cz.muni.fi.PA165.flight.entity.Flight;
import org.springframework.dao.DataIntegrityViolationException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Michal Mašír on 9. 10. 2014.
 * @author Michal Masir
 */
public class AirportDAOImpl implements AirportDAO{

    @PersistenceContext
    public EntityManager em;


    public AirportDAOImpl() {
    }

    public AirportDAOImpl(EntityManager em){
        this.em = em;
    }

    @Override
    public List<Airport> getAllAirports() {
        return em.createQuery("SELECT a FROM Airport a", Airport.class).getResultList();
    }

    @Override
    public Airport getAirportById(long id) {
        return em.find(Airport.class, id);
    }

    @Override
    public void deleteAirport(Airport airport) {
        em.remove(airport);
    }

    @Override
    public void addAirport(Airport airport) {
        //validateAirport(airport);
        em.persist(airport);
    }

    @Override
    public Airport updateAirport(Airport airport) {
       //validateAirport(airport);
       return em.merge(airport);
    }

    public boolean validateAirport(Airport airport){
       if(airport==null)
           throw new DataIntegrityViolationException("You can not add a null as airport !");
       if(!validateFlights(airport))
           throw new DataIntegrityViolationException("You can not add a null as Flight!");
       return true;
    }

    public boolean validateFlights(Airport airport){
        for(Flight flight: airport.getFlightsFrom()){
            if(flight==null)return false;
        }
        for(Flight flight: airport.getFlightsTo()){
            if(flight==null)return false;
        }
        return true;
    }
}
