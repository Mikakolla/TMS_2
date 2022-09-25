package filter;

import exception.AccessException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static java.lang.System.out;

@WebFilter(value = "/car")
public class AccessFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String role = req.getHeader("role");
        String method = req.getMethod();

        if ((role != null && role.equals("admin")) || method.equals("GET")){
            out.println("this is admin");
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            throw new AccessException();
        }
    }

    @Override
    public void destroy() {

    }
}
