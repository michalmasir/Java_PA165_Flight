package cz.muni.fi.PA165.flight;

import cz.muni.fi.PA165.flight.dao.impl.StewardDAOImpl;
import cz.muni.fi.PA165.flight.entity.Steward;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

/**
 *  @author Michal Galan
 */
@ContextConfiguration(classes = DaoContext.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class StewardDAOTest extends AbstractTestNGSpringContextTests {

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
    public void testStewardAdd() {

        long stewardId;
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Steward steward = new Steward();
        new StewardDAOImpl(em).addSteward(steward);
        stewardId = steward.getId();
        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        Steward fetchedSteward = new StewardDAOImpl(em).getStewardById(stewardId);
        em.close();

        Assert.assertNotNull(fetchedSteward);
    }

    @Test
    public void testStewardDelete() {

        long stewardId;
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Steward steward = new Steward();
        new StewardDAOImpl(em).addSteward(steward);
        stewardId = steward.getId();
        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        Steward fetchedSteward;
        fetchedSteward = new StewardDAOImpl(em).getStewardById(stewardId);
        em.close();

        Assert.assertNotNull(fetchedSteward);

        em = emf.createEntityManager();
        em.getTransaction().begin();
        fetchedSteward = new StewardDAOImpl(em).getStewardById(fetchedSteward.getId());
        new StewardDAOImpl(em).deleteSteward(fetchedSteward);
        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        Steward deletedSteward;
        deletedSteward = new StewardDAOImpl(em).getStewardById(fetchedSteward.getId());
        em.close();

        Assert.assertNull(deletedSteward);
    }

    @Test
    public void testStewardList() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Steward s1 = new Steward();
        s1.setFirstName("Philip");
        s1.setLastName("Fry");
        Steward s2 = new Steward();
        s2.setFirstName("Turanga");
        s2.setLastName("Leela");

        new StewardDAOImpl(em).addSteward(s1);
        new StewardDAOImpl(em).addSteward(s2);

        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        List<Steward> stewardsList = new StewardDAOImpl(em).getAllStewards();
        em.close();

        Assert.assertEquals(stewardsList.size(), 2);
    }


}
