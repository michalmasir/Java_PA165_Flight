package cz.muni.fi.PA165.flight.dao;

import cz.muni.fi.PA165.flight.dao.impl.FlightDAOImpl;
import cz.muni.fi.PA165.flight.dao.impl.PlaneDAOImpl;
import cz.muni.fi.PA165.flight.dao.impl.StewardDAOImpl;
import cz.muni.fi.PA165.flight.entity.Flight;
import cz.muni.fi.PA165.flight.entity.Plane;
import cz.muni.fi.PA165.flight.entity.Steward;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.Calendar;
import java.util.List;

/**
 * @author Michal Masir
 */
@ContextConfiguration(locations = {"classpath:/application-context.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class FlightTest extends AbstractTestNGSpringContextTests {

    @PersistenceUnit
    public EntityManagerFactory emf;

    private Flight dummyFlight() {
        Flight flight = new Flight();
        Calendar departure = Calendar.getInstance();
        departure.set(2000, 1, 1, 0, 0, 0);

        flight.setDepartureTime(departure.getTime());

        Calendar arrival = Calendar.getInstance();
        arrival.set(2000, 1, 1 ,1, 0,0);

        flight.setArrivalTime(arrival.getTime());

        Plane plane = new Plane();
        plane.setTankCapacity(1000);
        flight.setPlane(plane);

        return flight;
    }


    @Test
    public void testFlightCreation() {
        long flightId;

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Flight flight = dummyFlight();
        new FlightDAOImpl(em).addFlight(flight);
        new PlaneDAOImpl(em).addPlane(flight.getPlane());
        flightId = flight.getId();

        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        Flight flightFromDb = new FlightDAOImpl(em).getFlightById(flightId);
        em.close();


        Assert.assertNotNull(flightFromDb);
        Assert.assertEquals(1000, flightFromDb.getPlane().getTankCapacity());
    }


    @Test
    public void testFlightDelete() {
        long flightId;

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Flight flight = dummyFlight();
        new FlightDAOImpl(em).addFlight(flight);
        new PlaneDAOImpl(em).addPlane(flight.getPlane());
        flightId = flight.getId();

        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        Flight flightFromDb = new FlightDAOImpl(em).getFlightById(flightId);
        em.close();

        Assert.assertNotNull(flightFromDb);

        em = emf.createEntityManager();
        em.getTransaction().begin();
        flightFromDb = new FlightDAOImpl(em).getFlightById(flightFromDb.getId());
        new FlightDAOImpl(em).deleteFlight(flightFromDb);
        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        flightFromDb = new FlightDAOImpl(em).getFlightById(flightFromDb.getId());
        em.close();

        Assert.assertNull(flightFromDb);
    }


    @Test
    public void testFlightList() {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Flight flight1 = dummyFlight();
        Flight flight2 = dummyFlight();
        Flight flight3 = dummyFlight();
        FlightDAOImpl flightDao = new FlightDAOImpl(em);
        PlaneDAOImpl planeDAO = new PlaneDAOImpl(em);

        flightDao.addFlight(flight1);
        planeDAO.addPlane(flight1.getPlane());

        flightDao.addFlight(flight2);
        planeDAO.addPlane(flight2.getPlane());

        flightDao.addFlight(flight3);
        planeDAO.addPlane(flight3.getPlane());

        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        List<Flight> flights = new FlightDAOImpl(em).getAllFlights();
        em.close();

        Assert.assertEquals(flights.size(), 3);
    }

    @Test
    public void testGetFlightsByDate(){

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Flight flight1 = dummyFlight();
        PlaneDAOImpl planeDAO = new PlaneDAOImpl(em);
        planeDAO.addPlane(flight1.getPlane());

        FlightDAOImpl flightDao = new FlightDAOImpl(em);
        flightDao.addFlight(flight1);
        em.getTransaction().commit();
        em.close();


        Calendar departure1 = Calendar.getInstance();
        departure1.set(1999, 1, 1);

        Calendar departure2 = Calendar.getInstance();
        departure2.set(2001, 1, 1);


        Calendar arrival1 = Calendar.getInstance();
        arrival1.set(1999, 1, 1);

        Calendar arrival2 = Calendar.getInstance();
        arrival2.set(2001, 1, 1);

        em = emf.createEntityManager();
        List<Flight> flights = new FlightDAOImpl(em).getFlightsByDepartureDate(departure1.getTime(), departure2.getTime());
        em.close();

        Assert.assertEquals(flights.size(), 1);

        em = emf.createEntityManager();
        flights = new FlightDAOImpl(em).getFlightsByArrivalDate(arrival1.getTime(), arrival2.getTime());
        em.close();

        Assert.assertEquals(flights.size(), 1);
    }


    @Test(expectedExceptions = InvalidDataAccessApiUsageException.class)
    public void testPlaneCollision() {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Flight flight1 = dummyFlight();
        Flight flight2 = dummyFlight();

        PlaneDAOImpl planeDAO = new PlaneDAOImpl(em);

        planeDAO.addPlane(flight1.getPlane());
        em.getTransaction().commit();


        em.getTransaction().begin();
        FlightDAOImpl flightDao = new FlightDAOImpl(em);
        Plane plane = planeDAO.getPlaneById(flight1.getPlane().getId());

        flight1.setPlane(plane);
        flight2.setPlane(plane);

        flightDao.addFlight(flight1);
        plane.getFlights().add(flight1);
        em.getTransaction().commit();

        em.getTransaction().begin();
        flightDao.addFlight(flight2);
        plane.getFlights().add(flight2);

        em.getTransaction().commit();
        em.close();
    }

    @Test(expectedExceptions = InvalidDataAccessApiUsageException.class)
    public void testStewardCollision() {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();


        StewardDAO stewardDAO = new StewardDAOImpl(em);
        PlaneDAOImpl planeDAO = new PlaneDAOImpl(em);
        FlightDAOImpl flightDao = new FlightDAOImpl(em);

        Steward john = new Steward();
        stewardDAO.addSteward(john);
        em.getTransaction().commit();


        em.getTransaction().begin();
        john = stewardDAO.getStewardById(john.getId());

        Flight flight1 = dummyFlight();
        planeDAO.addPlane(flight1.getPlane());
        flight1.getStewards().add(john);

        flightDao.addFlight(flight1);
        em.getTransaction().commit();
        john.getFlights().add(flight1);


        em.getTransaction().begin();

        Flight flight2 = dummyFlight();
        planeDAO.addPlane(flight2.getPlane());
        flight2.getStewards().add(john);

        john.getFlights().add(flight2);
        flightDao.addFlight(flight2);

        em.getTransaction().commit();
        em.close();
    }

    @Test
    public void testSafeAdd() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();


        StewardDAO stewardDAO = new StewardDAOImpl(em);
        PlaneDAOImpl planeDAO = new PlaneDAOImpl(em);
        FlightDAOImpl flightDao = new FlightDAOImpl(em);

        Steward john = new Steward();
        stewardDAO.addSteward(john);
        em.getTransaction().commit();


        em.getTransaction().begin();
        john = stewardDAO.getStewardById(john.getId());

        Flight flight1 = dummyFlight();
        flightDao.addFlight(flight1);

        Assert.assertTrue(flightDao.canAddSteward(flight1, john));
        flight1.getStewards().add(john);
        john.getFlights().add(flight1);


        Flight flight2 = dummyFlight();
        flightDao.addFlight(flight2);
        Assert.assertFalse(flightDao.canAddSteward(flight2, john));


        em.getTransaction().rollback();
        em.close();
    }
}
