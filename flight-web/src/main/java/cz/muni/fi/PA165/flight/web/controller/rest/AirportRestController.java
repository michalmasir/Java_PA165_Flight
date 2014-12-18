package cz.muni.fi.PA165.flight.web.controller.rest;

import cz.muni.fi.PA165.flight.service.AirportService;
import cz.muni.fi.PA165.flight.transfer.AirportTO;
import cz.muni.fi.PA165.flight.web.exceptions.EntityNotFoundException;
import cz.muni.fi.PA165.flight.web.json.JsonObjectBuilderHelper;
import cz.muni.fi.PA165.flight.web.validation.AirportValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.validation.Valid;
import java.util.List;

import static cz.muni.fi.PA165.flight.web.utils.Constants.JSON_DATA_TYPE;
import static cz.muni.fi.PA165.flight.web.utils.Constants.JSON_HEADER;

/**
 * Created by Michal Mašír on 17. 12. 2014.
 */
@RestController
@RequestMapping("/rest/airport")
public class AirportRestController {

    @Autowired
    private AirportService airportService;

    @Autowired
    private JsonObjectBuilderHelper jsonObjectBuilderHelper;

    @RequestMapping(value = "/", method = RequestMethod.GET, headers = JSON_HEADER, produces = JSON_DATA_TYPE)
    public String getAirports() {
        List<AirportTO> airportTOList = airportService.getAirportsList();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (AirportTO a : airportTOList) {
            arrayBuilder.add(jsonObjectBuilderHelper.airportJsonObjectBuilder(a));
        }
        return arrayBuilder.build().toString();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = JSON_HEADER, produces = JSON_DATA_TYPE)
    public String getAirport(@PathVariable int id) {
        AirportTO airportTO = airportService.getAirportById(id);
        if (airportTO != null) {
            return jsonObjectBuilderHelper.airportJsonObjectBuilder(airportTO).build().toString();
        }
        throw new EntityNotFoundException(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = JSON_DATA_TYPE)
    public ResponseEntity<String> postJson(@Valid @RequestBody AirportTO airportTO) {
        airportService.addAirport(airportTO);
        return new ResponseEntity<>(ServletUriComponentsBuilder.fromPath("/rest/airport/" + airportTO.getId()).build().toUriString(), null, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = JSON_DATA_TYPE)
    public ResponseEntity<String> putJson(@PathVariable Integer id, @Valid @RequestBody AirportTO airportTO) {
        if (airportService.getAirportById(id) != null) {
            airportService.updateAirport(airportTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        throw new EntityNotFoundException(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        AirportTO airportTO = airportService.getAirportById(id);
        if (airportTO != null) {
            airportService.removeAirport(airportTO);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        throw new EntityNotFoundException(id);
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(new AirportValidation());
    }
}
