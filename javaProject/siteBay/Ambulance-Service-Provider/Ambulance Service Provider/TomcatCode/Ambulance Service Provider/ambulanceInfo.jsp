<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@page import="java.util.*" %>
<%@page import="com.dts.em.dao.*" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="style.css" rel="stylesheet" type="text/css" media="screen" />
<style type="text/css">
</style>
</head>
<body>
<%  
 String temp = request.getParameter("id"); 
 ArrayList al = new AmbulanceDAO().ambulanceInformation(temp); 	 
%>

	<h1><jsp:include page="header.html"/></h1>
	

<div id="menu">
	<ul>
	<%

		if((session.getAttribute("role")).equals("admin"))
		{%>
			<jsp:include page="adminoptions.html"/>  
        <%}
		else
		{
			%>
				<jsp:include page="useroptions.jsp"/>  
			<%
		}%>
</div>
<hr />
<div id="latest-post" class="post">
	<p class="byline">&nbsp;</p>
  <h1 class="title"><a href="#">Welcome to</a></h1>
  <p class="title">&nbsp;</p>
  <div class="entry">
  <div class="sub1">
    <div class="sub4">Ambulance information
      <div class="sub3">
          <form id="form1" method="post" action="">
          <table width="245" border="0">
     		<%if(al!=null && al.size() > 0){ %>
				
            <tr><td>Ambulance No </td><td><%=al.get(0) %></td></tr>
            <tr><td>Driver </td><td><%=al.get(1) %></td></tr>
            <tr><td>Helper </td><td><%=al.get(2) %></td></tr>
            <tr><td>Doctor </td><td><%=al.get(3) %></td></tr>
     		
     		<%}else{%> 
     		
     		<% 	} %>
     		<tr><td><input type="button" value="Back" onclick="javascript:history.go(-1)"/></td></tr>
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
