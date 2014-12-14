package cz.muni.fi.PA165.flight.web.controller;

import cz.muni.fi.PA165.flight.service.AirportService;
import cz.muni.fi.PA165.flight.transfer.AirportTO;

import cz.muni.fi.PA165.flight.web.validation.AirportValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.Locale;

/**
 * Controller for airport entity
 * @author Michal Mašír
 */
@Controller
@RequestMapping("/airport")
public class AirportController {

    @Autowired
    private AirportService airportService;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable long id, Model model) {
        AirportTO airportTO = airportService.getAirportById(id);
        model.addAttribute("airport", airportTO);
        return "airport/form";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String process_form(@ModelAttribute("airport") @Valid AirportTO airportTO, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder, Locale locale) {
        if (bindingResult.hasErrors()) {
            for (ObjectError err : bindingResult.getAllErrors()) {
                System.err.println(err);
            }
            //return previous form
            return "airport/form";
        } else if (airportTO.getId() == 0) {
            airportService.addAirport(airportTO);
            redirectAttributes.addFlashAttribute(
                    "message",
                    messageSource.getMessage("airport.add.flash", airportMessage(airportTO), locale)
            );
        } else {
            airportService.updateAirport(airportTO);
            redirectAttributes.addFlashAttribute(
                    "message",
                    messageSource.getMessage("airport.update.flash", airportMessage(airportTO), locale)
            );
        }
        return "redirect:" + uriBuilder.path("/airport/list").build();
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model) {
        AirportTO airportTO = new AirportTO();
        airportTO.setCity("");
        airportTO.setName("");
        airportTO.setState("");
        model.addAttribute("airport", airportTO);
        return "airport/form";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable long id, RedirectAttributes redirectAttributes, Locale locale, UriComponentsBuilder uriBuilder) {
        AirportTO airportTO = airportService.getAirportById(id);
        airportService.removeAirport(airportTO);
        redirectAttributes.addFlashAttribute(
                "message",
                messageSource.getMessage("airport.delete.flash", airportMessage(airportTO), locale)
        );
        return "redirect:" + uriBuilder.path("/airport/list").build();
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("airports", airportService.getAirportsList());
        return "airport/list";
    }

    private static Object[] airportMessage(AirportTO airportTO) {
        return new Object[]{airportTO.getId(), airportTO.getName(), airportTO.getCity(), airportTO.getState()};
    }


    @InitBinder
    protected void initBinder(WebDataBinder binder) {
       binder.addValidators(new AirportValidation());
    }

}