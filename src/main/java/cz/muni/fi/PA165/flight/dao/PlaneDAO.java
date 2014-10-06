package cz.muni.fi.PA165.flight.dao;


import cz.muni.fi.PA165.flight.entity.Plane;

import java.util.List;

/**
 * @author Michal Galan
 */
public interface PlaneDAO {

    public List<Plane> getAllPlanes();

    public Plane getPlaneById(long id);

    public void deletePlane(Plane plane);

    public void addPlane(Plane plane);
}
