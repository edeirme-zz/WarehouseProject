package web;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import entities.User;

import db.UserDb;
import java.util.List;
/**
 *
 * @author Deirme
 */
public class LUserServlet extends HttpServlet
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
        
        String userName;
        userName = request.getParameter("username");
        User user1 = new User(); 
         
        user1.setUserName(userName);
       
        
        List<User> uList = UserDb.searchUsername(user1);
       
        
        request.setAttribute("userli", uList);
        RequestDispatcher vieww;  
        vieww = request.getRequestDispatcher("listUse.jsp");
        vieww.forward(request, response);  
        
      

    }
}

/* AJAX JASON xixi
 * 
 * 
 * public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException
    {      
          ///
                
                 String filename = "c:" + File.separator + "JustExample.txt";
        File f = new File(filename);
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(f);
            String strContent = "Just Example";
            int intContent = 1;
            double doubleContent = Math.random();
            //convinient way to add new line while print content
            pw.println(strContent);
            //using printf to format content. SInce java 1.5
            pw.printf("Hello this is %s. I am %d years old. My lucky number is  %f", strContent, intContent, doubleContent);
            
            pw.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally{
            //no matter what happen. close the output stream always.
            //note that closing a printer will not throw IOException
            if(pw!=null){
                pw.close();
            }
        }
        /////
        String userName;
        userName = request.getParameter("uname");
       // User user = new User(); 
       // User user2 = new User(); 
       // user.setUserName(userName);
       // user2=UserDb.searchUsername(user);
        
        // GSOOON
        Gson gson = new Gson(); 
        JsonObject myObj = new JsonObject();
        User userinfo= UserDb.searchUsername(userName);
        JsonElement userObj=gson.toJsonTree(userinfo);
        if(userinfo.getFirstName()== null){
            myObj.addProperty("success", false);
        }
        else {
            myObj.addProperty("success", true);
        }
       /* if (//user2.isValid())
        //{
        //request.setAttribute("user1i", user2);
        RequestDispatcher view;  
        view = request.getRequestDispatcher("listUse.jsp");
        view.forward(request, response);  
        
        }
        else
        {
            
            String url = "error.html";
            response.sendRedirect(url);
        }
*/
    