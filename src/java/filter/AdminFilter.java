
package filter;

import dataaccess.UserDB;
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
import models.User;

/**
 *
 * @author levan
 */
public class AdminFilter implements Filter {

   
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
       // To user getSession, make have an HttpServletRequest object
        HttpServletRequest  httpRequest = (HttpServletRequest)request;
        HttpSession session = httpRequest.getSession();
        String email = (String) session.getAttribute("email");
        // to use sendRedirect, we must have HttpServletResponse
       
        
        if(email != null){
            UserDB userDB = new UserDB();
            try{
                // get user from calling UserDB
                User user = userDB.get(email);
                // when user login is admin
                if(user.getRole().getRoleId() == 1){
                    // allow to pass request and response to the next fillter or servelet 
                     chain.doFilter(request, response);
                }
            }catch (Exception e){

            }
        }
        // regular user, redirect into notes page
        ((HttpServletResponse)response).sendRedirect("notes");
    }

    @Override
    public void destroy() {}
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

}
