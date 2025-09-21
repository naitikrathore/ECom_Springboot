package com.codeSnippet.CodeSnippet.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * {@code LoggingInterceptor} is a Spring {@link HandlerInterceptor} implementation
 * used for logging request lifecycle events in a Spring MVC application.
 * <p>
 * Interceptors act as cross-cutting handlers for incoming requests which sits between Serverlet and Controller.
 * They are useful for tasks like:
 * <ul>
 *   <li>Logging request and response details</li>
 *   <li>Performing security checks before a request reaches the controller</li>
 *   <li>Modifying or validating request data before processing</li>
 *   <li>Cleaning up resources after the request is complete</li>
 * </ul>
 *
 * <p>This interceptor demonstrates the three main lifecycle methods:
 * <ol>
 *   <li>{@link #preHandle(HttpServletRequest, HttpServletResponse, Object)} —
 *       Executed before the request reaches the controller.</li>
 *   <li>{@link #postHandle(HttpServletRequest, HttpServletResponse, Object, ModelAndView)} —
 *       Executed after the controller method but before the view is rendered.</li>
 *   <li>{@link #afterCompletion(HttpServletRequest, HttpServletResponse, Object, Exception)} —
 *       Executed after the complete request has finished.</li>
 * </ol>
 */

@Component
public class LoggingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("PreHandle: Request URI is " + request.getRequestURI());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("PostHandle: Executed Handler URI is " + request.getRequestURI());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("AfterCompletion: Request Completed is " + request.getRequestURI());
    }
}
