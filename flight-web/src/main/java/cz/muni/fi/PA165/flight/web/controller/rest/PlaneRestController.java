package cz.muni.fi.PA165.flight.web.controller.rest;

import cz.muni.fi.PA165.flight.service.PlaneService;
import cz.muni.fi.PA165.flight.transfer.PlaneTO;
import cz.muni.fi.PA165.flight.web.exceptions.EntityNotFoundException;
import cz.muni.fi.PA165.flight.web.json.JsonObjectBuilderHelper;
import cz.muni.fi.PA165.flight.web.validation.FlightValidation;
import cz.muni.fi.PA165.flight.web.validation.PlaneValidation;
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
 * Date: 18. 12. 2014
 * Time: 13:23
 */
@RestController
@RequestMapping("/rest/plane")
public class PlaneRestController {

    @Autowired
    private PlaneService planeService;

    @Autowired
    private JsonObjectBuilderHelper jsonObjectBuilderHelper;

    @RequestMapping(value = "/", method = RequestMethod.GET, headers = JSON_HEADER, produces = JSON_DATA_TYPE)
    public String getPlanes() {
        List<PlaneTO> planeTOs = planeService.planeList();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (PlaneTO planeTO : planeTOs) {
            arrayBuilder.add(jsonObjectBuilderHelper.planeJsonObjectBuilder(planeTO));
        }
        return arrayBuilder.build().toString();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = JSON_HEADER, produces = JSON_DATA_TYPE)
    public String getPlane(@PathVariable int id) {
         PlaneTO plane = planeService.getPlaneById(id);
        if (plane != null) {
            return jsonObjectBuilderHelper.planeJsonObjectBuilder(plane).build().toString();
        }
        throw new EntityNotFoundException(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = JSON_DATA_TYPE)
    public ResponseEntity<String> postJson(@Valid @RequestBody PlaneTO planeTO) {
        planeService.addPlane(planeTO);
        return new ResponseEntity<>(ServletUriComponentsBuilder.fromPath("/rest/plane/" + planeTO.getId()).build().toUriString(), null, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = JSON_DATA_TYPE)
    public ResponseEntity<String> putJson(@PathVariable Integer id, @Valid @RequestBody PlaneTO planeTO) {
        PlaneTO test = planeService.getPlaneById(id);
        if (test != null) {
            planeService.updatePlane(planeTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        throw new EntityNotFoundException(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        PlaneTO test = planeService.getPlaneById(id);
        if (test != null) {
            planeService.removePlane(test);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        throw new EntityNotFoundException(id);
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
       binder.addValidators(new PlaneValidation());
       SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
       dateFormat.setLenient(false);
       binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
