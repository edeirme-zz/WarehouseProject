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
<p>Warehouse Configuration</p>
<div>
    <div class="Boxes">
<div class= "userlistnew showprod">
                    <p>Warehouse Inventory</p>
                    <form id="AjaxReq" action="" method="post">              
    <table class="bordered" >  
        <thead>  
            <tr>  
                <th>Products</th>  
             <% if(u.getwarehouser()=="Write") {%>   <th>Action</th>  <%}%>
                  
            </tr>
        </thead>  
        <tbody> 
        
           <c:forEach items="${ppwList}" var="ppw"> 
                <tr>
                     <td><c:out value="${ppw.pprodName}" /></td> 
                  <% if(u.getwarehouser()=="Write") {%>   <td onclick="document.location = '<c:url value="deleteproduct?prodname=${ppw.pprodName}&warehousename=${ppw.wwName}"/>';" >Delete</td> 
                  <%}%>
                      
                    
                    
                </tr>  
           </c:forEach>
        
        </tbody> 
    </table>
  </form>            
    </div>
    <div class= "userlistnew showprod">
                    <p>Warehouse Activity</p>
                    <form id="AjaxReq">              
    <table class="bordered" >  
        <thead>  
            <tr>  
                <th>From</th>  
                <th>To</th>  
                <th>Action</th>  
                <th>Product</th>  
                <th>Time</th>  
                  
            </tr>
        </thead>  
        <tbody> 
           <c:forEach items="${actlist}" var="act"> 
                <tr>
                     <td><c:out value="${act.from}" /></td> 
                     <td><c:out value="${act.to}" /></td> 
                     <td><c:out value="${act.action}" /></td> 
                     <td><c:out value="${act.product}" /></td> 
                     <td><c:out value="${act.time}" /></td> 
                </tr>  
           </c:forEach>
        </tbody> 
    </table>
  </form>            
    </div>
</div>
<div  id="login3" class="login3 dexa">
  <form action="<%= response.encodeURL("updateWarehouse")%>" method="get">
      
  
            <c:forEach items="${wli}" var="warehouse">        
            <!LOGIN BUTTON>


        <h2><span class="fontawesome-lock"></span>Modify User</h2>

        

            <fieldset>

                <p><label for="username">Warehouse Name</label></p>
                <p><input type="text" id="warehousename"  class="text" name="warehousename" value="${warehouse.wareName}" onBlur="if(this.value=='')this.value='Username'" onFocus="if(this.value=='Username')this.value=''"disabled></p> 
                <input type="hidden" name="warehousename" value="${warehouse.wareName}">  
                <p><label for="lastname">Address</label></p>
                <p><input type="text" id="firstname"  class="text" name="waddr" value="${warehouse.wareAddress}" onBlur="if(this.value=='')this.value='First Name'" onFocus="if(this.value=='First Name')this.value=''"></p> 
                <p><label for="lastname">Description</label></p>
                <p><input type="text" id="lastname" class="text"  name="wdescr" value="${warehouse.wareDescr}" onBlur="if(this.value=='')this.value='Last Name'" onFocus="if(this.value=='Last Name')this.value=''"></p> 
                 <p><label for="lastname">Distinct Products Count</label></p>
                <p><input type="text" id="lastname" class="text"  name="wdescr" value="${count}" onBlur="if(this.value=='')this.value='Last Name'" onFocus="if(this.value=='Last Name')this.value=''"disabled></p> 
                
                <p><label for="warehouserights">State</label></p>
                <p><input type="radio" id="radio4" name="radios" value="Open" <c:choose> <c:when test="${warehouse.wareState=='Open'}">checked</c:when></c:choose>>
             <label for="radio4">Open</label>
                <input type="radio" id="radio5" name="radios"value="Closed"<c:choose> <c:when test="${warehouse.wareState=='Closed'}">checked</c:when></c:choose>>
                <label for="radio5">Closed</label>
                

                

               
                
                <div class="fashbuttons">
              <% if (u.getwarehouser()=="Write") {%>  <p class="registerbuttons"><input type="submit" name="update" value="Update" ></p>
                <p class="registerbuttons"><input type="submit" name="delete" value="Delete"></p> <%}%>
                <p id="dltbtn" class= "registerbuttons"><input type="submit"  formnovalidate name="back" value="Back"></p>
                </div>
            </c:forEach>                
       
   </fieldset>
    </form>
</div>
<div class="wrapper4">
    <div class="fotterz">
            <p>Tel. Num. (305)+20145729 839.</p>
           
        </div>
        <div class="footer">
            <p>Copyright (c) 2013</p>
        </div>
        </div>

</body>
</html>