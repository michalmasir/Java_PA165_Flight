package cz.muni.fi.PA165.flight.web.controller;

import cz.muni.fi.PA165.flight.service.PlaneService;
import cz.muni.fi.PA165.flight.transfer.PlaneTO;
import cz.muni.fi.PA165.flight.web.validation.PlaneValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author Michal Galan
 */

@Controller
@RequestMapping("/plane")
public class PlaneController {

    @Autowired
    private PlaneService planeService;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable long id, Model model) {
        PlaneTO plane = planeService.getPlaneById(id);
        return form(model, plane);
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model) {
        PlaneTO plane = new PlaneTO();
        Calendar cal = Calendar.getInstance();
        cal.set(2014, 1, 1);
        plane.setLastRevisionTime(cal.getTime());
        return form(model, plane);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String process_form(@Valid @ModelAttribute("plane") PlaneTO plane, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder, Locale locale) {
        if (bindingResult.hasErrors()) {
            for (ObjectError err : bindingResult.getAllErrors()) {
                System.err.println(err);
            }

            return form(model, plane);
        }
        if (plane.getId() == 0) {
            planeService.addPlane(plane);
            redirectAttributes.addFlashAttribute(
                    "message",
                    messageSource.getMessage("plane.add.flash", planeData(plane), locale)
            );
        } else {
            planeService.updatePlane(plane);
            redirectAttributes.addFlashAttribute(
                    "message",
                    messageSource.getMessage("plane.update.flash", planeData(plane), locale)
            );
        }
        return "redirect:" + uriBuilder.path("/plane/list").build();
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("planes", planeService.planeList());
        return "plane/list";
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable long id, RedirectAttributes redirectAttributes, Locale locale, UriComponentsBuilder uriBuilder) {
        PlaneTO plane = planeService.getPlaneById(id);
        planeService.removePlane(plane);
        redirectAttributes.addFlashAttribute(
                "message",
                messageSource.getMessage("plane.delete.flash", planeData(plane), locale)
        );
        return "redirect:" + uriBuilder.path("/plane/list").build();
    }


    private String form(Model model, PlaneTO plane) {
        model.addAttribute("plane", plane);
        model.addAttribute("planes", planeService.planeList());
        return "plane/form";
    }

    private static Object[] planeData(PlaneTO plane) {
        return new Object[]{plane.getId(), plane.getType(), plane.getManufacturer()};
    }


    @InitBinder
    protected void initBinder(WebDataBinder binder) {
       binder.addValidators( new PlaneValidation());
       SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
       dateFormat.setLenient(false);
       binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}

