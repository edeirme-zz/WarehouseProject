package web;

import db.RolesDb;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import entities.User;

import db.UserDb;
import entities.Roles;
public class RegisterRoleServlet extends HttpServlet
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
        String rolename = request.getParameter("rolename");
        Roles role= new Roles();
        role.setroleName(rolename);
         int rolenum=0;
         String providerright=request.getParameter("radios");
        String productright=request.getParameter("radios1");
        String warehouseright=request.getParameter("radios2");
       
        if(         "none".equals(providerright)){
            rolenum=rolenum+0;
        }
        else if(    "read".equals(providerright)){
            rolenum=rolenum+100;
        }
        else if(    "write".equals(providerright))
        {rolenum=rolenum+200;}
        if(         "none".equals(productright)){
            rolenum=rolenum+0;
        }
        else if(    "read".equals(productright)){
            rolenum=rolenum+10;
        }
        else if(    "write".equals(productright))
        {rolenum=rolenum+20;}
        if(         "none".equals(warehouseright)){
            rolenum=rolenum+0;
        }
        else if(    "read".equals(warehouseright)){
            rolenum=rolenum+1;
        }
        else if (   "write".equals(warehouseright))
        { rolenum=rolenum+2;}
        role.setrole(rolenum);
                
        if(RolesDb.searchexistsRlnname(role)){
             request.setAttribute("eror", "Role name and/or Role is already in use");

            String url = "/addrole.jsp";

            ServletContext context = getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }
         
        

      if (RolesDb.insertRole(role))
        {
            
            
            String url = "/listRoles";
            ServletContext context = getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }else
             {
            String url = "/register2.jsp";

            ServletContext context = getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher(url);
            dispatcher.forward(request, response);
             }
        

        }
     
     else if(request.getParameter("cancel") != null){
        
            String url = "/listRoles";

            ServletContext context = getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher(url);
            dispatcher.forward(request, response);
        
       
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
            throws IOException, ServletException{
doPost(request,response);
}}

 