package cz.muni.fi.PA165.flight.service.impl;

import cz.muni.fi.PA165.flight.dao.PlaneDAO;
import cz.muni.fi.PA165.flight.entity.Plane;
import cz.muni.fi.PA165.flight.service.PlaneService;
import cz.muni.fi.PA165.flight.transfer.FlightTO;
import cz.muni.fi.PA165.flight.transfer.PlaneTO;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * @author Michal Galan
 */
@Service
public class PlaneServiceImpl implements PlaneService {

    @Autowired
    private PlaneDAO planeDAO;

    @Autowired
    private DozerBeanMapper dozerBeanMapper;

    @Override
    @Transactional
    @Secured("ROLE_ADMIN")
    public void addPlane(PlaneTO planeTO) {
        Plane plane = dozerBeanMapper.map(planeTO, Plane.class);
        planeDAO.addPlane(plane);
    }

    @Override
    @Transactional
    @Secured("ROLE_ADMIN")
    public void updatePlane(PlaneTO planeTO) {
        Plane plane = dozerBeanMapper.map(planeTO, Plane.class);
        planeDAO.updatePlane(plane);
    }


    @Override
    @Transactional
    @Secured("ROLE_ADMIN")
    public List<PlaneTO> planeList() {
        List<PlaneTO> flightTOs = new ArrayList<>();

        for (Plane flight : planeDAO.getAllPlanes()) {
            flightTOs.add(dozerBeanMapper.map(flight, PlaneTO.class));
        }
        return flightTOs;
    }

    @Override
    @Transactional
    @Secured("ROLE_ADMIN")
    public PlaneTO getPlaneById(long id) {
        Plane plane = planeDAO.getPlaneById(id);
        if (plane == null) {
            return null;
        }
        return dozerBeanMapper.map(plane, PlaneTO.class);
    }

    @Override
    @Transactional
    @Secured("ROLE_ADMIN")
    public void removePlane(PlaneTO planeTO) {
        planeDAO.deletePlane(planeDAO.getPlaneById(planeTO.getId()));
    }
}
