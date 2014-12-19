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

import static org.hamcrest.Matcher.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MySpringMvcConfig.class}, loader = AnnotationConfigWebContextLoader.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AirportRestControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private AirportService airportService;

    private MockMvc mockMvc;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();

            AirportTO airportTO = new AirportTO();
            airportService.addAirport(airportTO);
            airportTO = airportService.getAirportById(1);

    }

    @Test
    public void readAirport() throws Exception {
        mockMvc.perform(get("/rest/airport/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(airportService.getAirportsList().size())))
                .andExpect(jsonPath("$[0].id", is((int) airportService.getAirportsList().get(0).getId())));
    }

    @Test
    public void AirportNotFound() throws Exception {
        mockMvc.perform(get("/rest/airport/666")
                .contentType(contentType))
                .andExpect(status().isNotFound());
    }

    @Test
    public void AirportFound() throws Exception {
        mockMvc.perform(get("/rest/airport/1")
                .contentType(contentType))
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id", is((int) airportService.getAirportById(1).getId())))
                .andExpect(status().isOk());
    }

    @Test
    public void createAirport() throws Exception {
        String airportJson = "{\"id\":\"1\",\"name\":\"Letisko\",\"state\":\"SK\", \"city\":\"Banska Bystrica\"}";
        this.mockMvc.perform(post("/rest/airport/")
                .contentType(contentType)
                .content(airportJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void updateAirport() throws Exception {
        String airportJson = "{\"name\":\"Letisko\",\"state\":\"SK\", \"city\":\"Banska Bystrica\"}";
        this.mockMvc.perform(put("/rest/flight/1")
                .contentType(contentType)
                .content(airportJson))
                .andExpect(status().isOk());
    }

    @Test
    public void removeAirport() throws Exception {
        this.mockMvc.perform(delete("/rest/airport/1")
                .contentType(contentType))
                .andExpect(status().isOk());
    }

}