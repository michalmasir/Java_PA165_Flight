package cz.muni.fi.PA165.flight.web.controller;

import cz.muni.fi.PA165.flight.service.StewardService;
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

    @RequestMapping(value = "/update/{id}",method = RequestMethod.GET)
    public String update(@PathVariable long id, Model model){

        StewardTO steward = stewardService.getStewardById(id);
        model.addAttribute("steward",steward);

        return "steward/form";
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String process_form(@ModelAttribute("steward") StewardTO steward,BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder, Locale locale){

        if(bindingResult.hasErrors()){
            for(ObjectError err : bindingResult.getAllErrors()){
                System.err.println(err);
            }

            return "steward/form";
        }

        if(steward.getId() == 0){
            stewardService.addSteward(steward);
            redirectAttributes.addFlashAttribute("message",messageSource.getMessage("steward.add.flash",stewardData(steward),locale));
        }
        else{
            stewardService.updateSteward(steward);
            redirectAttributes.addFlashAttribute("message",messageSource.getMessage("steward.update.flash",stewardData(steward),locale));
        }

        return "redirect"+ uriBuilder.path("/steward/list").build();
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model){

        StewardTO steward = new StewardTO();
        steward.setFirstName("John");
        steward.setLastName("Doe");
        model.addAttribute("steward",steward);

        return "steward/form";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable long id,RedirectAttributes redirectAttributes, Locale locale, UriComponentsBuilder uriBuilder){

        StewardTO steward = stewardService.getStewardById(id);
        stewardService.deleteSteward(steward);
        redirectAttributes.addAttribute("message",messageSource.getMessage("steward.delete.flash",stewardData(steward),locale));

        return "redirect"+ uriBuilder.path("/steward/list").build();
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        ensureDataInDb();
        model.addAttribute("stewards",stewardService.getAllStewards());

        return "steward/list";
    }


    private static Object[] stewardData(StewardTO steward) {
        return new Object[]{steward.getId(), steward.getFirstName(),steward.getLastName()};
    }

    private static boolean db_ready = false;

    private void ensureDataInDb() {
        if (db_ready) {
            return;
        }
        db_ready = true;

        StewardTO steward1 = new StewardTO();
        steward1.setFirstName("Bruce");
        steward1.setLastName("Wayne");

        StewardTO steward2 = new StewardTO();
        steward2.setFirstName("Barry");
        steward2.setLastName("Allen");

        StewardTO steward3 = new StewardTO();
        steward3.setFirstName("Oliver");
        steward3.setLastName("Queen");

        stewardService.addSteward(steward1);
        stewardService.addSteward(steward2);
        stewardService.addSteward(steward3);


    }

}
