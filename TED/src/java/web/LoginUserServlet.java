package web;

import db.UserDb;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import entities.User;



public class LoginUserServlet extends HttpServlet
{ 
     /**
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    @Override
     public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException
    {  
        String x=request.getParameter("submit");
        if (request.getParameter("submit") != null) {   
        String userName = request.getParameter("username");        
        String password = request.getParameter("password");        
        User user = new User();
        user.setUserName(userName);       
        user.setpassword(password);
        user=UserDb.searchCredentials(user);
        HttpSession session=null;
      if (user.isValid())
        {
            session = request.getSession(true);
            session.setAttribute("userReg",user);
            String url = "/index.jsp";
           //response.sendRedirect(url);
            response.setHeader("Cache-Control", "no-cache, no-store");
	response.setHeader("Pragma", "no-cache");
            ServletContext context = getServletContext(); 
            RequestDispatcher dispatcher = context.getRequestDispatcher(url); 
            dispatcher.forward(request, response);  
            
        }
        else
        {
             request.setAttribute("eror", "Username and/ or Password are invalid");
            String url = "/login.jsp";
            //response.sendRedirect(url);
            ServletContext context = getServletContext(); 
            RequestDispatcher dispatcher = context.getRequestDispatcher(url);
            dispatcher.forward(request, response);  
        }
    }
    else  if (request.getParameter("cancel") != null) {   
            String url = "/index.jsp"; 
          
            response.setHeader("Cache-Control", "no-cache, no-store");
	response.setHeader("Pragma", "no-cache");
            ServletContext context = getServletContext(); 
            RequestDispatcher dispatcher = context.getRequestDispatcher(url); 
            dispatcher.forward(request, response);  
    }
    else{
         String url = "/errorlogin.jsp";
            //response.sendRedirect(url);
            ServletContext context = getServletContext(); 
            RequestDispatcher dispatcher = context.getRequestDispatcher(url);
            dispatcher.forward(request, response);  
    }

    }
}
