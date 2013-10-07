package web;

import db.ProductDb;
import db.ProviderDb;
import db.UserDb;
import db.WarehouseDb;
import entities.Product;
import entities.Provider;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import entities.User;
import entities.Warehouse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class ListProvidersServlet extends HttpServlet
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
        
        
        List<Provider> provList = ProviderDb.listProvider();
        
        
        request.setAttribute("provList",provList);
        RequestDispatcher view = request.getRequestDispatcher("adminproviderconfig.jsp");  
        view.forward(request, response);  
       
       
    }
    @Override
     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         doGet(request,response);
     }
}