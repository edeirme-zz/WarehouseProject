<%@page import="entities.User"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html lang="en">
<head>
<link rel="stylesheet" type="text/css" href="css/index.css">
<meta charset="utf-8">
<title>Mock Corp</title> 

</head>
<body>
<div id="pagewidth">
    <div class="center">
        <div class="center_content">
        <div class="columns">
            <div class="half1"><a href="<%= response.encodeURL("index.jsp")%>"><img id=aucg src="badass-logo.jpg"></a></div>
        </div>
      
    <nav id="menu">
        <ul id="menu1">
             <li><a href="<%= response.encodeURL("index.jsp")%>">Home</a></li>
             <li><a href="<%= response.encodeURL("#")%>">About Us</a>
             <ul>
                    <li><a href="<%= response.encodeURL("The_team.jsp")%>">The Team</a></li>
                    <li><a href="<%= response.encodeURL("history.jsp")%>">History</a></li>
                     <li><a href="<%= response.encodeURL("vision.jsp")%>">Vision</a></li>
            </ul>
            </li>
            <li><a href="<%= response.encodeURL("#")%>">Contact</a>
             <ul>
                 <li><a href="<%= response.encodeURL("contactpage1.jsp")%>">Online</a></li>
                 <li><a href="<%= response.encodeURL("contactpage2.jsp")%>">Right Here</a></li>
                 <li><a href="<%= response.encodeURL("contactpage3.jsp")%>">Somewhere Else</a></li>
            </ul>
            </li>   
            <%  session=request.getSession(false);
                User u = (User)session.getAttribute("userReg");
                if(u!=null && u.getRolerole()>0){
              %>
           <li><a href="<%= response.encodeURL("#")%>">Configure</a>
                <ul>
                    <% if (u.getRolerole()==333) {%>
                    <li><a href="<%= response.encodeURL("listRoles")%>">Roles</a></li>
                    <li><a href="<%= response.encodeURL("listUsers")%>">Users</a></li>
                   
                    <%}  if(u.getwarehouser()=="Read" || u.getwarehouser()=="Write") {%>
                 <li><a href="<%= response.encodeURL("listWarehouse")%>"> Warehouse</a></li>
                 <%}  if(u.getproductr()=="Read" || u.getproductr()=="Write") {%>
                 <li><a href="<%= response.encodeURL("listProducts")%>"> Product</a></li>
                 <%}  if (u.getproviderr()== "Read" || u.getproviderr()== "Write"){ %>
                 <li><a href="<%= response.encodeURL("listProviders")%>"> Provider</a></li>
                </ul>
            </li>
            <%}}%>
        </ul>
    </nav>
    <nav id="login1">
        <ul id="log">
            <% if(u!=null){ if(u.getRolerole()<0) {%>
            <li><a href="<%= response.encodeURL("#")%>">Log In</a>
                <ul>
            <li><a href="<%= response.encodeURL("login.jsp")%>">Log In</a></li>
            <li><a href="<%= response.encodeURL("register.jsp")%>">Register</a></li>
            </ul>
            </li>
            <%} else {%>
            <li><a href="<%=response.encodeURL("logoffUser")%>">Log Off</a>
                <%}} else{%>
                <li><a href="<%= response.encodeURL("#")%>">Log In</a>
                <ul>
            <li><a href="<%= response.encodeURL("login.jsp")%>">Log In</a></li>
            <li><a href="<%= response.encodeURL("register.jsp")%>">Register</a></li>
            </ul>
            </li>
            <%}%>
        </ul>
    </nav>
    </div>
</div>
</div>
<div class="title">
<p>Product Configuration</p>
<div>
    <div id="createbutton">

       

         <nav id="createuserbutton">
        <ul id="createuerbuttoncontent">
            <li><a href="<%= response.encodeURL("addProduct")%>">Add Product</a></li>
                </ul>
            </nav>
                <form id="search" action="<%= response.encodeURL("searchproduct")%>" method="get">
        <input type="text" name="searchval"  class="text" value="" placeholder="Search Product">
        <input type="submit" value="Search" class="sbutton"/>
</form>

    </div>

                <div class= "userlistnew">
                    <p>Product List</p>
                    <form id="AjaxReq">              
<table class="bordered"  action=<%= response.encodeURL("listUsers")%>>  
  
        <thead>  
  
            <tr>  
                <th>Product Name</th>  
                <th>Serial Number</th> 
                <th>Description</th>
                <th>Type</th>  
                <th>Weight</th>  
                 
                 
                
            </tr>  
  
        </thead>  
  
        <tbody>  
        
            <c:forEach items="${pliname}" var="product"> 
                 
                <tr onclick="document.location = '<c:url value="lProd?pname=${product.prodName}"/>';">  
                    <td><c:out value="${product.prodName}" /></td>  
                    <td><c:out value="${product.sn}" /></td>  
                    <td><c:out value="${product.prodDescr}" /></td>  
                    <td><c:out value="${product.prodType}" /></td>  
                    <td><c:out value="${product.prodWeight}" /></td>
                    
                    
  
                   
  
                </tr>  
  
            </c:forEach>  
        </tbody>  
    </table> 
                    </form>            
</div>
  <div class= "userlistnew">
                    <p>Product List</p>
                    <form id="AjaxReq">    
<table class="bordered"  action=<%= response.encodeURL("listUsers")%>>  
  
        <thead>  
  
            <tr>  
                <th>Provider Name</th>  
                <th>Product Name</th> 
                
                 
                 
                
            </tr>  
  
        </thead>  
  
        <tbody>  
        
            <c:forEach items="${pliprovidor}" var="product"> 
                 
                   <tr onclick="document.location = '<c:url value="lProd?pname=${product.pprodName}"/>';">
                    <td><c:out value="${product.pprovName}" /></td>  
                    <td><c:out value="${product.pprodName}" /></td>  
                </tr>  
  
            </c:forEach>  
        </tbody>  
    </table> 
                    </form>            
</div>


<div class="wrapper3">
    <div class="fotterz">
            <p>Tel. Num. (305)+20145729 839.</p>
           
        </div>
        <div class="footer">
            <p>Copyright (c) 2013</p>
        </div>
        </div>
</body>
</html>