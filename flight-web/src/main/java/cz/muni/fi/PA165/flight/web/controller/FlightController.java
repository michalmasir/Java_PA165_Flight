package cz.muni.fi.PA165.flight.web.controller;

import cz.muni.fi.PA165.flight.enums.UserRole;
import cz.muni.fi.PA165.flight.service.AirportService;
import cz.muni.fi.PA165.flight.service.FlightService;
import cz.muni.fi.PA165.flight.service.PlaneService;
import cz.muni.fi.PA165.flight.service.StewardService;
import cz.muni.fi.PA165.flight.transfer.FlightTO;
import cz.muni.fi.PA165.flight.web.validation.FlightValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.*;

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

    @Autowired
    private PlaneService planeService;

    @Autowired
    private AirportService airportService;

    @Autowired
    private StewardService stewardService;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private FlightValidation flightValidation;

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable long id, Model model) {
        FlightTO flight = flightService.getFlightById(id);
        return form(model, flight);
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model) {
        FlightTO flight = new FlightTO();


        Calendar cld = Calendar.getInstance();
        flight.setDepartureTime(cld.getTime());

        cld.add(Calendar.HOUR, 1);
        flight.setArrivalTime(cld.getTime());
        return form(model, flight);
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String process_form(@Valid @ModelAttribute("flight") FlightTO flight, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder, Locale locale) {

        if (bindingResult.hasErrors()) {
            return form(model, flight);
        }
        if (flight.getId() == 0) {
            flightService.addFlight(flight);
            redirectAttributes.addFlashAttribute(
                    "message",
                    messageSource.getMessage("flight.add.flash", flightData(flight), locale)
            );
        } else {
            flightService.updateFlight(flight);
            redirectAttributes.addFlashAttribute(
                    "message",
                    messageSource.getMessage("flight.update.flash", flightData(flight), locale)
            );
        }
        return "redirect:" + uriBuilder.path("/flight/list").build();
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("flights", flightService.getFlightsList());
        return "flight/list";
    }


    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable long id, RedirectAttributes redirectAttributes, Locale locale, UriComponentsBuilder uriBuilder) {
        FlightTO flight = flightService.getFlightById(id);
        flightService.removeFlight(flight);
        redirectAttributes.addFlashAttribute(
                "message",
                messageSource.getMessage("flight.delete.flash", flightData(flight), locale)
        );
        return "redirect:" + uriBuilder.path("/flight/list").build();
    }


    private String form(Model model, FlightTO flight) {
        model.addAttribute("flight", flight);
        model.addAttribute("planes", planeService.planeList());
        model.addAttribute("airports", airportService.getAirportsList());
        model.addAttribute("stewards", stewardService.getAllStewards());

        return "flight/form";
    }

    private static Object[] flightData(FlightTO flight) {
        return new Object[]{flight.getId(), flight.getFrom().getName(), flight.getTo().getName(), flight.getPlane().getType()};
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(flightValidation);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}

