package cz.muni.fi.PA165.flight.web.controller;

import cz.muni.fi.PA165.flight.service.StewardService;
import cz.muni.fi.PA165.flight.transfer.StewardTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.annotation.Secured;
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
import java.util.Locale;

/**
 * Created by M on 28.11.2014.
 */

@Controller
@RequestMapping("/steward")
public class StewardController {

    @Autowired
    private StewardService stewardService;

    @Autowired
    private MessageSource messageSource;

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable long id, Model model) {

        StewardTO steward = stewardService.getStewardById(id);
        model.addAttribute("steward", steward);

        return "steward/form";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String process_form(@ModelAttribute("steward") @Valid StewardTO steward, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder, Locale locale) {

        if (bindingResult.hasErrors()) {
            return "steward/form";
        }

        if (steward.getId() == 0) {
            stewardService.addSteward(steward);
            redirectAttributes.addFlashAttribute("message", messageSource.getMessage("steward.add.flash", stewardData(steward), locale));
        } else {
            stewardService.updateSteward(steward);
            redirectAttributes.addFlashAttribute("message", messageSource.getMessage("steward.update.flash", stewardData(steward), locale));
        }

        return "redirect:" + uriBuilder.path("/steward/list").build();
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model) {

        StewardTO steward = new StewardTO();
        steward.setFirstName("John");
        steward.setLastName("Doe");
        model.addAttribute("steward", steward);

        return "steward/form";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable long id, RedirectAttributes redirectAttributes, Locale locale, UriComponentsBuilder uriBuilder) {

        StewardTO steward = stewardService.getStewardById(id);
        stewardService.deleteSteward(steward);
        redirectAttributes.addAttribute("message", messageSource.getMessage("steward.delete.flash", stewardData(steward), locale));

        return "redirect:" + uriBuilder.path("/steward/list").build();
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("stewards", stewardService.getAllStewards());
        return "steward/list";
    }


    private static Object[] stewardData(StewardTO steward) {
        return new Object[]{steward.getId(), steward.getFirstName(), steward.getLastName()};
    }

}
