package cz.muni.fi.PA165.flight.dao.impl;


import cz.muni.fi.PA165.flight.dao.FlightDAO;
import cz.muni.fi.PA165.flight.entity.Flight;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Vladimir Jurenka
 */
public class FlightDAOImpl implements FlightDAO {

    @PersistenceContext(unitName = "myUnit")
    public EntityManager em;

    public FlightDAOImpl() {
    }

    public FlightDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Flight> getAllFlights() {
        return em.createQuery("SELECT f FROM Flight f", Flight.class).getResultList();
    }

    @Override
    public Flight getFlightById(long id) {
        return em.find(Flight.class, id);
    }

    @Override
    public void deleteFlight(Flight flight) {
        em.remove(flight);
    }

    @Override
    public void addFlight(Flight flight) {
        em.persist(flight);
    }
}
