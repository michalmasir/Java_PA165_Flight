package cz.muni.fi.PA165.flight.dao.impl;

import cz.muni.fi.PA165.flight.dao.StewardDAO;
import cz.muni.fi.PA165.flight.entity.Flight;
import cz.muni.fi.PA165.flight.entity.Steward;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author  Michal Hruby
 */

public class StewardDAOImpl implements StewardDAO {

    @PersistenceContext
    public EntityManager em;

    public StewardDAOImpl() {
    }

    public StewardDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Steward> getAllStewards() {

        return em.createQuery("SELECT s FROM Steward s", Steward.class).getResultList();
    }

    @Override
    public Steward getStewardById(long id) {

        return em.find(Steward.class, id);
    }

    @Override
    public void addSteward(Steward steward) {

        em.persist(steward);
    }

    @Override
    public void deleteSteward(Steward steward) {

        em.remove(steward);

    }



}
