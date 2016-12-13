package com.wizardjava.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class DeniedController {

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(ModelMap model, Principal principal) {
        String username = principal.getName();
        return "403";
    }
}
