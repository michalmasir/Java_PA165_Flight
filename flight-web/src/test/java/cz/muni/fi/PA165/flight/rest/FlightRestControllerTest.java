package cz.muni.fi.PA165.flight.rest;


import cz.muni.fi.PA165.flight.service.AirportService;
import cz.muni.fi.PA165.flight.service.FlightService;
import cz.muni.fi.PA165.flight.service.PlaneService;
import cz.muni.fi.PA165.flight.transfer.AirportTO;
import cz.muni.fi.PA165.flight.transfer.FlightTO;
import cz.muni.fi.PA165.flight.transfer.PlaneTO;
import cz.muni.fi.PA165.flight.web.config.MySpringMvcConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;
import java.util.Calendar;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MySpringMvcConfig.class}, loader = AnnotationConfigWebContextLoader.class)
public class FlightRestControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private FlightService flightService;

    @Autowired
    private PlaneService planeService;

    @Autowired
    private AirportService airportService;

    private MockMvc mockMvc;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
        if (flightService.getFlightById(1) == null) {
            FlightTO flight = new FlightTO();

            PlaneTO plane = new PlaneTO();
            planeService.addPlane(plane);
            plane = planeService.getPlaneById(1);

            AirportTO airportTO = new AirportTO();
            airportService.addAirport(airportTO);
            airportTO = airportService.getAirportById(1);

            flight.setPlane(plane);
            flight.setFrom(airportTO);
            flight.setTo(airportTO);

            Calendar departure1 = Calendar.getInstance();
            departure1.set(2010, Calendar.JANUARY, 1);
            Calendar arrival1 = Calendar.getInstance();
            arrival1.set(2011, Calendar.FEBRUARY, 1);

            flight.setArrivalTime(arrival1.getTime());
            flight.setDepartureTime(departure1.getTime());
            flightService.addFlight(flight);
        }
    }

    @Test
    public void readFlights() throws Exception {
        mockMvc.perform(get("/rest/flight/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(flightService.getFlightsList().size())))
                .andExpect(jsonPath("$[0].id", is((int) flightService.getFlightsList().get(0).getId())))
                .andExpect(jsonPath("$[0].plane.id", is((int) flightService.getFlightsList().get(0).getPlane().getId())));
    }

    @Test
    public void flightNotFound() throws Exception {
        mockMvc.perform(get("/rest/flight/314")
                .contentType(contentType))
                .andExpect(status().isNotFound());
    }

    @Test
    public void flightFound() throws Exception {
        mockMvc.perform(get("/rest/flight/1")
                .contentType(contentType))
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id", is((int) flightService.getFlightById(1).getId())))
                .andExpect(jsonPath("$.plane.id", is((int) flightService.getFlightById(1).getPlane().getId())))
                .andExpect(status().isOk());
    }

     @Test
     public void createFlight() throws Exception {
         String flightJson = "{\"departureTime\":973721940000,\"arrivalTime\":976362300000,\"stewards\":[{\"id\":\"3\"}],\"plane\":{\"id\":\"2\"},\"from\":{\"id\":\"1\"},\"to\":{\"id\":\"2\"}}";
         this.mockMvc.perform(post("/rest/flight/")
                 .contentType(contentType)
                 .content(flightJson))
                 .andExpect(status().isCreated());
     }

    @Test
    public void updateFlight() throws Exception {
        String flightJson = "{\"id\":\"1\",\"departureTime\":973721940000,\"arrivalTime\":976362300000,\"stewards\":[{\"id\":\"2\"}],\"plane\":{\"id\":\"1\"},\"from\":{\"id\":\"1\"},\"to\":{\"id\":\"2\"}}";
        this.mockMvc.perform(put("/rest/flight/1")
                .contentType(contentType)
                .content(flightJson))
                .andExpect(status().isCreated());
    }

}