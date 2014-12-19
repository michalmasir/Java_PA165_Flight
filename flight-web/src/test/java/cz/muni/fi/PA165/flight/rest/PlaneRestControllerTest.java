package cz.muni.fi.PA165.flight.rest;

import cz.muni.fi.PA165.flight.service.PlaneService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.nio.charset.Charset;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MySpringMvcConfig.class}, loader = AnnotationConfigWebContextLoader.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PlaneRestControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private PlaneService planeService;

    private MockMvc mockMvc;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
        if (planeService.getPlaneById(1) == null) {

            PlaneTO plane = new PlaneTO();
            planeService.addPlane(plane);
        }
    }

    @Test
    public void getPlanes() throws Exception {
        mockMvc.perform(get("/rest/plane/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize( planeService.planeList().size() )))
                .andExpect(jsonPath("$[0].id", is((int) planeService.planeList().get(0).getId())));
    }

    @Test
    public void planeNotExists() throws Exception {
        mockMvc.perform(get("/rest/plane/99999")
                .contentType(contentType))
                .andExpect(status().isNotFound());
    }

    @Test
    public void planeExists() throws Exception {
        mockMvc.perform(get("/rest/plane/1")
                .contentType(contentType))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is((int) planeService.getPlaneById(1).getId())))
                .andExpect(jsonPath("$.plane.id", is((int) planeService.getPlaneById(1).getId())));
    }

    @Test
    public void addPlane() throws Exception {
        String planeJson = "{\"id\":1,\"fuelLeft\":3000,\"manufacturer\":\"Boeing\",\"type\":\"737\",\"passangerSeatsCount\":300,\"staffSeatsCount\":8,\"tankCapacity\":50000,\"lastRevisionTime\":1.1.2014 4:28,\"totalFlightTime\":6565,\"totalFlightDistance\":588}";
        this.mockMvc.perform(post("/rest/plane/")
                .contentType(contentType)
                .content(planeJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void updatePlane() throws Exception {
        String planeJson = "{\"id\":1,\"fuelLeft\":3000,\"manufacturer\":\"Boeing Updated\",\"type\":\"737\",\"passangerSeatsCount\":300,\"staffSeatsCount\":8,\"tankCapacity\":50000,\"lastRevisionTime\":1.1.2014 4:28,\"totalFlightTime\":6565,\"totalFlightDistance\":588}";
        this.mockMvc.perform(put("/rest/plane/1")
                .contentType(contentType)
                .content(planeJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void removePlane() throws Exception {
        this.mockMvc.perform(delete("/rest/plane/1")
                .contentType(contentType))
                .andExpect(status().isOk());
    }

}