package cz.muni.fi.PA165.flight.service;

import cz.muni.fi.PA165.flight.dao.PlaneDAO;
import cz.muni.fi.PA165.flight.entity.Plane;
import cz.muni.fi.PA165.flight.service.impl.PlaneServiceImpl;
import cz.muni.fi.PA165.flight.transfer.FlightTO;
import cz.muni.fi.PA165.flight.transfer.PlaneTO;
import org.dozer.DozerBeanMapper;
import org.mockito.*;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Calendar;
import java.util.Date;

import static org.mockito.Mockito.verify;

/**
 * User: PC
 * Date: 6. 11. 2014
 * Time: 1:21
 */

@ContextConfiguration(locations = {"classpath:/application-context.xml"})
public class PlaneServiceTest {

    @Spy
    DozerBeanMapper dozerBeanMapper;

    @Mock
    PlaneDAO planeDAO;

    @InjectMocks
    PlaneServiceImpl planeService;
    
    PlaneTO planeTO1;
    Plane plane1;


    @BeforeMethod
    public void setup() {
        planeTO1 = new PlaneTO();
        plane1 = new Plane();

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddPlane() {
        planeService.addPlane(planeTO1);
        Mockito.verify(planeDAO).addPlane(plane1);
    }
    
    @Test
    public void testUpdatePlane() {
        planeService.updatePlane(planeTO1);
        Mockito.verify(planeDAO).updatePlane(plane1);
    }
}
