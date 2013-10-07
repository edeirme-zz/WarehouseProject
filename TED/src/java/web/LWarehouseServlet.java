package web;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import db.ActivityDb;
import db.ProvProdWareDb;
import entities.User;

import db.UserDb;
import db.WarehouseDb;
import entities.Activity;
import entities.ProvProdWare;
import entities.Warehouse;
import java.util.List;
/**
 *
 * @author Deirme
 */
public class LWarehouseServlet extends HttpServlet
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
        String count=null;
        String warehouseName;
        warehouseName = request.getParameter("wname");
        Warehouse warehouse1 = new Warehouse(); 
         
        warehouse1.setwareName(warehouseName);
        List<Warehouse> wList = WarehouseDb.searchWname(warehouse1);
       
       List<Activity> activityList= ActivityDb.listactivity(warehouseName);
       request.setAttribute("actlist", activityList);
        ProvProdWare ppw=new ProvProdWare();
        ppw.setwwName(warehouseName);
        count=ProvProdWareDb.searchprodcount(ppw);
        List<ProvProdWare> ppwList= ProvProdWareDb.returnPPWproducts(ppw);
        request.setAttribute("wli", wList);
        request.setAttribute("count",count);
        request.setAttribute("ppwList", ppwList);
        RequestDispatcher vieww;  
        vieww = request.getRequestDispatcher("listWar.jsp");
        vieww.forward(request, response);  
        
      

    }
}


    