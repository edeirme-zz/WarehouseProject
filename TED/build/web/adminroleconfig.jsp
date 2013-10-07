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
<p>Role Configuration</p>
 <c:forEach items="${eror}" var="warehouse"> <div class="errorlogin loco">
<p><c:out value="${warehouse}" />  </p>

</div>
        </c:forEach>
    <div id="createbutton">

       
<% if(u.getRolerole()==333) {%>
        <nav id="createuserbutton">
        <ul id="createuerbuttoncontent">
            <li><a href="<%= response.encodeURL("addrole.jsp")%>">Add Role</a></li>
                </ul>
            </nav>
<%}%>

    </div>

               
<div class= "userlist">
    <p>Role List</p>
   
    <form >
   
<table class="bordered">  
  
        <thead>  
            <tr>  
                <th>Role Name</th>  
                 <th>Role</th>  
                 <th>Provider Role</th>  
                 <th>Product Role</th>  
                 <th>Warehouse Role</th> 
            </tr> 
        </thead> 
        <tbody>  
            <c:forEach items="${rlist}" var="role"> 
                <tr onclick="document.location= '<c:url value="lRole?rolename=${role.roleName}"/>';">
                    
                    <td><c:out value="${role.roleName}" /></td> 
                    <td><c:out value="${role.role}" /></td> 
                    <td><c:out value="${role.providerr}" /></td> 
                    <td><c:out value="${role.productr}" /></td> 
                    <td><c:out value="${role.warehouser}" /></td> 
                  
                   
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