package cz.muni.fi.PA165.flight.service;

import cz.muni.fi.PA165.flight.dao.AirportDAO;
import cz.muni.fi.PA165.flight.dao.FlightDAO;
import cz.muni.fi.PA165.flight.entity.Airport;
import cz.muni.fi.PA165.flight.entity.Flight;
import cz.muni.fi.PA165.flight.service.impl.AirportServiceImpl;
import cz.muni.fi.PA165.flight.service.impl.FlightServiceImpl;
import cz.muni.fi.PA165.flight.transfer.AirportTO;
import cz.muni.fi.PA165.flight.transfer.FlightTO;
import org.dozer.DozerBeanMapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Calendar;

import static org.mockito.Mockito.verify;

@ContextConfiguration(locations = {"classpath:/application-context.xml"})
public class AirportServiceTest extends AbstractTestNGSpringContextTests {

    @Spy
    DozerBeanMapper dozerBeanMapper;
    @Mock
    FlightDAO flightDAO;
    @Mock
    AirportDAO airportDAO;

    @InjectMocks
    FlightServiceImpl flightService;
    @InjectMocks
    AirportServiceImpl airportService;

    private Airport airport1;
    private Airport airport2;
    private Airport airport3;

    private AirportTO airportTO1;
    private AirportTO airportTO2;
    private AirportTO airportTO3;

    private Flight flight1;
    private Flight flight2;

    private FlightTO flightTO1;
    private FlightTO flightTO2;

    @BeforeMethod
    public void setup() {
        //Airports
        airportTO1 = new AirportTO();
        airportTO1.setId(0);
        airportTO1.setName("Masarykovo letisko");
        airportTO1.setCity("Brno");
        airportTO1.setState("CZ");

        airportTO2 = new AirportTO();
        airportTO2.setId(1);
        airportTO2.setName("LAX");
        airportTO2.setState("US");
        airportTO2.setCity("Los Angeles");

        airportTO3 = new AirportTO();
        airportTO3.setId(2);
        airportTO3.setName("Letisko Bratislava");
        airportTO3.setState("SK");
        airportTO3.setCity("Bratislava");

        //Flights
        flightTO1 = new FlightTO();
        flightTO1.setId(1);

        Calendar departure1 = Calendar.getInstance();
        departure1.set(2000, Calendar.JANUARY, 1);
        Calendar arrival1 = Calendar.getInstance();
        arrival1.set(2000, Calendar.FEBRUARY, 1);

        flightTO1.setDepartureTime(departure1.getTime());
        flightTO1.setArrivalTime(arrival1.getTime());
        flightTO1.setFrom(airportTO1);
        flightTO1.setTo(airportTO2);

        flightTO2 = new FlightTO();
        flightTO2.setId(1);
        Calendar departure2 = Calendar.getInstance();
        departure2.set(2002, Calendar.JANUARY, 1);
        Calendar arrival2 = Calendar.getInstance();
        arrival2.set(2002, Calendar.FEBRUARY, 1);

        flightTO2.setDepartureTime(departure2.getTime());
        flightTO2.setArrivalTime(arrival2.getTime());
        flightTO2.setFrom(airportTO2);
        flightTO2.setTo(airportTO3);


        airport1 = new Airport();
        airport1.setId(0);
        airport1.setName("Masarykovo letisko");
        airport1.setCity("Brno");
        airport1.setState("CZ");

        airport2 = new Airport();
        airport2.setId(1);
        airport2.setName("LAX");
        airport2.setState("US");
        airport2.setCity("Los Angeles");

        airport3 = new Airport();
        airport3.setId(2);
        airport3.setName("Letisko Bratislava");
        airport3.setState("SK");
        airport3.setCity("Bratislava");

        flight1 = new Flight();
        flight1.setId(1);
        flight1.setArrivalTime(arrival1.getTime());
        flight1.setDepartureTime(departure1.getTime());
        flight1.setFrom(airport1);
        flight1.setTo(airport2);

        flight2 = new Flight();
        flight2.setId(2);
        flight2.setArrivalTime(arrival2.getTime());
        flight2.setDepartureTime(departure2.getTime());
        flight2.setFrom(airport2);
        flight2.setTo(airport3);

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddAirpot(){
        airportService.addAirpot(airportTO1);
        verify(airportDAO).addAirport(airport1);
    }

    @Test
    public void testRemoveAirpot(){

    }

    @Test
    public void testUpdateAirport(){

    }

    @Test
    public void testGetAirportsList(){}
}