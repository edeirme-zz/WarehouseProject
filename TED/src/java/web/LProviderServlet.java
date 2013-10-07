package web;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import db.ProductDb;
import db.ProvProdDb;
import db.ProviderDb;
import entities.User;

import db.UserDb;
import db.WarehouseDb;
import entities.Product;
import entities.Provider;
import entities.ProviderhasProducts;
import entities.Warehouse;
import java.util.List;
/**
 *
 * @author Deirme
 */
public class LProviderServlet extends HttpServlet
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
        
        String providerName;
        providerName = request.getParameter("provname");
        Provider provider1=new Provider();
        provider1.setprovName(providerName);
        List<Provider> provList=ProviderDb.searchProvname(provider1);
        ProviderhasProducts provprod=new ProviderhasProducts();
        provprod.setpprovName(providerName);
        if(ProvProdDb.searchProvProdbyname(provprod))
        {
            List<ProviderhasProducts> provprodList=ProvProdDb.returnProvProds(provprod);
            request.setAttribute("provprodli", provprodList);
        }
        
        
        request.setAttribute("provli", provList);
        RequestDispatcher vieww;  
        vieww = request.getRequestDispatcher("listProv.jsp");
        vieww.forward(request, response);  
        
      

    }
}


    