package cz.muni.fi.PA165.flight.dao.impl;

import cz.muni.fi.PA165.flight.dao.AirportDAO;
import cz.muni.fi.PA165.flight.entity.Airport;
import cz.muni.fi.PA165.flight.entity.Plane;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by Michal Mašír on 9. 10. 2014.
 */
public class AirportDAOImpl implements AirportDAO{
    public EntityManager em;

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
        em.persist(airport);
    }
}
