package web;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author
 */
public class LogoffUserServlet extends HttpServlet {

   @Override
   protected void doGet(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {

	response.setHeader("Cache-Control", "no-cache, no-store");
	response.setHeader("Pragma", "no-cache");

	request.getSession().invalidate();
	response.sendRedirect(request.getContextPath() + "/index.jsp");
}
    
}
