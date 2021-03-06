package cz.muni.fi.PA165.flight.dao;

import cz.muni.fi.PA165.flight.dao.impl.PlaneDAOImpl;
import cz.muni.fi.PA165.flight.entity.Plane;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.Date;
import java.util.List;

/**
 * @author  Michal Hruby
 */
@ContextConfiguration(locations = {"classpath:/application-context.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PlaneDAOTest extends AbstractTestNGSpringContextTests {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Test
    public void testGetAllPlanes() throws Exception {
        EntityManager em = emf.createEntityManager();
        PlaneDAO planeDAO;
        Plane p1 = new Plane();
        p1.setManufacturer("Airbus");
        p1.setType("A320");
        Plane p2 = new Plane();
        p2.setManufacturer("Boeing");
        p2.setType("737");

        em.getTransaction().begin();
        planeDAO = new PlaneDAOImpl(em);
        planeDAO.addPlane(p1);
        planeDAO = new PlaneDAOImpl(em);
        planeDAO.addPlane(p2);
        em.getTransaction().commit();

        planeDAO = new PlaneDAOImpl(em);

        List<Plane> planes = planeDAO.getAllPlanes();

        Assert.assertEquals(planes.size(), 2);
    }

    @Test
    public void testDeletePlane() throws Exception {
        long planeID = -1;
        Plane plane = new Plane();
        plane.setManufacturer("Airbus");
        plane.setType("A320");
        plane.setTankCapacity(500000);
        plane.setFuelLeft(10000);
        plane.setLastRevisionTime(new Date(5484855));
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        PlaneDAO planeDAO = new PlaneDAOImpl(em);
        planeDAO.addPlane(plane);
        planeID = plane.getId();
        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        planeDAO = new PlaneDAOImpl(em);
        Plane testedPlane = planeDAO.getPlaneById(planeID);
        em.close();

        Assert.assertNotNull(testedPlane);

        em = emf.createEntityManager();
        em.getTransaction().begin();
        planeDAO = new PlaneDAOImpl(em);
        testedPlane = planeDAO.getPlaneById(testedPlane.getId());
        planeDAO.deletePlane(testedPlane);
        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        planeDAO = new PlaneDAOImpl(em);
        Plane deletedPlane = planeDAO.getPlaneById(testedPlane.getId());

        Assert.assertNull(deletedPlane);
    }

    @Test
    public void testAddPlane() throws Exception {
        long planeID = -1;
        Plane plane = new Plane();
        plane.setManufacturer("Airbus");
        plane.setType("A320");
        plane.setTankCapacity(500000);
        plane.setFuelLeft(10000);
        plane.setLastRevisionTime(new Date(5484855));
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        PlaneDAO planeDAO = new PlaneDAOImpl(em);
        planeDAO.addPlane(plane);
        planeID = plane.getId();
        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        planeDAO = new PlaneDAOImpl(em);
        Plane testedPlane = planeDAO.getPlaneById(planeID);
        em.close();

        Assert.assertNotNull(testedPlane);
    }
}