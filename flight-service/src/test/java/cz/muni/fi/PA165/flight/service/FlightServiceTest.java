package cz.muni.fi.PA165.flight.service;

import cz.muni.fi.PA165.flight.dao.FlightDAO;
import cz.muni.fi.PA165.flight.dao.PlaneDAO;
import cz.muni.fi.PA165.flight.entity.Flight;
import cz.muni.fi.PA165.flight.entity.Plane;
import cz.muni.fi.PA165.flight.service.impl.FlightServiceImpl;
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

import static org.mockito.Mockito.*;
import java.util.Calendar;

/**
 * User: PC
 * Date: 5. 11. 2014
 * Time: 19:59
 */

@ContextConfiguration(locations = { "classpath:/application-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class FlightServiceTest {

    @Spy
    DozerBeanMapper dozerBeanMapper;

    @Mock
    FlightDAO flightDAO;

    @Mock
    PlaneDAO planeDAO;

    @InjectMocks
    FlightServiceImpl flightService;

    @InjectMocks
    PlaneServiceImpl planeService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFlightAdd() {
        PlaneTO planeTO = new PlaneTO();

        FlightTO flightTO = new FlightTO();

        Calendar departure = Calendar.getInstance();
        departure.set(2000, Calendar.JANUARY, 1);
        Calendar arrival = Calendar.getInstance();
        arrival.set(2000, Calendar.FEBRUARY, 1);

        flightTO.setDepartureTime(departure.getTime());
        flightTO.setArrivalTime(arrival.getTime());

        flightTO.setPlane(planeTO);

        flightService.addFlight(flightTO);

        Flight flight = new Flight();
        flight.setArrivalTime(arrival.getTime());
        flight.setDepartureTime(departure.getTime());
        flight.setPlane(new Plane());

        verify(flightDAO).addFlight(flight);
    }
}
