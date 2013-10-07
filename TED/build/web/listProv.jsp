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
<p>Provider Configuration</p>

    <div class= "userlistnew showprod">
                    <p>Providing Products</p>
                    <form id="AjaxReq">              
    <table class="bordered"  >  
        <thead>  
            <tr>  
                <th>Product Name</th>  
                  
            </tr>
        </thead>  
        <tbody> 
           <c:forEach items="${provprodli}" var="provprod"> 
                <tr>
                     <td><c:out value="${provprod.pprodName}" /></td> 
                </tr>  
           </c:forEach>
        </tbody> 
    </table>
  </form>            
    </div>
    

<div  id="login34"  class="login3 dexa">
  <form action="<%= response.encodeURL("updateProvider")%>" method="get">
      
  
            <c:forEach items="${provli}" var="provider">        
            <!LOGIN BUTTON>


        <h2><span class="fontawesome-lock"></span>Modify Provider</h2>

        

            <fieldset>

                <p><label for="pname">Provider Name</label></p>
                <p><input type="text" id="pname" name="provname"  class="text" value="${provider.provName}" onBlur="if(this.value=='')this.value='Provider Name'" onFocus="if(this.value=='Provider Name')this.value=''"disabled></p> 
                <input type="hidden" name="provname" value="${provider.provName}">  
                 <p><label for="descri">Address</label></p>
                <p><input type="text" id="address"  class="text" name="provaddress" value="${provider.provAddress}" onBlur="if(this.value=='')this.value='Address'" onFocus="if(this.value=='Address')this.value=''"></p> 
                 <p><label for="tp">Social Security Number</label></p>
                <p><input type="number" id="tp" name="ssn"  class="text" value="${provider.provSSN}" onBlur="if(this.value=='')this.value=''" onFocus="if(this.value=='')this.value=''"></p> 
                
                
                

               
                
                <div class="fashbuttons">
               <% if(u.getproviderr()=="Write") {%> <p class="registerbuttons"><input type="submit" name="update" value="Update" ></p> 
                 <p class="registerbuttons"><input type="submit" name="delete" value="Delete"></p><%}%>
                <p id="dltbtn" class= "registerbuttons"><input type="submit" formnovalidate name="back" value="Back" ></p>
                </div>
            </c:forEach>                
       
   </fieldset>
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