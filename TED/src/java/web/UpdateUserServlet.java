package web;

import db.RolesDb;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import entities.User;

import db.UserDb;
import entities.Roles;
/**
 *
 * @author Deirme
 */
public class UpdateUserServlet extends HttpServlet
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
        String userName = request.getParameter("username");
        User user = new User();
        User user2 = new User();
         HttpSession session=null;
        session = request.getSession(false);
        user2 = (User)session.getAttribute("userReg");
                if(user!=null){
                       user.setUserName(userName);
                       user=UserDb.searchRole(user);
               if (user2.getRolerole() ==333 )
                {
                    
                    int rolenum=0;
        String providerright=request.getParameter("radios");
        String productright=request.getParameter("radios1");
        String warehouseright=request.getParameter("radios2");
       if(user.getRolerole()!=333){
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
        user.setRolerole(rolenum);
                }
       
           }}
        
        
       Roles rolezs= new Roles();
        rolezs.setrole(user.getRolerole());
        if(!RolesDb.searchexistsRoleonly(rolezs)){
            request.setAttribute("eror", "Role does not exist");

            String url = "/listUsers";

            ServletContext context = getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }
        
       

      if (UserDb.updateUser(user))
        {
            request.setAttribute("userReg", user);

            String url = "/listUsers";

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
       else if (request.getParameter("cancel") != null) {
            String url = "/listUsers";

            ServletContext context = getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher(url);
            dispatcher.forward(request, response);
       }
       }
    
    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException
    {   doPost(request,response); }
}

