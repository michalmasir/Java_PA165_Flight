package cz.muni.fi.PA165.flight.web.controller;

import cz.muni.fi.PA165.flight.enums.UserRole;
import cz.muni.fi.PA165.flight.service.UserService;
import cz.muni.fi.PA165.flight.transfer.UserTO;
import cz.muni.fi.PA165.flight.web.validation.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.annotation.Secured;
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
 * User: PC
 * Date: 21. 1. 2015
 * Time: 11:43
 */

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserValidation userValidation;

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model) {
        UserTO user = new UserTO();
        return form(model, user);
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String process_form(@Valid @ModelAttribute("user") UserTO user, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder, Locale locale) {

        if (bindingResult.hasErrors()) {
            for (ObjectError err : bindingResult.getAllErrors()) {
                System.err.println(err);
            }

            return form(model, user);
        }

        userService.addUser(user);
        redirectAttributes.addFlashAttribute(
                "message",
                messageSource.getMessage("user.add.flash", userData(user), locale)
        );

        return "redirect:" + uriBuilder.path("/user/list").build();
    }


    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "user/list";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/delete/{username}", method = RequestMethod.GET)
    public String delete(@PathVariable String username, RedirectAttributes redirectAttributes, Locale locale, UriComponentsBuilder uriBuilder) {
        UserTO user = userService.getUserByUsername(username);
        userService.removeUser(user);
        redirectAttributes.addFlashAttribute(
                "message",
                messageSource.getMessage("user.delete.flash", userData(user), locale)
        );
        return "redirect:" + uriBuilder.path("/user/list").build();
    }


    private String form(Model model, UserTO user) {
        model.addAttribute("user", user);
        model.addAttribute("userRoles", UserRole.values());
        return "user/form";
    }

    private static Object[] userData(UserTO user) {
        return new Object[]{user.getUsername()};
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(userValidation);
    }

}