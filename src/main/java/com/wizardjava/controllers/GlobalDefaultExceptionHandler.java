package com.wizardjava.controllers;

import org.springframework.orm.hibernate4.HibernateJdbcException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

/**
 * Created by Codeine on 05.11.2016.
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    public static final String DEFAULT_ERROR_VIEW = "exception";

    @ExceptionHandler(HibernateJdbcException.class)
    public ModelAndView databaseError(Exception e) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;

    }
}
