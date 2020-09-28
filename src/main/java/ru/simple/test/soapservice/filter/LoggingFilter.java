package ru.simple.test.soapservice.filter;

import org.apache.cxf.common.util.StringUtils;
import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

public class LoggingFilter implements Filter {

    public static final String ID_LOGGER_HEADER_REQUEST = "request-id";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String requestId = httpRequest.getHeader(ID_LOGGER_HEADER_REQUEST);
        MDC.put(ID_LOGGER_HEADER_REQUEST, StringUtils.isEmpty(requestId) ? UUID.randomUUID().toString() : requestId);
        filterChain.doFilter(request, response);

        MDC.clear();
    }

    @Override
    public void destroy() {

    }
}