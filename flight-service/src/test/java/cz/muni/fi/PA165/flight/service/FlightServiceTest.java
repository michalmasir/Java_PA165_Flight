package cz.muni.fi.PA165.flight.service;

import cz.muni.fi.PA165.flight.transfer.FlightTO;
import cz.muni.fi.PA165.flight.transfer.PlaneTO;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.util.Calendar;

/**
 * User: PC
 * Date: 5. 11. 2014
 * Time: 19:59
 */

//((PlaneServiceImpl) planeService).planeDAO.getAllPlanes()
@ContextConfiguration({"/application-context.xml",})
@TransactionConfiguration(defaultRollback=false)
public class FlightServiceTest extends AbstractTestNGSpringContextTests {

    @Inject
    private FlightService flightService;

    @Inject
    private PlaneService planeService;

    @BeforeMethod
    public void setup() {
    }

    @Test
    public void testFlightAdd() {
        PlaneTO plane = new PlaneTO();
        FlightTO flight = new FlightTO();

        Calendar departure = Calendar.getInstance();
        departure.set(2000, Calendar.JANUARY, 1);

        flight.setDepartureTime(departure.getTime());

        Calendar arrival = Calendar.getInstance();
        arrival.set(2000, Calendar.FEBRUARY, 1);

        flight.setArrivalTime(arrival.getTime());

        flight.setPlane(plane);
        planeService.addPlane(plane);
        flightService.addFlight(flight);

        Assert.assertEquals(flightService.getFlightsList().size(), 1);
    }
}
