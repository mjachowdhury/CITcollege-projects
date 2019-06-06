<%@page import="com.dts.em.dao.*" %>
<%@page import="com.dts.es.dto.*" %>
<%@page import="com.dts.em.fun.*" %>
<%@page import="java.util.*"%>
<%@page import="com.dts.core.util.DateWrapper"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%
 
  PatientDAO pdao = new PatientDAO();
  ArrayList al = pdao.getPatientDetails();
	  		
	  		
 %>	
 

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
	<h1><jsp:include page="header.html"/></h1>
<div id="menu">
	<ul>
		<jsp:include page="adminoptions.html"/></ul>
</div><div id="latest-post" class="post">
	<p class="byline">&nbsp;</p>
  <h1 class="title"><a href="#">Welcome to</a></h1>
  <p class="title">&nbsp;</p>
  <div class="entry">
  <div class="sub1">
    <div class="sub4">Patient Details 
      <div class="sub3">
          <form id="form1" method="post" action="">
        <%
	        if(al!=null && al.size()>0)
		   {
		     		
        %>
            <table width="245" border="1">
            
            <tr>
            <th nowrap>First Name</th>
            <th nowrap>Last Name</th>
            <th nowrap>Date Of Birth</th>
            <th nowrap>Location</th>
            <th nowrap>City</th>
            <th nowrap>State</th>
            <th nowrap>Address</th>
            <th nowrap>Phone</th>
            <th nowrap>Height</th>
            <th nowrap>Weight</th>
            <th nowrap>Join Date</th>
            <th nowrap>Type of Message</th>
            </tr>
           <% for(int i=0;i<al.size();i++)
		     {
		   		PatientDTO pdto = (PatientDTO)al.get(i);
		   	%>	
            <tr>
	   		<td><%=pdto.getFirstname()%></td>
	    	<td><%=pdto.getLastname()%></td>
	    	<td><%=DateWrapper.parseDate(pdto.getDob1())%></td>
	    	<td><%=pdto.getLocation()%></td>
	    	<td><%=pdto.getCity()%></td>
	    	<td><%=pdto.getState()%></td>
	    	<td><%=pdto.getAddress()%></td>
	    	<td><%=pdto.getPhone() %></td>
	    	<td><%=pdto.getHeight() %></td>
	    	<td><%=pdto.getWeight() %></td>	    	
	    	<td><%=DateWrapper.parseDate(pdto.getJdate1()) %></td>
	    	<td><%=pdto.getType() %></td>
	    	</tr>
	       <%} %>
            </table>
           <%}%> 
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
