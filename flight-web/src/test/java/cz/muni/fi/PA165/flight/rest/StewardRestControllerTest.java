package cz.muni.fi.PA165.flight.rest;


import cz.muni.fi.PA165.flight.service.StewardService;
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

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MySpringMvcConfig.class}, loader = AnnotationConfigWebContextLoader.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class StewardRestControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private StewardService stewardService;

    private MockMvc mockMvc;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void readSteward() throws Exception {
        mockMvc.perform(get("/rest/steward/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(stewardService.getAllStewards().size())))
                .andExpect(jsonPath("$[0].id", is((int) stewardService.getAllStewards().get(0).getId())));
    }

    @Test
    public void StewardNotFound() throws Exception {
        mockMvc.perform(get("/rest/steward/666")
                .contentType(contentType))
                .andExpect(status().isNotFound());
    }

    @Test
    public void StewardFound() throws Exception {
        mockMvc.perform(get("/rest/steward/1")
                .contentType(contentType))
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id", is((int) stewardService.getStewardById(1).getId())))
                .andExpect(status().isOk());
    }

    @Test
    public void createSteward() throws Exception {
        String stewardJson = "{\"firstName\":\"Benjamin\",\"lastName\":\"Franklin\"}";
        this.mockMvc.perform(post("/rest/steward/")
                .contentType(contentType)
                .content(stewardJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void updateSteward() throws Exception {
        String stewardJson = "{\"id\":\"1\",\"firstName\":\"Benjamin\",\"lastName\":\"Franklin\"}";
        this.mockMvc.perform(put("/rest/steward/1")
                .contentType(contentType)
                .content(stewardJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void removeSteward() throws Exception {
        this.mockMvc.perform(delete("/rest/steward/1")
                .contentType(contentType))
                .andExpect(status().isOk());
    }

}