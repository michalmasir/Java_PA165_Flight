package cz.muni.fi.PA165.flight.web.controller;
import cz.muni.fi.PA165.flight.service.AirportService;
import cz.muni.fi.PA165.flight.transfer.AirportTO;
import java.util.List;
import java.util.Locale;

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

/**
 *
 * @author Michal Mašír
 */
@Controller
@RequestMapping("/airport")
public class AirportController {

    @Autowired
    private AirportService airportService;

    @Autowired
    private MessageSource messageSource;

    /*
    @ModelAttribute("airports")
    public List<AirportTO> allAirports() {
        return airportService.getAirportsList();
    }
    */

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable long id, Model model) {
        //todo return null from dozer if not found
        AirportTO airportTO = airportService.getAirportById(id);
        model.addAttribute("airport", airportTO);
        return "airport/form";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String process_form(@ModelAttribute AirportTO airportTO,BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder, Locale locale) {
        if (bindingResult.hasErrors()) {
            for (ObjectError err : bindingResult.getAllErrors()) {
                System.err.println(err);
            }
            //return previouse form
            return "airport/form";
        }
        else if(airportTO.getId()==0){
            airportService.addAirport(airportTO);
            redirectAttributes.addFlashAttribute(
                    "message",
                    messageSource.getMessage("airport.add.flash", airportMessage(airportTO), locale)
            );
        }
        else{
            airportService.updateAirport(airportTO);
            redirectAttributes.addFlashAttribute(
                    "message",
                    messageSource.getMessage("airport.update.flash", airportMessage(airportTO), locale)
            );
        }
        return "redirect:" + uriBuilder.path("/airport/list").build();
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(@ModelAttribute AirportTO airportTO, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder, Locale locale) {
        if (bindingResult.hasErrors()) {
            for (ObjectError err : bindingResult.getAllErrors()) {
                System.err.println(err);
            }
            //return previouse form
            return "airport/form";
        }
        else {
            airportService.addAirport(airportTO);
            redirectAttributes.addFlashAttribute(
                    "message",
                    messageSource.getMessage("airport.add.flash", airportMessage(airportTO), locale)
            );
        }
        return "redirect:" + uriBuilder.path("/airport/list").build();
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
        this.ensureDataInDb();
        model.addAttribute("airports", airportService.getAirportsList());
        return "airport/list";
    }

    private static Object[] airportMessage(AirportTO airportTO) {
        return new Object[]{airportTO.getId(), airportTO.getName(), airportTO.getCity(), airportTO.getState()};
    }

    private static boolean db_ready = false;

    private void ensureDataInDb() {
        if (db_ready) {
            return;
        }
        db_ready = true;

        AirportTO a1 = new AirportTO();
        a1.setCity("Brno");
        a1.setName("Masarykovo letisko");
        a1.setState("CZ");

        AirportTO a2 = new AirportTO();
        a2.setCity("Banská Bystrica");
        a2.setName("Sliač");
        a2.setState("SK");

        AirportTO a3 = new AirportTO();
        a3.setCity("Viedeň");
        a3.setName("Vienna airport");
        a3.setState("AU");

        AirportTO a4 = new AirportTO();
        a4.setCity("Los Angeles");
        a4.setName("LAX");
        a4.setState("USA");

        airportService.addAirport(a1);
        airportService.addAirport(a2);
        airportService.addAirport(a3);
        airportService.addAirport(a4);

    }
}