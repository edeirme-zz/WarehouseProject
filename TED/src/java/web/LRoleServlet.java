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
import db.RolesDb;
import entities.User;

import db.UserDb;
import db.WarehouseDb;
import entities.Product;
import entities.Provider;
import entities.ProviderhasProducts;
import entities.Roles;
import entities.Warehouse;
import java.util.List;
/**
 *
 * @author Deirme
 */
public class LRoleServlet extends HttpServlet
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
        
        String rolename;
        rolename = request.getParameter("rolename");
        Roles role1=new Roles();
        role1.setroleName(rolename);
        List<Roles> roleList=RolesDb.searchRolename(role1);
        role1=RolesDb.searchRole(role1);
       User user= new User();
       user.setRolerole(role1.getrole());
       List<User> uli=UserDb.searchRole2(user);
       request.setAttribute("uli",uli);
        
        request.setAttribute("roleli", roleList);
        RequestDispatcher vieww;  
        vieww = request.getRequestDispatcher("listRole.jsp");
        vieww.forward(request, response);  
        
      

    }
}


    