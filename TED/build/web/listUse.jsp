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
<p>User Configuration</p>
<div>
    

<div  id="login3" class="login3">
  <form action="<%= response.encodeURL("updateUser")%>" method="get">
      
  
            <c:forEach items="${userli}" var="user">        
            <!LOGIN BUTTON>


        <h2><span class="fontawesome-lock"></span>Modify User</h2>

        

            <fieldset>

                <p><label for="username">Username</label></p>
                <p><input type="text" id="username" class="text" name="username" value="${user.userName}" onBlur="if(this.value=='')this.value='Username'" onFocus="if(this.value=='Username')this.value=''"disabled></p> 
                <input type="hidden" name="username" value="${user.userName}">  
                <p><label for="lastname">First Name</label></p>
                <p><input type="text" id="firstname"  class="text"name="firstname" value="${user.firstName}" onBlur="if(this.value=='')this.value='First Name'" onFocus="if(this.value=='First Name')this.value=''"disabled></p> 
                <p><label for="lastname">Last Name</label></p>
                <p><input type="text" id="lastname"  class="text" name="lastname" value="${user.lastName}" onBlur="if(this.value=='')this.value='Last Name'" onFocus="if(this.value=='Last Name')this.value=''"disabled></p> 
                <p><label for="email">E-mail address</label></p>
                <p><input type="email" id="email"  class="text" name="email" value="${user.emailAddress}" onBlur="if(this.value=='')this.value='mail@address.com'" onFocus="if(this.value=='mail@address.com')this.value=''"disabled></p> 
                <%if(u!=null){ if(u.getRolerole()>0) { %>
                
                <p><label for="warehouserights">Provider Rights</label></p>
                <p><input type="radio" id="radio4" name="radios" value="none" <c:choose> <c:when test="${user.providerr=='None'}">checked</c:when></c:choose><c:choose><c:when test="${user.rolerole==333}">disabled</c:when></c:choose>>
             <label for="radio4">None</label>
                <input type="radio" id="radio5" name="radios"value="read"<c:choose> <c:when test="${user.providerr=='Read'}">checked</c:when></c:choose> <c:choose><c:when test="${user.rolerole==333}">disabled</c:when></c:choose>>
                <label for="radio5">Read</label>
                <input type="radio" id="radio6" name="radios"value="write"<c:choose> <c:when test="${user.providerr=='Write'}">checked</c:when></c:choose> <c:choose><c:when test="${user.rolerole==333}">disabled</c:when></c:choose> >
                 <label for="radio6">Write</label></p>

                <p><label for="warehouserights">Product Rights</label></p>
                <p><input type="radio" id="radio7" name="radios1" value="none"<c:choose> <c:when test="${user.productr=='None'}">checked</c:when></c:choose><c:choose><c:when test="${user.rolerole==333}">disabled</c:when></c:choose>>
                 <label for="radio7">None</label>
                <input type="radio" id="radio8" name="radios1"value="read"<c:choose> <c:when test="${user.productr=='Read'}">checked</c:when> </c:choose><c:choose><c:when test="${user.rolerole==333}">disabled</c:when></c:choose>>
                  <label for="radio8">Read</label>
                <input type="radio" id="radio9" name="radios1"value="write"<c:choose> <c:when test="${user.productr=='Write'}">checked</c:when> </c:choose><c:choose><c:when test="${user.rolerole==333}">disabled</c:when></c:choose>>
                    <label for="radio9">Write</label></p>
                <p><label for="warehouserights">Warehouse Rights</label></p>
                 <p><input type="radio" id="radio1" name="radios2" value="none" <c:choose> <c:when test="${user.warehouser=='None'}">checked</c:when></c:choose><c:choose><c:when test="${user.rolerole==333}">disabled</c:when></c:choose>>
                <label for="radio1">None</label>
                <input type="radio" id="radio2" name="radios2"value="read" <c:choose> <c:when test="${user.warehouser=='Read'}">checked</c:when></c:choose><c:choose><c:when test="${user.rolerole==333}">disabled</c:when></c:choose>>
                <label for="radio2">Read</label>
                <input type="radio" id="radio3" name="radios2"value="write"<c:choose> <c:when test="${user.warehouser=='Write'}">checked</c:when></c:choose> <c:choose><c:when test="${user.rolerole==333}">disabled</c:when></c:choose>>
                <label for="radio3">Write</label></p>

                <%}}%>
                
                <div class="fashbuttons">
                   <% if(u.getRolerole()==333){%>  <c:choose><c:when test="${user.rolerole!=333}"><p class="registerbuttons"><input type="submit" name="update" value="Update" ></p></c:when></c:choose>
<%}%>
                <p class= "registerbuttons"><input type="submit" name="cancel" formnovalidate value="Cancel"></p>
                </div>
            </c:forEach>  
       
   </fieldset>
    </form>
</div>
<div class="wrapper">
    <div class="fotterz">
            <p>Tel. Num. (305)+20145729 839.</p>
           
        </div>
        <div class="footer">
            <p>Copyright (c) 2013</p>
        </div>
        </div>

</body>
</html>