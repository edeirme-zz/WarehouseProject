package web;

import db.UserDb;
import db.WarehouseDb;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import entities.User;
import entities.Warehouse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class ListWarehousesServlet extends HttpServlet
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
        
        
        List<Warehouse> wList = WarehouseDb.listwarehouses();
        
        
        request.setAttribute("wList",wList);
        RequestDispatcher view = request.getRequestDispatcher("adminwarehouseconfig.jsp");  
        view.forward(request, response);  
       
       
    }
    @Override
     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         doGet(request,response);
     }
}