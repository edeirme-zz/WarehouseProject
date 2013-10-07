package web;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import db.ProductDb;
import db.ProviderDb;
import entities.User;

import db.UserDb;
import db.WarehouseDb;
import entities.Product;
import entities.Provider;
import entities.Warehouse;
import java.util.List;
/**
 *
 * @author Deirme
 */
public class addProductServlet extends HttpServlet
{    
    
    /**
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException
    {      
        
       
         
       
        List<Warehouse> wList = WarehouseDb.listopenwarehouses();
        List<Provider> provList=ProviderDb.listProvider();
        request.setAttribute("provli", provList);
        request.setAttribute("wli", wList);
       
        
      
        RequestDispatcher vieww;  
        vieww = request.getRequestDispatcher("addproduct.jsp");
        vieww.forward(request, response);  
        
      

    }
}


    