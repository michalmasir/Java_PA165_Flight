package cz.muni.fi.PA165.flight;

import cz.muni.fi.PA165.flight.dao.StewardDAO;
import cz.muni.fi.PA165.flight.dao.impl.FlightDAOImpl;
import cz.muni.fi.PA165.flight.dao.impl.PlaneDAOImpl;
import cz.muni.fi.PA165.flight.dao.impl.StewardDAOImpl;
import cz.muni.fi.PA165.flight.entity.Flight;
import cz.muni.fi.PA165.flight.entity.Plane;
import cz.muni.fi.PA165.flight.entity.Steward;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.Calendar;
import java.util.List;

/**
 * @author Michal Masir
 */
@ContextConfiguration(classes = DaoContext.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class FlightTest extends AbstractTestNGSpringContextTests {

    @PersistenceUnit
    public EntityManagerFactory emf;

    private Flight dummyFlight() {
        Flight flight = new Flight();
        Calendar departure = Calendar.getInstance();
        departure.set(2000, 1, 1);

        flight.setDepartureTime(departure.getTime());

        Calendar arrival = Calendar.getInstance();
        arrival.set(2000, 1, 1);

        flight.setArrivalTime(arrival.getTime());

        Plane plane = new Plane();
        flight.setPlane(plane);

        return flight;
    }

    @BeforeMethod
    public void setup() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        em.getTransaction().commit();
        em.close();
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


    @Test(expectedExceptions = IllegalArgumentException.class)
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
        flightDao.addFlight(flight2);
        plane.getFlights().add(flight2);

        em.getTransaction().commit();
        em.close();
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
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
        flight1.getStewards().add(john);
        john.getFlights().add(flight1);

        Flight flight2 = dummyFlight();
        flight1.getStewards().add(john);
        john.getFlights().add(flight2);

        planeDAO.addPlane(flight1.getPlane());
        planeDAO.addPlane(flight2.getPlane());

        flightDao.addFlight(flight1);
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
        Assert.assertTrue(flightDao.canAddSteward(flight1, john));
        flight1.getStewards().add(john);
        john.getFlights().add(flight1);

        Flight flight2 = dummyFlight();

        Assert.assertFalse(flightDao.canAddSteward(flight2, john));


        em.getTransaction().rollback();
        em.close();
    }
}
