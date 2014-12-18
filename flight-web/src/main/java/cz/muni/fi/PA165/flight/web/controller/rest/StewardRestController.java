package cz.muni.fi.PA165.flight.web.controller.rest;

import cz.muni.fi.PA165.flight.service.StewardService;
import cz.muni.fi.PA165.flight.transfer.StewardTO;
import cz.muni.fi.PA165.flight.web.exceptions.EntityNotFoundException;
import cz.muni.fi.PA165.flight.web.json.JsonObjectBuilderHelper;
import cz.muni.fi.PA165.flight.web.validation.StewardValidation;
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
 * Created by M on 17.12.2014.
 */
@RestController
@RequestMapping("/rest/steward")
public class StewardRestController {

    @Autowired
    private StewardService stewardService;

    @Autowired
    private JsonObjectBuilderHelper jsonObjectBuilderHelper;

    @RequestMapping(value = "/", method = RequestMethod.GET, headers = JSON_HEADER, produces = JSON_DATA_TYPE)
    public String getStewards() {
        List<StewardTO> stewardTOList = stewardService.getAllStewards();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (StewardTO stewardTO : stewardTOList) {
            arrayBuilder.add(jsonObjectBuilderHelper.stewardJsonObjectBuilder(stewardTO));
        }
        return arrayBuilder.build().toString();

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = JSON_HEADER, produces = JSON_DATA_TYPE)
    public String getSteward(@PathVariable int id) {
        StewardTO steward = stewardService.getStewardById(id);
        if (steward != null) {
            return jsonObjectBuilderHelper.stewardJsonObjectBuilder(steward).build().toString();
        }
        throw new EntityNotFoundException(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = JSON_DATA_TYPE)
    public ResponseEntity<String> postJson(@Valid @RequestBody StewardTO stewardTO) {
        stewardService.addSteward(stewardTO);
        return new ResponseEntity<>(ServletUriComponentsBuilder.fromPath("/rest/steward" + stewardTO.getId()).build().toUriString(), null, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = JSON_DATA_TYPE)
    public ResponseEntity<String> putJson(@PathVariable Integer id, @Valid @RequestBody StewardTO stewardTO) {
        StewardTO testSteward = stewardService.getStewardById(id);
        if (testSteward != null) {
            stewardService.updateSteward(stewardTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        throw new EntityNotFoundException(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        StewardTO testSteward = stewardService.getStewardById(id);
        if (testSteward != null) {
            stewardService.deleteSteward(testSteward);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        throw new EntityNotFoundException(id);
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(new StewardValidation());
    }

}
