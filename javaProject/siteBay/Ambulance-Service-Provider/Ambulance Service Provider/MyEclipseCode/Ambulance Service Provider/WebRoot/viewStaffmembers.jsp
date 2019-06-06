<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@page import="com.dts.em.dao.*" %>
<%@page import="com.dts.es.dto.*" %>
<%@page import="java.util.*"%>
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
<%
 
  StaffDAO sdao = new StaffDAO();
  boolean flag=false;
  ArrayList al = sdao.getStaffDetails();
	  		
	  		
 %>	

	<h1><jsp:include page="header.html"/></h1>
	

<div id="menu">
	<ul>
		<jsp:include page="adminoptions.html"/>  
	</ul>
</div>
<hr />
<div id="latest-post" class="post">
	<p class="byline">&nbsp;</p>
  <h1 class="title"><a href="#">Welcome to</a></h1>
  <p class="title">&nbsp;</p>
  <div class="entry">
  <div class="sub1">
    <div class="sub4">Staff Members
      <div class="sub3">
        <form id="form1" method="post" action="">
        <%
	        if(al!=null && al.size()>0)
		   {
		     		
        %>
            <table width="245" border="1">
            <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Role of Staff Member</th>
            <th>Phone</th>
            <th>Email</th>
            <th>Location</th>
            <th>Address</th>
            <th>Working Hours</th>
            <th>Joining Date</th>
             </tr>
           <% for(int i=0;i<al.size();i++)
		     {
		   		StaffDTO sdto = (StaffDTO)al.get(i);
		   	%>	
            <tr>
	   		<td><a href="editStaffDetails.jsp?id=<%=sdto.getSid()%>"><%=sdto.getFirstname()%></a></td>
	    	<td><%=sdto.getLastname()%></td>
	    	<td><%=sdto.getStaffRole()%></td>
	    	<td><%=sdto.getPhone() %></td>
	    	<td><%=sdto.getEmail() %></td>
	    	<td><%=sdto.getLocation()%></td>
	    	<td><%=sdto.getAddress()%></td>
	    	<td><%=sdto.getWorkinghours() %></td>
	    	<td><%=sdto.getJdate1() %></td>
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
