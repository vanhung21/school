//package com.school.SchoolManagerment.configuration;
//
//import jakarta.servlet.*;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//@Component
//@Order(Ordered.HIGHEST_PRECEDENCE)
//public class SimpleCorsFilter implements Filter {
//
//    @Override
//    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) req;
//        HttpServletResponse response = (HttpServletResponse)  res;
//
//        Map<String, String> map = new HashMap<>();
//
//        String originHeader = request.getHeader("origin");
//        response.setHeader("Access-Controll-Allow-Origin", originHeader);
//        response.setHeader("Access-Controll-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
//        response.setHeader("Access-Controll-Max-Age", "3600");
//        response.setHeader("Access-Controll-Allow-Headers", "*");
//
//        if("OPTIONS".equalsIgnoreCase(request.getMethod())){
//            response.setStatus(HttpServletResponse.SC_OK);
//        }else {
//            chain.doFilter(request, response);
//        }
//    }
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
//    }
//
//    @Override
//    public void destroy() {
//        Filter.super.destroy();
//    }
//}
