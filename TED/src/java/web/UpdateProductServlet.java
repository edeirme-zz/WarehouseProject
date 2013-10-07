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
public class UpdateProductServlet extends HttpServlet
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
       if (request.getParameter("update") != null) {
           
        String pName = request.getParameter("pname");
        String psn = request.getParameter("psn");
        String pDescr = request.getParameter("pdescr");
        String ptype= request.getParameter("tp");
        String weight=request.getParameter("weight");
        if(weight=="" || weight==null){
             pWeight=0;
        }
        else
            pWeight=Integer.parseInt(weight);
        long pssn=Long.parseLong(psn);
        Product pr=new Product();
        pr.setprodName(pName);
        pr.setsn(pssn);
        pr.setprodDescr(pDescr);
        pr.setprodType(ptype);
        pr.setprodWeight(pWeight);
        
        
             
             
      if (ProductDb.updateProduct(pr))
        {
            //PROV PROD FASH//
         ProviderhasProducts provprod= new ProviderhasProducts();
          String[] providersz= request.getParameterValues("provname");
          String[] prices=request.getParameterValues("price");
          provprod.setpprodName(pName);
          if(providersz!=null){
              provprod.setpprovSSN(pssn);
              int pprice;
              for (int i = 0; i < providersz.length; i++) {
                  String provname = providersz[i];
                  String price=prices[i];
                  
                   pprice=Integer.parseInt(price);
                  provprod.setprice(pprice);
                  provprod.setpprovName(provname);
                  if(ProvProdDb.searchProvProd(provprod).isValid()){
                      ProvProdDb.updateProvProd(provprod);
                  }
                  else{
                  ProvProdDb.insertProvProd(provprod);
                  }
        }
          }
              //PROV PROD WARE FASH//
              ProvProdWare ppw=new ProvProdWare();
              String from=request.getParameter("from");
              String to=request.getParameter("to");
              String amount=request.getParameter("amount");
              ppw.setpprodName(pName);
              ppw.setpprovSSN(pssn);
              ppw.setwwName(from);
              if(from!=null  && !"none".equals(from) && amount!=null && amount!=""){ //KANONIKI metafora apo from se to
              List<ProvProdWare> ppwlistzor= ProvProdWareDb.returnPPWprovidernquant(ppw); //Epistrefei providers kai quantity
               ProvProdWare ppw2=new ProvProdWare();
                   ProvProdWare ppw3=new ProvProdWare();
                   ProviderhasProducts pp2=new ProviderhasProducts();
                   ProviderhasProducts pp3=new ProviderhasProducts();
                   pp2.setpprodName(pName);
                   List<ProviderhasProducts> pp2list= ProvProdDb.returnProvProdsPrice(pp2);
                   int amt=Integer.parseInt(amount);
               for (int q=0; q<ppwlistzor.size(); q++) {
                    ppw2=ppwlistzor.get(q);
                    if((ppw2.getquantity()-amt)<=0){ ///An afairei more apo to capacity diagrafei thn eggrafh sto warehouse
                        ProvProdWareDb.deleteProvProdWareList(ppw2);
                    }
                    else{ //allios apla afairei kai kanei update
                        ppw2.setquantity(ppw2.getquantity()-amt);
                        ProvProdWareDb.updateProvProdWare(ppw2);
                    }
               }
                        ppw3=(ppwlistzor.get(0));
                         pp3=(pp2list.get(0));
                         ppw3.setwwName(to);
                         ppw3.setpprovName(pp3.getpprovName());
                        ProvProdWare pp4=new ProvProdWare();
                        pp4=ProvProdWareDb.returnPPWquant(ppw3);
                        pp4.setquantity(amt+pp4.getquantity());
                        
                for (int q=0; q<ppwlistzor.size(); q++) {
                    ppw3=ppwlistzor.get(q);
                   ppw3.setquantity(pp4.getquantity());
                    
                         ppw3.setwwName(to);
                         
                     for (int i = 0; i < pp2list.size(); i++) {//vazei providers
                         pp3=pp2list.get(i);
                    ppw3.setpprovName(pp3.getpprovName());
                  
                    if(ProvProdWareDb.searchProvProdWarebyall(ppw3))
                    {
                        
                   // prosthetei ta quantities
                       ProvProdWareDb.updateProvProdWare(ppw3);
                    }
                   else 
                   { ppw3.setquantity(amt);
                        ProvProdWareDb.insertProvProdWareList(ppw3);
                   } }
                }
                 //Acivityy
               Activity activity= new Activity();
               activity.setfrom(from);
               activity.setto(to);
               activity.setproduct(pName);
               activity.setaction("Move");
               ActivityDb.insertActivitymove(activity);
        }
              else if("none".equals(from) && "none".equals(to)){
                  //Ama den exei valei tipota h den exei valei proorismo
              }
              else if("none".equals(to)){
                  
              }
              else{ //Den EXEI WAREHOUSE KAI KANEI INSERT STO TO 
                  
                  ProvProdWare ppw3=new ProvProdWare();
                  ProviderhasProducts pp3=new ProviderhasProducts();
                  ProviderhasProducts pp2=new ProviderhasProducts();
                  pp2.setpprodName(pName);
                  List<ProviderhasProducts> pp2list= ProvProdDb.returnProvProdsPrice(pp2);
                  if(amount==""||amount==null)
                      amount="0";
                    int amt=Integer.parseInt(amount);
                    //
                    
                         pp3=(pp2list.get(0));
                         ppw3.setwwName(to);
                         ppw3.setpprovName(pp3.getpprovName());
                        ProvProdWare pp4=new ProvProdWare();
                        pp4=ProvProdWareDb.returnPPWquant(ppw3);
                        pp4.setquantity(amt+pp4.getquantity());
                        //
                     for (int i = 0; i < pp2list.size(); i++) {
                         pp3=pp2list.get(i);
                    ppw3.setpprovName(pp3.getpprovName());
                    ppw3.setwwName(to);
                    ppw3.setpprovSSN(pp3.getpprovSSN());
                    ppw3.setpprodName(pName);
                    ppw3.setquantity(pp4.getquantity());
                     if(ProvProdWareDb.searchProvProdWarebyall(ppw3))
                    {
                    
                    }
                     else{
                         ppw3.setquantity(amt);
                         ProvProdWareDb.insertProvProdWareList(ppw3);
                     }
                }
                 //Acivityy
               Activity activity= new Activity();
               activity.setto(to);
               activity.setproduct(pName);
               activity.setaction("Insertion");
               ActivityDb.insertActivity(activity);
              }
               
            request.setAttribute("pli", pr);
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
       else if(request.getParameter("delete")!=null){
           String productname;
        productname = request.getParameter("pname");
        Product product1=new Product();
        product1.setprodName(productname);
        ProvProdWare ppw=new ProvProdWare();
        ProvProdWare ppw2=new ProvProdWare();
        ppw.setpprodName(productname);
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
            ProvProdWareDb.deleteProvProdWarebyprod(ppw);
           
         
        }
            ProvProdDb.deleteProvProdbyprod(php);
            ProductDb.deleteProdbyprodnm(product1);
           RequestDispatcher vieww;  
        vieww = request.getRequestDispatcher("/listProducts");
        vieww.forward(request, response);   
        }
        else{
        
        ProductDb.deleteProdbyprodnm(product1);
        RequestDispatcher vieww;  
        vieww = request.getRequestDispatcher("/listProducts");
        vieww.forward(request, response);  }
       }
       else if(request.getParameter("back")!=null){
           RequestDispatcher vieww;  
        vieww = request.getRequestDispatcher("/listProducts");
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

