package de.rgse.brewlogs.env;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "*")
public class CORSFilter implements Filter {

    private static final String HEADERS = "Content-Type, Authorization, Accept, Accept-Language, content-type, authorization, accept, accept-language";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //unused
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        response.setHeader("Access-Control-Allow-Origin", "*"); // If you want to be more restrictive it could be localhost:4200
        response.setHeader("Access-Control-Allow-Methods", "GET, PUT, POST, OPTIONS, DELETE"); // You can add HEAD, DELETE, TRACE, PATCH
        response.setHeader("Access-Control-Allow-Headers", HEADERS); // You can add many more
        response.setHeader("Allow-access-expose-headers", HEADERS); // You can add many more

        if(request.getMethod().equalsIgnoreCase("OPTIONS")) {
            response.setStatus(200);
        } else {
            chain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {
        //unused
    }
}
