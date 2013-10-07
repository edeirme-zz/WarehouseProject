package web;

import db.ProductDb;
import db.ProviderDb;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import entities.User;

import db.UserDb;
import db.WarehouseDb;
import entities.Product;
import entities.Provider;
import entities.Warehouse;
public class RegisterProviderServlet extends HttpServlet
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
         if (request.getParameter("submit") != null) {
        String provName = request.getParameter("provname");
        if(ProviderDb.searchexistsProvname(provName)){
             request.setAttribute("eror", "Name is already in use");

            String url = "/addprovider.jsp";

            ServletContext context = getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }
        String provAddres = request.getParameter("provaddress");
        long provssn;
         String ssn=request.getParameter("ssn");
        if(ssn=="" || ssn==null){
             provssn=0;
        }
        else
            provssn=Long.parseLong(ssn);
        Provider provider=new Provider();
        provider.setprovName(provName);
        provider.setprovSSN(provssn);
        provider.setprovAddress(provAddres);

      if (ProviderDb.insertProvider(provider))
        {
            request.setAttribute("provider", provider);

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
         else if (request.getParameter("cancel")!=null){
             String url = "/listProviders";

            ServletContext context = getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher(url);
            dispatcher.forward(request, response);
         }
    }
    @Override
     public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException{
         doPost(request,response);
     }
}

