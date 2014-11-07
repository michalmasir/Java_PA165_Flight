package cz.muni.fi.PA165.flight.service;

import cz.muni.fi.PA165.flight.dao.PlaneDAO;
import cz.muni.fi.PA165.flight.entity.Plane;
import cz.muni.fi.PA165.flight.service.impl.PlaneServiceImpl;
import cz.muni.fi.PA165.flight.transfer.FlightTO;
import cz.muni.fi.PA165.flight.transfer.PlaneTO;
import org.dozer.DozerBeanMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.Date;
import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.verify;

/**
 * User: PC
 * Date: 6. 11. 2014
 * Time: 1:21
 */

@ContextConfiguration(locations = {"classpath:/application-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class PlaneServiceTest {

    @Spy
    DozerBeanMapper dozerBeanMapper;

    @Mock
    PlaneDAO planeDAO;

    @InjectMocks
    PlaneServiceImpl planeService;
    
    PlaneTO planeTO1;
    Plane plane1;


    @Before
    public void setup() {
        planeTO1 = new PlaneTO();
//        planeTO1.setId(1);
        plane1 = new Plane();
//        plane1.setId(1);
        
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddPlane() {
        planeService.addPlane(planeTO1);
        verify(planeDAO).addPlane(plane1);
    }
    
    @Test
    public void testUpdatePlane() {
        planeService.updatePlane(planeTO1);
        verify(planeDAO).updatePlane(plane1);
    }
    
    @Test
    public void testRevisionDone() {
        planeService.revisionDone(planeTO1, new Date());
        verify(planeDAO).updatePlane(plane1);
    }
}
