<%@page import="com.dts.em.dao.*" %>
<%@page import="com.dts.es.dto.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="style.css" rel="stylesheet" type="text/css" media="screen" />
<style type="text/css">
<!--
.style1 {color: #000000}
.style3 {color: #000000; font-weight: bold; }
a:link {
	text-decoration: none;
}
a:visited {
	text-decoration: none;
}
a:hover {
	text-decoration: none;
}
a:active {
	text-decoration: none;
}
-->
</style>
</head>
<body>
<%String temp="";
   AmbulanceDAO adao= new AmbulanceDAO();
   AmbulanceDTO adto=null;
   temp=request.getParameter("id");
   if(temp!=null && !temp.equals(""))
   {
   	 adto = adao.getAmbulanceDetails(temp);
   }
   %>

	<h1><jsp:include page="header.html"/></h1>
	

<div id="menu">
	<ul>
		<jsp:include page="adminoptions.html"/>  
	</ul>
</div><div id="latest-post" class="post">
	<p class="byline">&nbsp;</p>
  <h1 class="title"><a href="#">Welcome to</a></h1>
  <p class="title">&nbsp;</p>
  <div class="entry">
  <div class="sub1">
    <div class="sub4">Edit Ambulance Form
      <div class="sub3">
          <form name="editAmbulanceform" id="form1" method="post" action="editAmbulanceAction.jsp">
            <table width="245" border="0">
            <tr>
            <td>Ambulance Number</td>
            <td><input type="text" name="ano" value="<%=adto.getAno() %>"/></td>
            </tr>
            <tr>
            <td>Ambulance Type</td>
            <td>
            <select name="atype">
            <%if(adto.getAtype().equals("Small Ambulance")){ %>
	            <option value="Small Ambulance" selected>Small Ambulance</option>
	            <option value="Big Ambulance">Big Ambulance</option>
	           <%}else{ %> 
	            <option value="Small Ambulance">Small Ambulance</option>
	            <option value="Big Ambulance" selected>Big Ambulance</option>
	            <% } %>
            </select>
            </td>
            </tr>
            <tr>
            <td>Ambulance Location</td>
            <td><input type="text" name="alocation" value="<%=adto.getAlocation() %>"/></td>
            </tr>
            <tr>
            <td>Ambulance Equipment</td>
            <td><textarea name="aequipement"> <%= adto.getAequipement()%> </textarea></td>
            </tr>
            <tr>
            <tr>
            <td><input type="hidden" name="id" value="<%=temp %>"/></td></tr>
            <tr>
            <td><input type="submit" value="Update"/></td>
             <td><input type="reset" value="Clear"/></td>
            </tr>
            </table>
          </form>
      </div>
    </div>
    <p>&nbsp;</p>
    <p>&nbsp;</p>
  </div>
  <p></p>
		<p>&nbsp;</p>
  </div>
	
</div>
<div id="recent-posts">
	<h2>&nbsp;</h2>

  
</div>


<div id="footer">
	
</div>
</body>
</html>
