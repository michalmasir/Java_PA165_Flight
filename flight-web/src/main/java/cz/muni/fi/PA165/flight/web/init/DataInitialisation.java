package cz.muni.fi.PA165.flight.web.init;

import cz.muni.fi.PA165.flight.service.*;
import cz.muni.fi.PA165.flight.transfer.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

/**
 * User: PC
 * Date: 28. 11. 2014
 * Time: 13:42
 */
@Component
public class DataInitialisation implements InitializingBean {

    @Autowired
    private FlightService flightService;

    @Autowired
    private PlaneService planeService;

    @Autowired
    private AirportService airportService;

    @Autowired
    private StewardService stewardService;


    @Autowired
    private UserService userService;

    @Override
    public void afterPropertiesSet() throws Exception {
        PlaneTO planeTO = new PlaneTO();
        planeTO.setManufacturer("Boeing");
        planeTO.setType("747");
        planeTO.setTankCapacity(500000);
        planeTO.setFuelLeft(3000);
        planeService.addPlane(planeTO);

        AirportTO airportTO = new AirportTO();
        airportTO.setName("MR Stefanika");
        airportTO.setCity("BA");
        airportTO.setState("SK");
        airportService.addAirport(airportTO);

        AirportTO airportTO1 = new AirportTO();
        airportTO1.setName("V Havla");
        airportTO1.setCity("Prague");
        airportTO1.setState("CR");

        airportService.addAirport(airportTO1);

        StewardTO stewardTO = new StewardTO();
        stewardTO.setFirstName("john");
        stewardTO.setLastName("tester");
        stewardService.addSteward(stewardTO);


        FlightTO flight = new FlightTO();
        flight.setPlane(planeService.getPlaneById(1));
        flight.setFrom(airportService.getAirportById(1));
        flight.setTo(airportService.getAirportById(2));
        Calendar cld = Calendar.getInstance();
        cld.set(2000, Calendar.AUGUST, 11);
        flight.setDepartureTime(cld.getTime());

        cld.set(2000, Calendar.SEPTEMBER, 12, 12, 45);
        flight.setArrivalTime(cld.getTime());
        flight.getStewards().add(stewardService.getStewardById(1));
        flightService.addFlight(flight);
        
        PlaneTO planeTO1 = new PlaneTO();
        planeTO1.setManufacturer("Boeing");
        planeTO1.setType("747");
        planeTO1.increaseTotalFlightDistance(200);
        planeTO1.setPassangerSeatsCount(300);
        planeTO1.setStaffSeatsCount(8);
        planeTO1.setTankCapacity(10000);
        planeTO1.tankFuel(544);
        planeTO1.increaseTotalFlightTime(1244);
        planeTO1.increaseTotalFlightDistance(65998);
        planeTO1.setLastRevisionTime(new Date(5416));

        planeService.addPlane(planeTO1);


        AirportTO a1 = new AirportTO();
        a1.setCity("Brno");
        a1.setName("Masarykovo letisko");
        a1.setState("CZ");

        AirportTO a2 = new AirportTO();
        a2.setCity("Bansk\u00E1 Bystrica");
        a2.setName("Slia\u010D");
        a2.setState("SK");

        AirportTO a3 = new AirportTO();
        a3.setCity("Viede\u0148");
        a3.setName("Vienna airport");
        a3.setState("AU");

        AirportTO a4 = new AirportTO();
        a4.setCity("Los Angeles");
        a4.setName("LAX");
        a4.setState("USA");

        airportService.addAirport(a1);
        airportService.addAirport(a2);
        airportService.addAirport(a3);
        airportService.addAirport(a4);

        StewardTO steward1 = new StewardTO();
        steward1.setFirstName("Bruce");
        steward1.setLastName("Wayne");

        StewardTO steward2 = new StewardTO();
        steward2.setFirstName("Barry");
        steward2.setLastName("Allen");

        StewardTO steward3 = new StewardTO();
        steward3.setFirstName("Oliver");
        steward3.setLastName("Queen");

        stewardService.addSteward(steward1);
        stewardService.addSteward(steward2);
        stewardService.addSteward(steward3);

        UserTO userTO = new UserTO();
        userTO.setUsername("test");
        userTO.setPassword(new BCryptPasswordEncoder().encode("test"));
        userService.addUser(userTO);
    }
}
