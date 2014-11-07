package cz.muni.fi.PA165.flight.dao.impl;

import cz.muni.fi.PA165.flight.dao.PlaneDAO;
import cz.muni.fi.PA165.flight.entity.Plane;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Michal Galan
 */
public class PlaneDAOImpl implements PlaneDAO {

    @PersistenceContext
    public EntityManager em;

    public PlaneDAOImpl() {
    }

    public PlaneDAOImpl(EntityManager em){

        this.em = em;

    }

    public List<Plane> getAllPlanes(){

        return em.createQuery("SELECT p FROM Plane p", Plane.class).getResultList();

    }

    public Plane getPlaneById(long id){

        return em.find(Plane.class, id);

    }

    public void deletePlane(Plane plane){

        em.remove(plane);

    }

    public void addPlane(Plane plane){

        em.persist(plane);

    }
    
    public Plane updatePlane(Plane plane){
        
        return em.merge(plane);
        
    }
}
