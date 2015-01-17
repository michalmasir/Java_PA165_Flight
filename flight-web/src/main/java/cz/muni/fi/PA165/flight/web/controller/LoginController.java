package cz.muni.fi.PA165.flight.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * User: PC
 * Date: 17. 1. 2015
 * Time: 23:06
 */


@Controller
public class LoginController {
    @RequestMapping(value = "/loginPage", method = RequestMethod.GET)
    public String login(ModelMap model) {
        return "login/login_page";
    }
}