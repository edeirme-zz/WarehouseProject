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

public class RegisterProductServlet extends HttpServlet
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
           
            
             
        String pName = request.getParameter("pname");
        String psn = request.getParameter("psn");
        String pDescr = request.getParameter("pdescr");
        String pType = request.getParameter("ptype");
        int pWeight;
        boolean nmexists=ProductDb.searchexistsProduct(pName);
        boolean snexists=ProductDb.searchexistsSN(psn);
         if(nmexists || snexists){
             
         if(nmexists && snexists){
    request.setAttribute("eror", "Name and Serial number are already in use");
            }
         else if(snexists)
             request.setAttribute("eror", "Serial number is already in use");
         else
             request.setAttribute("eror", "Name is already in use");
            String url = "/listProducts";

            ServletContext context = getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }
         
           
         String weight=request.getParameter("pweight");
        if(weight=="" || weight==null){
             pWeight=0;
        }
        else
            pWeight=Integer.parseInt(weight);
        long psnn=Long.parseLong(psn);
        Product product=new Product();
        product.setprodName(pName);
        product.setsn(psnn);
        product.setprodDescr(pDescr);
        product.setprodType(pType);
        product.setprodWeight(pWeight);

      if (ProductDb.insertProduct(product))
        {
            //PROV PROD FASH//
         ProviderhasProducts provprod= new ProviderhasProducts();
          String[] providersz= request.getParameterValues("provname");
          String[] prices=request.getParameterValues("price");
          
          provprod.setpprodName(pName);
              provprod.setpprovSSN(psnn);
              for (int i = 0; i < providersz.length; i++) {
                  String provname = providersz[i];
                  String price=prices[i];
                  if(price==null || price=="")
                      price="0";
                  int pprice=Integer.parseInt(price);
                  provprod.setprice(pprice);
                  provprod.setpprovName(provname);
                  ProvProdDb.insertProvProd(provprod);
                  
              }
             
              ProvProdWare ppw=new ProvProdWare();
              
              String[] warehousez=request.getParameterValues("wname");
              String[] quantity=request.getParameterValues("quantity");
              
              ppw.setpprodName(pName);
              ppw.setpprovSSN(psnn);
              
              for(int q=0;q<warehousez.length;q++){
                  String whname=warehousez[q];
                  String qnt=quantity[q];
                  if(qnt==null||qnt=="")
                      qnt="0";
                   int pquant=Integer.parseInt(qnt);
                  ppw.setwwName(whname);
              ppw.setquantity(pquant);
                 
               for (int i = 0; i < providersz.length; i++) {
                   String provname = providersz[i];
              ppw.setpprovName(provname);
              ProvProdWareDb.insertProvProdWare(ppw);
               }
              //Acivityy
               Activity activity= new Activity();
               activity.setto(whname);
               activity.setproduct(pName);
               activity.setaction("Insertion");
               ActivityDb.insertActivity(activity);
            request.setAttribute("product", product);

              }
            String url = "/listProducts";

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
             String url = "/listProducts";

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

