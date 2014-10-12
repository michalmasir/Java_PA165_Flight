package cz.muni.fi.PA165.flight;

import cz.muni.fi.PA165.flight.DaoContext;
import cz.muni.fi.PA165.flight.dao.AirportDAO;
import cz.muni.fi.PA165.flight.dao.impl.AirportDAOImpl;
import cz.muni.fi.PA165.flight.entity.Airport;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

@ContextConfiguration(classes = DaoContext.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AirportTest extends AbstractTestNGSpringContextTests {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @BeforeMethod
    public void setup(){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        em.getTransaction().commit();
        em.close();
    }

    @Test
    public void testAirportCreation(){
        long airportId = -1;

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        AirportDAO airportDAO;

        Airport airport1 = new Airport();
        airport1.setCity("Brno");
        airport1.setName("Masarykovo letisko");
        airport1.setState("CZ");

        Airport airport2 = new Airport();
        airport2.setCity("Bratislava");
        airport2.setName("Letisko Bratislava");
        airport2.setState("SK");

        Airport airport3 = new Airport();
        airport3.setCity("London");
        airport3.setName("Heathrow");
        airport3.setState("UK");

        airportDAO = new AirportDAOImpl(em);
        airportDAO.addAirport(airport1);
        airportDAO = new AirportDAOImpl(em);
        airportDAO.addAirport(airport2);
        airportDAO = new AirportDAOImpl(em);
        airportDAO.addAirport(airport3);

        airportId = airport1.getId();
        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        Airport newAirport = new AirportDAOImpl(em).getAirportById(airportId);
        Assert.assertNotNull(newAirport);
    }

    @Test
    public void testDelete(){
        long airportId = -1;

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        AirportDAO airportDAO;

        Airport airport1 = new Airport();
        airport1.setCity("Brno");
        airport1.setName("Masarykovo letisko");
        airport1.setState("CZ");

        airportDAO = new AirportDAOImpl(em);
        airportDAO.addAirport(airport1);

        airportId = airport1.getId();
        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        airportDAO = new AirportDAOImpl(em);
        Airport newAirport = airportDAO.getAirportById(airportId);
        em.close();

        Assert.assertNotNull(newAirport);

        em = emf.createEntityManager();
        em.getTransaction().begin();
        airportDAO = new AirportDAOImpl(em);
        newAirport = airportDAO.getAirportById(airportId);
        airportDAO.deleteAirport(newAirport);
        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        airportDAO = new AirportDAOImpl(em);
        Airport deletedAirport = airportDAO.getAirportById(airportId);

        Assert.assertNull(deletedAirport);
    }

    @Test
    public void testAirportList(){

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        AirportDAO airportDAO;

        Airport airport1 = new Airport();
        airport1.setCity("Brno");
        airport1.setName("Masarykovo letisko");
        airport1.setState("CZ");

        Airport airport2 = new Airport();
        airport2.setCity("Bratislava");
        airport2.setName("Letisko Bratislava");
        airport2.setState("SK");

        Airport airport3 = new Airport();
        airport3.setCity("London");
        airport3.setName("Heathrow");
        airport3.setState("UK");

        airportDAO = new AirportDAOImpl(em);
        airportDAO.addAirport(airport1);
        airportDAO = new AirportDAOImpl(em);
        airportDAO.addAirport(airport2);
        airportDAO = new AirportDAOImpl(em);
        airportDAO.addAirport(airport3);
        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        List<Airport> aList = new AirportDAOImpl(em).getAllAirports();

            System.out.println("+++ "+aList.get(1));

        Assert.assertEquals(aList.size(),3);
    }

}