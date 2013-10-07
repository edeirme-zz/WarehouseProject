package web;

import db.RolesDb;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import entities.User;

import db.UserDb;
import entities.Roles;
public class RegisterUserServlet extends HttpServlet
{    
    private Roles rolenum;
    
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
    {   HttpSession session=null;
        session = request.getSession(false);
        User u = (User)session.getAttribute("userReg");
        if (request.getParameter("submit") != null) {   
        String userName = request.getParameter("username");
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String emailAddress = request.getParameter("email");
        String password = request.getParameter("password");
        
         if(UserDb.searchexistsUname(userName)){
             request.setAttribute("eror", "Name is already in use");

            String url = "/register.jsp";

            ServletContext context = getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }
         
        User user = new User();
         
                if(u!=null){
               if (u.getRolerole() >0 )
                {
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
        user.setRolerole(rolenum);
                }}
                else{
                    user.setRolerole(0);
                }
        
        user.setFirstName(firstName);
        user.setUserName(userName);
        user.setLastName(lastName);
        user.setEmailAddress(emailAddress);
        user.setpassword(password);
        Roles rolezs= new Roles();
        rolezs.setrole(user.getRolerole());
        if(!RolesDb.searchexistsRoleonly(rolezs)){
            if(rolezs.getrole()==0){
                rolezs.setroleName("New User");
                RolesDb.insertRole(rolezs);
                
            }
            else{
            request.setAttribute("eror", "Role does not exist");

            String url = "/register.jsp";

            ServletContext context = getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher(url);
            dispatcher.forward(request, response);
            }}
        
      if (UserDb.insertUser(user))
        {
            request.setAttribute("userReg", user);
            if(u!=null){
             if(u.getRolerole()>0){
            String url = "/listUsers";

            ServletContext context = getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher(url);
            dispatcher.forward(request, response);
                        }
                 else
             {
            String url = "/register2.jsp";

            ServletContext context = getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }
        }else
             {
            String url = "/register2.jsp";

            ServletContext context = getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher(url);
            dispatcher.forward(request, response);
             }
        

    }
      else
        {
            
            String url = "error.html";
            response.sendRedirect(url);
        }
        }
     else if(request.getParameter("cancel") != null){
        if(u!=null){
            String url = "/listUsers";

            ServletContext context = getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }
        else
        {
            String url = "/index.jsp";

            ServletContext context = getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }
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

 