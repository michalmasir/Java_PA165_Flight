package cz.muni.fi.PA165.flight.dao;

import cz.muni.fi.PA165.flight.dao.impl.PlaneDAOImpl;
import cz.muni.fi.PA165.flight.entity.Plane;
import junit.framework.TestCase;
import org.hibernate.jpa.internal.EntityManagerFactoryImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Date;

/**
 * @author Michal Galan
 */
public class PlaneDAOTest extends TestCase {

    private EntityManagerFactory emf;

    public void testGetAllPlanes() throws Exception {

    }

    public void testGetPlaneById() throws Exception {

    }

    public void testDeletePlane() throws Exception {

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

        assertNotNull(testedPlane);
    }
}