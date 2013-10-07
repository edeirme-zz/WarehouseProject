package web;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import entities.User;

import db.UserDb;
import db.WarehouseDb;
import entities.Warehouse;
public class RegisterWarehouseServlet extends HttpServlet
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
        String wName = request.getParameter("wname");
        String wAddress = request.getParameter("waddress");
        String wDescr = request.getParameter("wdescr");
        String wState = request.getParameter("radios");
        Warehouse warehouse = new Warehouse();
        if(WarehouseDb.searchexistsWname(wName)){
             request.setAttribute("eror", "Name is already in use");

            String url = "/addwarehouse.jsp";

            ServletContext context = getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }
        //User user = new User();
        // HttpSession session=null;
        //session = request.getSession(false);
        //Warehouse wh=(Warehouse)session.getAttribute(wName);
        //User u = (User)session.getAttribute("userReg");
          //      if(u!=null)
        
        warehouse.setwareName(wName);
        warehouse.setwareDescr(wDescr);
        warehouse.setwareAddress(wAddress);
        warehouse.setwareState(wState);

      if (WarehouseDb.insertWarehouse(warehouse))
        {
            request.setAttribute("wh", warehouse);

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
    @Override
     public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException{
         doPost(request,response);
     }
}

