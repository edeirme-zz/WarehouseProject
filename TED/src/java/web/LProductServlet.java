package web;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import db.ProductDb;
import db.ProvProdDb;
import db.ProvProdWareDb;
import db.ProviderDb;
import entities.User;

import db.UserDb;
import db.WarehouseDb;
import entities.Product;
import entities.ProvProdWare;
import entities.Provider;
import entities.ProviderhasProducts;
import entities.Warehouse;
import java.util.List;
/**
 *
 * @author Deirme
 */
public class LProductServlet extends HttpServlet
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
        
        String productName;
        productName = request.getParameter("pname");
        Product product1= new Product();
        product1.setprodName(productName);
        List<Product> pList=ProductDb.searchPname(product1);
        Warehouse warehouse1 = new Warehouse(); 
         ////
        ProvProdWare ppw=new ProvProdWare();
        ProvProdWare ppwnot=new ProvProdWare();
        ppw.setpprodName(productName);
        ppwnot.setpprodName(productName);
        if(ProvProdWareDb.searchProvProdWarebyprodname(ppw))
        {
            //ProvProdWareDb.searchProvProdWarebyprodnameNot(ppwnot);
            
            List<ProvProdWare> ppwList2=ProvProdWareDb.returnPPWDistinct(ppw);
          
           // List<ProvProdWare> ppwList=ProvProdWareDb.returnPPW(ppw);
            //request.setAttribute("ppwList", ppwList);
            request.setAttribute("ppwList2", ppwList2);
           
            
           
        }
        ProviderhasProducts php=new ProviderhasProducts();
            php.setpprodName(productName);
            List<ProviderhasProducts> provprodList=ProvProdDb.returnProvProdsPrice(php);
            request.setAttribute("phplist", provprodList);
        List<Warehouse> wList = WarehouseDb.listopenwarehouses();
        List<Provider> provList=ProviderDb.listProvider();
        request.setAttribute("provli", provList);
        request.setAttribute("wli", wList);
       
        
        request.setAttribute("pli", pList);
        RequestDispatcher vieww;  
        vieww = request.getRequestDispatcher("listProd.jsp");
        vieww.forward(request, response);  
        
      

    }
}


    