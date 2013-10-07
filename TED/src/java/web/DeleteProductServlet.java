package web;

import db.ActivityDb;
import db.ProductDb;
import db.ProvProdDb;
import db.ProvProdWareDb;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import entities.User;

import db.UserDb;
import db.WarehouseDb;
import entities.Activity;
import entities.Product;
import entities.ProvProdWare;
import entities.ProviderhasProducts;
import entities.Warehouse;
import java.util.List;
/**
 *
 * @author Deirme
 */
public class DeleteProductServlet extends HttpServlet
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
        int pWeight;
       
       
           String productname;
           String warehousename;
        productname = request.getParameter("prodname");
        warehousename = request.getParameter("warehousename");
        Product product1=new Product();
        product1.setprodName(productname);
        ProvProdWare ppw=new ProvProdWare();
        ProvProdWare ppw2=new ProvProdWare();
        ppw.setpprodName(productname);
        ppw.setwwName(warehousename);
        ProviderhasProducts php=new ProviderhasProducts();
        php.setpprodName(productname);
        //
        if(ProvProdDb.searchProvProdbyprod(php)){
            
            if(ProvProdWareDb.searchProvProdWarebyprodname(ppw))
        {
            Activity activity=new Activity();
           
          List<ProvProdWare> pppwlist= ProvProdWareDb.returnPPWDistinct(ppw);
          for(int q=0; q<pppwlist.size(); q++){
              ppw2=pppwlist.get(q);
              activity.setfrom(ppw2.getwwName());
              activity.setproduct(productname);
              activity.setaction("Extraction");
              ActivityDb.insertActivitymove(activity);
          }
          
           ProvProdWareDb.deleteProvProdWarebyprodnwh(ppw);
         
        }
            
           RequestDispatcher vieww;  
        vieww = request.getRequestDispatcher("/listWarehouse");
        vieww.forward(request, response);   
        }
        else{
        
       
        RequestDispatcher vieww;  
        vieww = request.getRequestDispatcher("/listWarehouse");
        vieww.forward(request, response);  }
       }
       
    
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
    {   doPost(request,response); }
}

