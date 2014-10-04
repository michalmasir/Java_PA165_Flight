package cz.muni.fi.PA165.flight;

import cz.muni.fi.PA165.flight.dao.impl.FlightDAOImpl;
import cz.muni.fi.PA165.flight.entity.Flight;
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
import java.util.List;

@ContextConfiguration(classes = DaoContext.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class FlightTest extends AbstractTestNGSpringContextTests {

    @PersistenceUnit
    public EntityManagerFactory emf;

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

        Flight flight = new Flight();
        new FlightDAOImpl(em).addFlight(flight);
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

        Flight flight = new Flight();
        new FlightDAOImpl(em).addFlight(flight);
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

        Flight flight1 = new Flight();
        Flight flight2 = new Flight();
        Flight flight3 = new Flight();
        new FlightDAOImpl(em).addFlight(flight1);
        new FlightDAOImpl(em).addFlight(flight2);
        new FlightDAOImpl(em).addFlight(flight3);

        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        List<Flight> flights = new FlightDAOImpl(em).getAllFlights();
        em.close();

        Assert.assertEquals(flights.size(), 3);


    }

}
