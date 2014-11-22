package cz.muni.fi.PA165.flight.dao.impl;


import cz.muni.fi.PA165.flight.dao.FlightDAO;
import cz.muni.fi.PA165.flight.entity.Flight;
import cz.muni.fi.PA165.flight.entity.Plane;
import cz.muni.fi.PA165.flight.entity.Steward;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

/**
 * @author Vladimir Jurenka
 */
@Repository
public class FlightDAOImpl implements FlightDAO {

    @PersistenceContext
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
        validateFlight(flight);
        em.persist(flight);
    }

    private void validateFlight(Flight flight) {
        validateTime(flight);
        validatePlane(flight);
        validateStewards(flight);
    }

    private void validateTime(Flight flight) {
        if(flight.getDepartureTime() == null){
            throw new DataIntegrityViolationException("Flight has null departure time!");
        }
        if(flight.getArrivalTime() == null){
            throw new DataIntegrityViolationException("Flight has null arrival time!");
        }

        if ((flight.getArrivalTime().compareTo(flight.getDepartureTime()) < 0)) {
            throw new DataIntegrityViolationException("Flight has arrival date before departure date!");
        }
    }

    private void validateStewards(Flight flight) {
        for (Steward steward : flight.getStewards()) {
            for (Flight f : steward.getFlights()) {
                if (f.equals(flight)) {
                    continue;
                }
                if (flightsInterfere(f, flight)) {
                    throw new InvalidDataAccessApiUsageException("Steward is scheduled for another flight at this time");
                }
            }
        }
    }

    public boolean canAddSteward(Flight flight, Steward steward) {
        for (Flight f : steward.getFlights()) {
            if (f.equals(flight)) {
                continue;
            }
            if (flightsInterfere(f, flight)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean safeAddSteward(Flight flight, Steward steward) {
        if (canAddSteward(flight, steward)) {
            flight.getStewards().add(steward);
            return true;
        }
        return false;
    }

    @Override
    public void updateFlight(Flight flight) {
        em.merge(flight);
    }

    @Override
    public List<Flight> getFlightsByDepartureDate(Date from, Date to) {
        return  em.createQuery("SELECT f FROM Flight f WHERE departureTime >= :from AND departureTime <= :to", Flight.class)
                .setParameter("from", from )
                .setParameter("to", to)
                .getResultList();
    }

    @Override
    public List<Flight> getFlightsByArrivalDate(Date from, Date to) {
        return  em.createQuery("SELECT f FROM Flight f WHERE arrivalTime >= :from AND arrivalTime <= :to", Flight.class)
                .setParameter("from", from )
                .setParameter("to", to)
                .getResultList();
    }

    public boolean flightsInterfere(Flight flight1, Flight flight2) {
        if (flight2.getDepartureTime().compareTo(flight1.getDepartureTime()) <= 0 && flight2.getArrivalTime().compareTo(flight1.getDepartureTime()) <= 0) {
            return true;
        }
        if (flight1.getDepartureTime().compareTo(flight2.getDepartureTime()) <= 0 && flight1.getArrivalTime().compareTo(flight2.getDepartureTime()) <= 0) {
            return true;
        }
        return false;
    }

    private void validatePlane(Flight flight) {
        Plane plane = flight.getPlane();
        if (plane == null) {
            throw new DataIntegrityViolationException("Flight has empty plane");
        }
        for (Flight f : plane.getFlights()) {
            if (f.equals(flight)) {
                continue;
            }
            if (flightsInterfere(f, flight)) {
                throw new InvalidDataAccessApiUsageException("Plane is scheduled for another flight at this time");
            }

        }
    }
}
