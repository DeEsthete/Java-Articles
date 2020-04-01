package kz.itstep.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {
    private static Logger logger = Logger.getLogger(EncodingFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("EncodingFilter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletResponse.setContentType("text/html");
        servletRequest.setCharacterEncoding("UTF-8");
        logger.info("EncodingFilter doFilter");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        logger.info("EncodingFilter destroy");
    }
}
