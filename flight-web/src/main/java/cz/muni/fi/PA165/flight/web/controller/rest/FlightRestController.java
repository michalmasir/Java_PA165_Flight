package cz.muni.fi.PA165.flight.web.controller.rest;

import cz.muni.fi.PA165.flight.service.FlightService;
import cz.muni.fi.PA165.flight.transfer.FlightTO;
import cz.muni.fi.PA165.flight.web.exceptions.EntityNotFoundException;
import cz.muni.fi.PA165.flight.web.json.JsonObjectBuilderHelper;
import cz.muni.fi.PA165.flight.web.validation.FlightValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static cz.muni.fi.PA165.flight.web.utils.Constants.JSON_DATA_TYPE;
import static cz.muni.fi.PA165.flight.web.utils.Constants.JSON_HEADER;

/**
 * User: PC
 * Date: 14. 12. 2014
 * Time: 11:58
 */
@RestController
@RequestMapping("/rest/flight")
public class FlightRestController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private JsonObjectBuilderHelper jsonObjectBuilderHelper;

    @RequestMapping(value = "/", method = RequestMethod.GET, headers = JSON_HEADER, produces = JSON_DATA_TYPE)
    public String getFlights() {
        List<FlightTO> flightTOs = flightService.getFlightsList();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (FlightTO flightTO : flightTOs) {
            arrayBuilder.add(jsonObjectBuilderHelper.flightJsonObjectBuilder(flightTO));
        }
        return arrayBuilder.build().toString();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = JSON_HEADER, produces = JSON_DATA_TYPE)
    public String getFlight(@PathVariable int id) {
        return jsonObjectBuilderHelper.flightJsonObjectBuilder(flightService.getFlightById(id)).build().toString();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = JSON_DATA_TYPE)
    public ResponseEntity<String> postJson(@Valid @RequestBody FlightTO flightTO) {
        flightService.addFlight(flightTO);
        return new ResponseEntity<>(ServletUriComponentsBuilder.fromPath("/rest/flight/" + flightTO.getId()).build().toUriString(), null, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = JSON_DATA_TYPE)
    public ResponseEntity<String> putJson(@PathVariable Integer id, @Valid @RequestBody FlightTO flightTO) {
        FlightTO test = flightService.getFlightById(id);
        if (test != null) {
            flightService.updateFlight(flightTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        throw new EntityNotFoundException(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        FlightTO test = flightService.getFlightById(id);
        if (test != null) {
            flightService.removeFlight(test);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        throw new EntityNotFoundException(id);
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
       binder.addValidators(new FlightValidation());
       SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
       dateFormat.setLenient(false);
       binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
