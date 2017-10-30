package com.crossent.monitoring.portal.common.interceptor;

import com.crossent.monitoring.portal.common.context.ServiceContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class ServiceInterceptor extends HandlerInterceptorAdapter {
    private static Logger logger = LoggerFactory.getLogger(ServiceInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.debug("call preHandle");

        logger.info("init ServiceContextHolder.");
        ServiceContextHolder.init();
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.debug("call postHandle");


        logger.info("clear ServiceContextHolder!!");
        ServiceContextHolder.clear();
        super.postHandle(request, response, handler, modelAndView);
    }
}
