package com.wizardjava.filter;

import org.springframework.beans.propertyeditors.LocaleEditor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Locale;

/**
 * Created by Codeine on 30.11.2016.
 */
public class InternationalizationFilter extends  GenericFilterBean  {

    private String localeParam = "lang";
    private LocaleResolver localeResolver;

    public InternationalizationFilter(String localeParam, LocaleResolver localeResolver) {
        this.localeParam = localeParam;
        this.localeResolver = localeResolver;
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain filterChain) throws ServletException, IOException {
        if (localeResolver == null) {
            throw new IllegalStateException("No LocaleResolver found: not in a DispatcherServlet request?");
        }
        else {
            final String newLocale = request.getParameter(localeParam);
            if (newLocale != null) {
                final Locale locale = StringUtils.parseLocaleString(newLocale.toLowerCase());
                LocaleContextHolder.setLocale(locale);
                LocaleEditor localeEditor = new LocaleEditor();
                localeEditor.setAsText(newLocale);
                localeResolver.setLocale((HttpServletRequest)request, (HttpServletResponse)response, (Locale) localeEditor.getValue());
            }
            else {
                final Locale locale = localeResolver.resolveLocale((HttpServletRequest)request);
                LocaleContextHolder.setLocale(locale);

            }

            try {
                filterChain.doFilter(request, response);
            } finally {
                LocaleContextHolder.resetLocaleContext();
            }
        }
    }

}
