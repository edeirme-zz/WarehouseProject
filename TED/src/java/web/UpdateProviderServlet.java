package web;

import db.ProductDb;
import db.ProvProdDb;
import db.ProvProdWareDb;
import db.ProviderDb;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

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
public class UpdateProviderServlet extends HttpServlet
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
        long pssn;
       if (request.getParameter("update") != null) {
        String pName = request.getParameter("provname");
        
        String padd = request.getParameter("provaddress");
        String ssn=request.getParameter("ssn");
        if(ssn=="" || ssn==null){
             pssn=0;
        }
        else
            pssn=Long.parseLong(ssn);
        Provider pv=new Provider();
        pv.setprovName(pName);
       
        pv.setprovAddress(padd);
        pv.setprovSSN(pssn);
        
             
             
      if (ProviderDb.updateProvider(pv))
        {
            request.setAttribute("pvli", pv);
            String url = "/listProviders";
            ServletContext context = getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }
        else
        {
            String url = "error.html";
            response.sendRedirect(url);
        }
       }
       else if(request.getParameter("delete")!= null){
           
           String providerName;
        providerName = request.getParameter("provname");
        Provider provider1=new Provider();
        ProviderhasProducts provprod=new ProviderhasProducts();
        provider1.setprovName(providerName);
        provprod.setpprovName(providerName);
        ProvProdWare ppw=new ProvProdWare();
        ppw.setpprovName(providerName);
        if(ProvProdDb.searchProvProdbyname(provprod) )
        {
            ProvProdDb.deleteProvProd(provprod);
            if(ProvProdWareDb.searchProvProdWarebyname(ppw))
            {
                ProvProdWareDb.deleteProvProdWare(ppw);
            }
        }
        ProviderDb.deleteProvider(provider1);
        //RequestDispatcher vieww;  
        //vieww = request.getRequestDispatcher("/listProviders");
        //vieww.forward(request, response);  
        String url = "/listProviders";
              ServletContext context = getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher(url);
            dispatcher.forward(request, response);
       }
       else if(request.getParameter("back")!=null){
           RequestDispatcher vieww;  
        vieww = request.getRequestDispatcher("/listProviders");
        vieww.forward(request, response);  
       }
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

