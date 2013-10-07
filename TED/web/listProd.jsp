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
</div>
        <div class="masterclass">
        <div class="Boxes">
<div class= "userlistnew showprod">
                    <p>Warehouses Kept</p>
                    <form id="AjaxReq">              
    <table class="bordered" >  
        <thead>  
            <tr>  
                <th>Warehouse Name</th>  
                <th>Product Quantity</th>  
                  
            </tr>
        </thead>  
        <tbody> 
           <c:forEach items="${ppwList2}" var="ppw"> 
                <tr>
                     <td><c:out value="${ppw.wwName}" /></td> 
                     <td><c:out value="${ppw.quantity}" /></td> 
                </tr>  
           </c:forEach>
        </tbody> 
    </table>
  </form>            
    </div>
    <div class= "userlistnew showprod2">
                    <p>Product Providers</p>
                    <form id="AjaxReq">              
    <table class="bordered"  >  
        <thead>  
            <tr>  
                <th>Provider Name</th>  
                  
            </tr>
        </thead>  
        <tbody> 
           <c:forEach items="${phplist}" var="php"> 
                <tr>
                     <td><c:out value="${php.pprovName}" /></td> 
                </tr>  
           </c:forEach>
        </tbody> 
    </table>
  </form>            
    </div>
 <div class= "userlistnew showprod3">
                    <p>Product Prices</p>
                    <form id="AjaxReq">              
    <table class="bordered" >  
        <thead>  
            <tr>  
                <th>Provider Name</th>  
                <th>Price</th>  
                  
            </tr>
        </thead>  
        <tbody> 
           <c:forEach items="${phplist}" var="php"> 
                <tr>
                     <td><c:out value="${php.pprovName}" /></td> 
                     <td><c:out value="${php.price}" /></td> 
                </tr>  
           </c:forEach>
        </tbody> 
    </table>
  </form>            
    </div>
        </div>

    <div  id="login3" class="login3 dexa">
  <form action="<%= response.encodeURL("updateProduct")%>" method="get">
      
  
            <c:forEach items="${pli}" var="product">        
            <!LOGIN BUTTON>


        <h2><span class="fontawesome-lock"></span>Modify Product</h2>

            <fieldset>

                <p><label for="pname">Product Name</label></p>
                <p><input type="text" id="pname"  class="text" name="pname" value="${product.prodName}" disabled></p> 
                <input type="hidden" name="pname" value="${product.prodName}">  
                <p><label for="sn">Serial number</label></p>
                <p><input type="number" id="sn" class="text" name="psn" value="${product.sn}" disabled></p> 
               <input type="hidden" name="psn" value="${product.sn}"> 
                <p><label for="descri">Description</label></p>
                <p><input type="text" id="descri" class="text"  name="pdescr" value="${product.prodDescr}" ></p> 
                 <p><label for="tp">Type</label></p>
                <p><input type="text" id="tp" name="tp"  class="text" value="${product.prodType}" ></p> 
                
                 <p><label for="weight">Weight</label></p>
                <p><input type="number" id="weight" class="text" name="weight" value="${product.prodWeight}" ></p> 
                <p>Transfer Product</p>
                <div class="dropbox">
                    <p class="warehouses1">From</p>
                <select class="warehouses1" name="from">
                    <option name="from" value="none">No Action</option>
                    <c:forEach items="${ppwList2}" var="ppw">
                        <option name="from" value="${ppw.wwName}"><c:out value="${ppw.wwName}" /></option>
                    </c:forEach>
                </select>
                    <p class="warehouses1"> To </p>
                <select class="warehouses1" name="to">
                     <option name="to" value="none">No Action</option>
                    <c:forEach items="${wli}" var="warehouse">
                        <option name="to" value="${warehouse.wareName}"><c:out value="${warehouse.wareName}" /></option>
                          
                    </c:forEach>
                </select>
                    <input type="number" id="amount" name="amount" placeholder="Move amount">
                </div>
               
                 <ul class="checklist"><p>Select Providers and Prices</p>
                      <c:forEach items="${provli}" var="provider">
                          <li><label for="o1"><input id="${provider.provName}" name="provname" type="checkbox" value="<c:out value="${provider.provName}"/>" /><c:out value="${provider.provName}" /></label></li>
                            <input type="number" id="${provider.provSSN}"  name="price" class="sfield"  placeholder="Price" disabled>
                 <script type="text/javascript">
                 document.getElementById('<c:out value="${provider.provName}" />').onchange = function() {
                         document.getElementById('<c:out value="${provider.provSSN}" />').disabled = !this.checked;
                         document.getElementById('<c:out value="${provider.provSSN}" />').required ===this.checked;
                    };
                </script>
                <script type="text/javascript">
                    var $n = jQuery.noConflict();
  $n('input[name^=debits]').live("focus", function(){  
    $n(this).next('input[name^=credits]').val('').attr("readonly", "readonly");
    $n(this).removeAttr("readonly");
  });
  $n('input[name^=credits]').live("focus", function(){
      $n(this).prev('input[name^=debits]').val('').attr("readonly", "readonly");
    $n(this).removeAttr("readonly");
  });
                </script>
                    </c:forEach>
                    </ul>
                <div class="fashbuttons">
             <% if(u.getproductr()=="Write") {%>    <p class="registerbuttons"><input type="submit" name="update" value="Update" ></p>
                 <p class="registerbuttons"><input type="submit" name="delete" value="Delete"></p><%}%>
                <p id="dltbtn" class= "registerbuttons"><input type="submit" formnovalidate name="back" value="Back" ></p>
                </div>
            </c:forEach>                
       
   </fieldset>
    </form>
</div>
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