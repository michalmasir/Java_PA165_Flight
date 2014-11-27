package cz.muni.fi.PA165.flight.service.impl;

import cz.muni.fi.PA165.flight.dao.PlaneDAO;
import cz.muni.fi.PA165.flight.entity.Plane;
import cz.muni.fi.PA165.flight.service.PlaneService;
import cz.muni.fi.PA165.flight.transfer.PlaneTO;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author Michal Galan
 */
@Service
public class PlaneServiceImpl implements PlaneService{

    @Inject
    private PlaneDAO planeDAO;

    @Inject
    private DozerBeanMapper dozerBeanMapper;

    @Override
    @Transactional
    public void addPlane(PlaneTO planeTO) {
        Plane plane = dozerBeanMapper.map(planeTO, Plane.class);
        planeDAO.addPlane(plane);
    }
    
    @Override
    @Transactional
    public void updatePlane(PlaneTO planeTO) {
        Plane plane = dozerBeanMapper.map(planeTO, Plane.class);
        planeDAO.updatePlane(plane);
    }
    
    @Override
    @Transactional
    public void revisionDone(PlaneTO planeTO, Date date) {
        planeTO.setLastRevisionTime(date);
        updatePlane(planeTO);
    }

    @Override
    @Transactional
    public List<PlaneTO> planeList() {
        List<PlaneTO> flightTOs = new ArrayList<>();

        for(Plane flight : planeDAO.getAllPlanes()){
            flightTOs.add(dozerBeanMapper.map(flight, PlaneTO.class));
        }
        return flightTOs;}

    @Override
    @Transactional
    public PlaneTO getPlaneById(long id) {
        Plane plane = planeDAO.getPlaneById(id);
        return dozerBeanMapper.map(plane, PlaneTO.class);
    }

    @Override
    @Transactional
    public void removePlane(PlaneTO planeTO) {
        planeDAO.deletePlane(planeDAO.getPlaneById(planeTO.getId()));
    }

}
