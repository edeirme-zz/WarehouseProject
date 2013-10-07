package web;

import db.ProvProdWareDb;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import entities.User;

import db.UserDb;

import db.WarehouseDb;
import entities.ProvProdWare;
import entities.Warehouse;

import java.util.List;
/**
 *
 * @author Deirme
 */
public class UpdateWarehouseServlet extends HttpServlet
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
       if (request.getParameter("update") != null) {
        String wName = request.getParameter("warehousename");
        String wAdd = request.getParameter("waddr");
        String wDescr = request.getParameter("wdescr");
        String wSt= request.getParameter("radios");
        Warehouse wh=new Warehouse();
        
             wh.setwareName(wName);
             wh.setwareDescr(wDescr);
             wh.setwareState(wSt);
             wh.setwareAddress(wAdd);
             
      if (WarehouseDb.updateWarehouse(wh))
        {
            request.setAttribute("wli", wh);
            String url = "/listWarehouse";
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
           
           String warehouseName;
        warehouseName = request.getParameter("warehousename");
        Warehouse warehouse1 = new Warehouse();
        warehouse1.setwareName(warehouseName);
        ProvProdWare ppw=new ProvProdWare();
        ppw.setwwName(warehouseName);
        
        if(ProvProdWareDb.searchppwwhexist(ppw).isValid())
        {
            request.setAttribute("eror", "Warehouse is loaded with products. Warehouse cannot be deleted");
            String url = "/listWarehouse";
              ServletContext context = getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher(url);
            dispatcher.forward(request, response);
        // RequestDispatcher vieww;  
       // vieww = request.getRequestDispatcher("/listWarehouse");
       // vieww.forward(request, response);  
        }
        else{
        WarehouseDb.deleteWarehouse(warehouse1);
         String url = "/listWarehouse";
              ServletContext context = getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher(url);
            dispatcher.forward(request, response);
        //RequestDispatcher vieww;  
        //vieww = request.getRequestDispatcher("/listWarehouse");
        //vieww.forward(request, response);  
        }
       
       }
       else if(request.getParameter("back")!=null){
           RequestDispatcher vieww;  
        vieww = request.getRequestDispatcher("/listWarehouse");
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

