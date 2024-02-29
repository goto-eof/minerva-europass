package com.andreidodu.minervaeuropass.controller.interceptor;

import com.andreidodu.minervaeuropass.constants.ControllerConst;
import com.andreidodu.minervaeuropass.global.ThreadContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.TreeMap;

@Component
public class ContextLoaderInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) {
        Map<String, String> map = new TreeMap<>((Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE));

        String locale = map.get(ControllerConst.PATH_VARIABLE_LOCALE);
        if (locale != null) {
            ThreadContext.getRequestContext().setLocale(locale);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView model) {
        ThreadContext.clear();
    }

}
