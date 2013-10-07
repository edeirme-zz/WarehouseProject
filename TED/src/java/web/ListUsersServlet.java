package web;

import db.UserDb;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import entities.User;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class ListUsersServlet extends HttpServlet
{ 
     /**
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    @Override
     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        List<User> uList = UserDb.doSelectApproved();
        List<User> uListnew = UserDb.doSelectNewUser();
        
        request.setAttribute("userList", uList);
        request.setAttribute("userListnew",uListnew);
        RequestDispatcher view = request.getRequestDispatcher("adminuserconfig.jsp");  
        view.forward(request, response);  
       
       
    }
}