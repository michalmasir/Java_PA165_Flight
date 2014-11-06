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


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testAddPlane() {
        PlaneTO planeTO = new PlaneTO();
        Plane plane = new Plane();

        planeService.addPlane(planeTO);
        verify(planeDAO).addPlane(plane);
    }
}
