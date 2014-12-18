package cz.muni.fi.PA165.flight.service;

import cz.muni.fi.PA165.flight.dao.FlightDAO;
import cz.muni.fi.PA165.flight.dao.PlaneDAO;
import cz.muni.fi.PA165.flight.entity.Flight;
import cz.muni.fi.PA165.flight.entity.Plane;
import cz.muni.fi.PA165.flight.entity.Steward;
import cz.muni.fi.PA165.flight.service.impl.FlightServiceImpl;
import cz.muni.fi.PA165.flight.service.impl.PlaneServiceImpl;
import cz.muni.fi.PA165.flight.transfer.FlightTO;
import cz.muni.fi.PA165.flight.transfer.PlaneTO;
import cz.muni.fi.PA165.flight.transfer.StewardTO;
import org.dozer.DozerBeanMapper;
import org.mockito.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.mockito.Mockito.verify;

/**
 * User: PC
 * Date: 5. 11. 2014
 * Time: 19:59
 */

@ContextConfiguration(locations = {"classpath:/application-context.xml"})
public class FlightServiceTest  extends AbstractTestNGSpringContextTests {

    @Spy
    DozerBeanMapper dozerBeanMapper;

    @Mock
    FlightDAO flightDAO;

    @Mock
    PlaneDAO planeDAO;


    @InjectMocks
    FlightServiceImpl flightService;



    private Flight flight1;
    private Flight flight2;

    private FlightTO flightTO1;
    private FlightTO flightTO2;

    private Plane plane1;
    private Plane plane2;

    private PlaneTO planeTO1;
    private PlaneTO planeTO2;


    private Steward steward;
    private StewardTO stewardTO;

    @BeforeMethod
    public void setup() {
        planeTO1 = new PlaneTO();
        planeTO1.setId(1);
        flightTO1 = new FlightTO();
        flightTO1.setId(1);

        Calendar departure1 = Calendar.getInstance();
        departure1.set(2000, Calendar.JANUARY, 1);
        Calendar arrival1 = Calendar.getInstance();
        arrival1.set(2000, Calendar.FEBRUARY, 1);

        flightTO1.setDepartureTime(departure1.getTime());
        flightTO1.setArrivalTime(arrival1.getTime());

        flightTO1.setPlane(planeTO1);


        planeTO2 = new PlaneTO();
        planeTO2.setId(2);
        flightTO2 = new FlightTO();
        planeTO2.setId(2);

        Calendar departure2 = Calendar.getInstance();
        departure2.set(2002, Calendar.JANUARY, 1);
        Calendar arrival2 = Calendar.getInstance();
        arrival2.set(2002, Calendar.FEBRUARY, 1);

        flightTO2.setDepartureTime(departure2.getTime());
        flightTO2.setArrivalTime(arrival2.getTime());

        flightTO2.setPlane(planeTO2);


        plane1 = new Plane();
        plane1.setId(1);

        plane2 = new Plane();
        plane2.setId(2);

        flight1 = new Flight();
        flight1.setId(1);
        flight1.setArrivalTime(arrival1.getTime());
        flight1.setDepartureTime(departure1.getTime());
        flight1.setPlane(plane1);

        flight2 = new Flight();
        flight2.setId(2);
        flight2.setArrivalTime(arrival2.getTime());
        flight2.setDepartureTime(departure2.getTime());
        flight2.setPlane(plane2);

        steward = new Steward();
        steward.setId(1);

        stewardTO = new StewardTO();
        stewardTO.setId(1);

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFlightAdd() {
        flightService.addFlight(flightTO1);
        Mockito.verify(flightDAO).addFlight(flight1);
    }


    @Test
    public void testGetFlights() {
        List<Flight> flights = new ArrayList<>();
        flights.add(flight1);
        flights.add(flight2);

        Mockito.when(flightDAO.getAllFlights()).thenReturn(flights);

        List<FlightTO> flightTOs = flightService.getFlightsList();
        Mockito.verify(flightDAO).getAllFlights();

        Assert.assertEquals(flightTOs.get(0).getId(), flights.get(0).getId());
        Assert.assertEquals(flightTOs.get(1).getId(), flights.get(1).getId());
        Assert.assertEquals(flightTOs.size(), flights.size());
    }


    @Test
    public void testFlightUpdate() {
        flightService.updateFlight(flightTO1);
        Mockito.verify(flightDAO).updateFlight(flight1);
    }

    @Test
    public void testFlightGetById() {
        long id = flight1.getId();

        Mockito.when(flightDAO.getFlightById(id)).thenReturn(flight1);
        FlightTO flightTO = flightService.getFlightById(id);
        Mockito.verify(flightDAO).getFlightById(id);

        Assert.assertEquals(id, flightTO.getId());
    }


    @Test
    public void testFlightGetDepartureByDate() {
        List<Flight> flights = new ArrayList<>();
        flights.add(flight1);
        flights.add(flight2);

        Calendar from = Calendar.getInstance();
        from.set(1999, Calendar.JANUARY, 1);
        Calendar to = Calendar.getInstance();
        to.set(2003, Calendar.FEBRUARY, 1);

        Mockito.when(flightDAO.getFlightsByDepartureDate(from.getTime(), to.getTime())).thenReturn(flights);

        List<FlightTO> flightTOs = flightService.getFlightsByDepartureDate(from.getTime(), to.getTime());
        Mockito.verify(flightDAO).getFlightsByDepartureDate(from.getTime(), to.getTime());

        Assert.assertEquals(flightTOs.get(0).getId(), flights.get(0).getId());
        Assert.assertEquals(flightTOs.get(1).getId(), flights.get(1).getId());
        Assert.assertEquals(flightTOs.size(), flights.size());
    }

    @Test
    public void testFlightGetArrivalByDate() {
        List<Flight> flights = new ArrayList<>();
        flights.add(flight1);
        flights.add(flight2);

        Calendar from = Calendar.getInstance();
        from.set(1999, Calendar.JANUARY, 1);
        Calendar to = Calendar.getInstance();
        to.set(2003, Calendar.FEBRUARY, 1);

        Mockito.when(flightDAO.getFlightsByArrivalDate(from.getTime(), to.getTime())).thenReturn(flights);

        List<FlightTO> flightTOs = flightService.getFlightsByArrivalDate(from.getTime(), to.getTime());
        Mockito.verify(flightDAO).getFlightsByArrivalDate(from.getTime(), to.getTime());

        Assert.assertEquals(flightTOs.get(0).getId(), flights.get(0).getId());
        Assert.assertEquals(flightTOs.get(1).getId(), flights.get(1).getId());
        Assert.assertEquals(flightTOs.size(), flights.size());
    }


    @Test
    public void testAssignSteward(){
       Mockito.when(flightDAO.safeAddSteward(flight1, steward)).thenReturn(true);
       boolean result =  flightService.assignStewardToFlight(flightTO1, stewardTO);
       Mockito.verify(flightDAO).safeAddSteward(flight1, steward);
        Assert.assertEquals(result, true);
    }

    @Test
    public void testFlightRemove(){
        Mockito.when(flightDAO.getFlightById(flightTO1.getId())).thenReturn(flight1);
        flightService.removeFlight(flightTO1);
        Mockito.verify(flightDAO).deleteFlight(flight1);
    }
    
}
