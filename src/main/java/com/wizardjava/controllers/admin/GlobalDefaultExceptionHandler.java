package com.wizardjava.controllers.admin;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.security.Principal;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    public static final String VIEW_PAGE_404 = "404";

    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView foundError(Exception e) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName(VIEW_PAGE_404);
        return mav;

    }
}
