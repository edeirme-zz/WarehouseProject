package web;

import db.ProductDb;
import db.ProvProdDb;
import db.ProvProdWareDb;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import entities.User;

import db.UserDb;
import db.WarehouseDb;
import entities.Product;
import entities.ProvProdWare;
import entities.ProviderhasProducts;
import entities.Warehouse;
import java.util.List;
/**
 *
 * @author Deirme
 */
public class SearchProductServlet extends HttpServlet
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
        String searchvalue;
        searchvalue = request.getParameter("searchval");
        Product product1= new Product();
        ProviderhasProducts product4= new ProviderhasProducts();
        product1.setprodName(searchvalue);
        product4.setpprovName(searchvalue);
        List<Product> pList1=ProductDb.search(product1);
        List<ProviderhasProducts> pList2=ProvProdDb.searchProvProdNameList(product4);
        request.setAttribute("pliname", pList1);
        
        request.setAttribute("pliprovidor", pList2);
        RequestDispatcher vieww;  
        vieww = request.getRequestDispatcher("searchresults.jsp");
        vieww.forward(request, response);  
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

