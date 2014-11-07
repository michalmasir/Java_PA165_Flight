package cz.muni.fi.PA165.flight.dao.impl;

import cz.muni.fi.PA165.flight.dao.PlaneDAO;
import cz.muni.fi.PA165.flight.entity.Plane;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import org.springframework.dao.DataIntegrityViolationException;

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
        if (plane == null){
            throw new DataIntegrityViolationException("Plane can not be null");
        }
        em.remove(plane);

    }

    public void addPlane(Plane plane){
        if (plane == null){
            throw new DataIntegrityViolationException("Plane can not be null");
        }
        em.persist(plane);

    }
    
    public Plane updatePlane(Plane plane){
        if (plane == null){
            throw new DataIntegrityViolationException("Plane can not be null");
        }
        return em.merge(plane);
        
    }
}
