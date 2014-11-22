package cz.muni.fi.PA165.flight.service.impl;

import cz.muni.fi.PA165.flight.dao.PlaneDAO;
import cz.muni.fi.PA165.flight.entity.Plane;
import cz.muni.fi.PA165.flight.service.PlaneService;
import cz.muni.fi.PA165.flight.transfer.PlaneTO;
import org.dozer.DozerBeanMapper;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Date;


/**
 * @author Michal Galan
 */
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


}
