package cz.muni.fi.PA165.flight.dao;

import cz.muni.fi.PA165.flight.DaoContext;
import cz.muni.fi.PA165.flight.dao.impl.PlaneDAOImpl;
import cz.muni.fi.PA165.flight.entity.Plane;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.hibernate.jpa.internal.EntityManagerFactoryImpl;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.BeforeMethod;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.Date;
import java.util.List;

/**
 * @author Michal Galan
 */
@ContextConfiguration(classes = DaoContext.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PlaneDAOTest extends TestCase {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @BeforeMethod
    public void setup() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        em.getTransaction().commit();
        em.close();
    }

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

        Assert.assertEquals(planes.size(),2);
    }

    public void testDeletePlane() throws Exception {
        long planeID = -1;
        Plane plane = new Plane();
        plane.setManufacturer("Airbus");
        plane.setType("A320");
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
        planeDAO.deletePlane(testedPlane);
        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        planeDAO = new PlaneDAOImpl(em);
        Plane deletedPlane = planeDAO.getPlaneById(testedPlane.getId());

        Assert.assertNull(deletedPlane);
    }

    public void testAddPlane() throws Exception {
        long planeID = -1;
        Plane plane = new Plane();
        plane.setManufacturer("Airbus");
        plane.setType("A320");
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