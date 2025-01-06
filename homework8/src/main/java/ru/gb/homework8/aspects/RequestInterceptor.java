package ru.gb.homework8.aspects;

import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class RequestInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, @Nonnull Object handler) throws Exception {
        System.out.println("PreHandler");
        System.out.println(request.getRequestURI());
        System.out.println(request.getHeaderNames());
        System.out.println(response.getHeaderNames());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, @Nonnull Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("PostHandler");
        System.out.println(request.getRequestURI());
        System.out.println(request.getHeaderNames());
        System.out.println(response.getHeaderNames());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, @Nonnull Object handler, Exception ex) throws Exception {
        System.out.println("AfterHandler");
        System.out.println(request.getRequestURI());
        System.out.println(request.getHeaderNames());
        System.out.println(response.getHeaderNames());
    }
}
