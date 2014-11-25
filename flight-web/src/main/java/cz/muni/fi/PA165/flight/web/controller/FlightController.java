package cz.muni.fi.PA165.flight.web.controller;

import cz.muni.fi.PA165.flight.service.AirportService;
import cz.muni.fi.PA165.flight.service.FlightService;
import cz.muni.fi.PA165.flight.service.PlaneService;
import cz.muni.fi.PA165.flight.service.StewardService;
import cz.muni.fi.PA165.flight.transfer.AirportTO;
import cz.muni.fi.PA165.flight.transfer.FlightTO;
import cz.muni.fi.PA165.flight.transfer.PlaneTO;
import cz.muni.fi.PA165.flight.transfer.StewardTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.Locale;

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

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable long id, Model model) {
        //todo return null from dozer if not found
        FlightTO flight = flightService.getFlightById(id);
        return form(model, flight);
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model) {
        FlightTO flight = new FlightTO();

        //----TESTING CODE-------
        ensureDataInDb();
        Calendar cld = Calendar.getInstance();
        cld.set(2000, Calendar.AUGUST, 11);
        flight.setDepartureTime(cld.getTime());

        cld.set(2000, Calendar.SEPTEMBER, 12, 12, 45);
        flight.setArrivalTime(cld.getTime());
        //----END OF TESTING CODE----
        return form(model, flight);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String process_form(@Valid @ModelAttribute FlightTO flight, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder, Locale locale) {
        if (bindingResult.hasErrors()) {
            for (ObjectError err : bindingResult.getAllErrors()) {
                System.err.println(err);
            }

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
        ensureDataInDb();
        model.addAttribute("flights", flightService.getFlightsList());
        return "flight/list";
    }


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

    private static boolean db_ready = false;

    private void ensureDataInDb() {
        if (db_ready) {
            return;
        }
        db_ready = true;

        PlaneTO planeTO = new PlaneTO();
        planeTO.setManufacturer("Boeing");
        planeTO.setType("747");
        planeService.addPlane(planeTO);

        AirportTO airportTO = new AirportTO();
        airportTO.setName("MR Stefanika");
        airportTO.setCity("BA");
        airportTO.setState("SK");
        airportService.addAirport(airportTO);

        AirportTO airportTO1 = new AirportTO();
        airportTO1.setName("V Havla");
        airportTO1.setCity("Prague");
        airportTO1.setState("CR");

        airportService.addAirport(airportTO1);

        StewardTO stewardTO = new StewardTO();
        stewardTO.setFirstName("john");
        stewardTO.setLastName("tetser");
        stewardService.addSteward(stewardTO);


        FlightTO flight = new FlightTO();
        flight.setPlane(planeService.getPlaneBtId(1));
        flight.setFrom(airportService.getAirportById(1));
        flight.setTo(airportService.getAirportById(2));
        Calendar cld = Calendar.getInstance();
        cld.set(2000, Calendar.AUGUST, 11);
        flight.setDepartureTime(cld.getTime());

        cld.set(2000, Calendar.SEPTEMBER, 12, 12, 45);
        flight.setArrivalTime(cld.getTime());
        flightService.addFlight(flight);

    }
}

