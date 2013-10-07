
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
<script language="javascript">
function validate() {
    var chks = document.getElementsByName('wname');
    var chks2 =document.getElementsByName('provname');
    
    var hasChecked = false;
    var hasChecked2= false;
    
    for (var i = 0; i < chks.length; i++) {
        if (chks[i].checked) {
            hasChecked = true;
            break;
        }
    }
    for (var i = 0; i < chks2.length; i++) {
        if (chks2[i].checked) {
            hasChecked2 = true;
            break;
        }
    }
    
    if (hasChecked == false) {
        alert("Please select at least one warehouse.");
        return false;
    }
    if (hasChecked2 == false) {
        alert("Please select at least one provider.");
        return false;
    }
    return true;
}
</script>
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
<p>Add New Product</p>
</div>
        <c:forEach items="${eror}" var="warehouse"> <div class="errorlogin">
<p><c:out value="${warehouse}" />  </p>

</div>
        </c:forEach>
        
        
<!LOGIN BUTTON>
<div id="login2">

        <h2><span class="fontawesome-lock"></span>Add Product</h2>

        <form action="<%= response.encodeURL("registerProduct")%>" method="POST" onSubmit="return validate()">

            <fieldset>

                <p><label for="pname">Product Name</label></p>
                <p><input type="text" id="warehouse" class="text" name="pname" value="" placeholder="Product Name" required></p>
                <p><label for="psn">Serial Number</label></p>
                <p><input type="number" id="warehouseadd"  class="text" name="psn" value="" placeholder="Serial Number" required></p>
                <p><label for="pdescr">Product Description</label></p>
                <p><input type="text" id="warehousedescr"  class="text" name="pdescr" value="" placeholder="Description" required></p>
                <p><label for="ptype">Product Type</label></p>
                <p><input type="text" id="warehousedescr" class="text" name="ptype" value="" placeholder="Type"></p>
                <p><label for="pweight">Product Weight</label></p>
                <p><input type="number" id="warehousedescr"  class="text" name="pweight" value="" placeholder="Weight"></p>
               
   
                <ul class="checklist" ><p>Select Warehouses and Quantity</p>
                    
                        
                    <c:forEach items="${wli}" var="warehouse">
                            <li><label for="o1"><input id="${warehouse.wareName}"  name="wname" type="checkbox" value="<c:out value="${warehouse.wareName}" />"/><c:out value="${warehouse.wareName}" /></label></li>
                          
                        <input type="number" id="${warehouse.wareDescr}"   name="quantity" class="sfield" value="" placeholder="Quantity"  disabled>
                        <script type="text/javascript">
    document.getElementById('<c:out value="${warehouse.wareName}" />').onchange = function() {
    document.getElementById('<c:out value="${warehouse.wareDescr}" />').disabled = !this.checked;
   
};
</script>
                    </c:forEach>
                    
                        
                    </ul>
         
                 <ul class="checklist" required><p>Select Providers and Prices</p>
                      <c:forEach items="${provli}" var="provider">
                        <li><label for="o1"><input id="${provider.provName}" name="provname" type="checkbox" value="<c:out value="${provider.provName}" />" /><c:out value="${provider.provName}" /></label></li>
                          
                 <input type="number" id="${provider.provSSN}" name="price" class="sfield" value="" placeholder="Price" disabled >
                  <script type="text/javascript">
    document.getElementById('<c:out value="${provider.provName}" />').onchange = function() {
    document.getElementById('<c:out value="${provider.provSSN}" />').disabled = !this.checked;
};
</script>
                    </c:forEach>
                    </ul>

                <p class="registerbuttons"><input type="submit" name="submit" value="Submit"></p>
                <p class= "registerbuttons"><input type="submit" formnovalidate name="cancel" value="Cancel"></p>

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