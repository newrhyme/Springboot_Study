package com.study.intercpetor;

import lombok.extern.slf4j.Slf4j;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
public class LoggerIntercpetor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.debug("=============================================");
        log.debug("=================  시     작  =================");
        log.debug("Request URI ===>" + request.getRequestURI());
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.debug("=================  종     료  =================");
        log.debug("=============================================");
        log.debug("Request URI ===>" + request.getRequestURI());
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
}
