
package filter;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class AuthorizationFilter implements Filter {

    

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // To use getSession, make have an HttpServletRequest object
        HttpServletRequest  httpRequest = (HttpServletRequest)request;
       
        HttpSession session = httpRequest.getSession();
        
        String email = (String) session.getAttribute("email");
        
        if (email == null){
            // to use sendRedirect, we must have HttpServletResponse
            HttpServletResponse  httpRespone = (HttpServletResponse)response;
            httpRespone.sendRedirect("login");
            return;
        }
        // any code before the chain.doFilter() method will execute before the link in the chain
        
        // pass the request or response to next filter or servlet in the chain
        chain.doFilter(request, response);
        
        // any code after the chain.doFilter() method will execute during the response
    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {   
    }

    @Override
    public void destroy() {
    }
    
    
}
