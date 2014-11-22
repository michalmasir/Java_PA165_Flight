package cz.fi.muni.PA165.flight.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * User: PC
 * Date: 22. 11. 2014
 * Time: 15:15
 */

@Controller
@RequestMapping("/flight")
public class FlightController {

    @RequestMapping(value = "/list", method = RequestMethod.GET)
     public String list() {
        return "list";
     }

}

