package cz.muni.fi.PA165.flight.web.controller;

import cz.muni.fi.PA165.flight.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @Autowired
    private FlightService flightService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
     public String list(Model model) {
        model.addAttribute("flights", flightService.getFlightsList());
        return "flight/list";
     }

}

