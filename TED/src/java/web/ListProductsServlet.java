package web;

import db.ProductDb;
import db.UserDb;
import db.WarehouseDb;
import entities.Product;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import entities.User;
import entities.Warehouse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class ListProductsServlet extends HttpServlet
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
        
        
        List<Product> pList = ProductDb.listProducts();
        
        
        request.setAttribute("pList",pList);
        RequestDispatcher view = request.getRequestDispatcher("adminproductconfig.jsp");  
        view.forward(request, response);  
       
       
    }
    @Override
     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         doGet(request,response);
     }
}