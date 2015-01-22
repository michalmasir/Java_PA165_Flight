package cz.muni.fi.PA165.flight.service;

import cz.muni.fi.PA165.flight.dao.PlaneDAO;
import cz.muni.fi.PA165.flight.entity.Plane;
import cz.muni.fi.PA165.flight.service.impl.PlaneServiceImpl;
import cz.muni.fi.PA165.flight.transfer.FlightTO;
import cz.muni.fi.PA165.flight.transfer.PlaneTO;
import org.dozer.DozerBeanMapper;
import org.mockito.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.verify;

/**
 * User: PC
 * Date: 6. 11. 2014
 * Time: 1:21
 */

@ContextConfiguration(locations = {"classpath:/application-context.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PlaneServiceTest {

    @Spy
    DozerBeanMapper dozerBeanMapper;

    @Mock
    PlaneDAO planeDAO;

    @InjectMocks
    PlaneServiceImpl planeService;
    
    PlaneTO planeTO1;
    Plane plane1;
    Plane plane2;
    Plane plane3;


    @BeforeMethod
    public void setup() {
        planeTO1 = new PlaneTO();
        planeTO1.setId(1);
        plane1 = new Plane();
        plane1.setId(1);
        plane2 = new Plane();
        plane2.setId(2);
        plane3 = new Plane();
        plane3.setId(3);

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddPlane() {
        planeService.addPlane(planeTO1);
        Mockito.verify(planeDAO).addPlane(plane1);
    }


    @Test
    public void testUpdatePlane() {
        Mockito.when(planeDAO.updatePlane(plane1)).thenReturn(plane1);
        planeService.updatePlane(planeTO1);
        Mockito.verify(planeDAO).updatePlane(plane1);
    }

    
    @Test
    public void testRemovePlane(){
        Mockito.when(planeDAO.getPlaneById(planeTO1.getId())).thenReturn(plane1);
        planeService.removePlane(planeTO1);
        Mockito.verify(planeDAO).deletePlane(plane1);
    }
    

    @Test
    public void testGetPlanesList(){
        List<Plane> planes = new ArrayList<>();
        planes.add(plane1);
        planes.add(plane2);
        planes.add(plane3);

        Mockito.when(planeDAO.getAllPlanes()).thenReturn(planes);

        List<PlaneTO> planeTOS = planeService.planeList();
        Mockito.verify(planeDAO).getAllPlanes();

        Assert.assertEquals(planeTOS.get(0).getId(), planes.get(0).getId());
        Assert.assertEquals(planeTOS.get(1).getId(), planes.get(1).getId());
        Assert.assertEquals(planeTOS.get(2).getId(), planes.get(2).getId());
        Assert.assertEquals(planeTOS.size(), planes.size());
    }
}
