package cz.muni.fi.PA165.flight.dao;


import cz.muni.fi.PA165.flight.entity.Plane;

import java.util.List;

/**
 * This interfaces specifies the methods that a {@link cz.muni.fi.PA165.flight.entity.Plane} data access object
 * must implement.
 *
 * @author Michal Galan
 */
public interface PlaneDAO {

    /**
     * Returns the list of all planes.
     *
     * @return list of all planes
     */
    public List<Plane> getAllPlanes();

    /**
     * Finds plane by id.
     *
     * @param id id of the Plane
     * @return the found Plane or null if the plane does not exist
     */
    public Plane getPlaneById(long id);

    /**
     * Deletes a Plane with the id.
     *
     * @param plane Plane to delete
     */
    public void deletePlane(Plane plane);

    /**
     * Saves a Plane.
     *
     * @param plane Plane to save
     */
    public void addPlane(Plane plane);
}
