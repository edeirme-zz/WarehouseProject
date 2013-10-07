package web;

import db.ProductDb;
import db.RolesDb;
import db.UserDb;
import db.WarehouseDb;
import entities.Product;
import entities.Roles;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import entities.User;
import entities.Warehouse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class ListRolesServlet extends HttpServlet
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
        
        List<Roles> rList= RolesDb.listRoles();
        
        
        request.setAttribute("rlist",rList);
        RequestDispatcher view = request.getRequestDispatcher("adminroleconfig.jsp");  
        view.forward(request, response);  
       
       
    }
    @Override
     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         doGet(request,response);
     }
}