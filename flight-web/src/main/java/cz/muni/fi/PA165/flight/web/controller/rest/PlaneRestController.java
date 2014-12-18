package cz.muni.fi.PA165.flight.web.controller.rest;

import cz.muni.fi.PA165.flight.service.PlaneService;
import cz.muni.fi.PA165.flight.transfer.PlaneTO;
import cz.muni.fi.PA165.flight.web.exceptions.EntityNotFoundException;
import cz.muni.fi.PA165.flight.web.json.JsonObjectBuilderHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
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
        return jsonObjectBuilderHelper.planeJsonObjectBuilder(planeService.getPlaneById(id)).build().toString();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = JSON_DATA_TYPE)
    public ResponseEntity<String> postJson(@RequestBody PlaneTO planeTO) {
        planeService.addPlane(planeTO);
        return new ResponseEntity<>(ServletUriComponentsBuilder.fromPath("/rest/plane/" + planeTO.getId()).build().toUriString(), null, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = JSON_DATA_TYPE)
    public ResponseEntity<String> putJson(@PathVariable Integer id, @RequestBody PlaneTO planeTO) {
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
}
