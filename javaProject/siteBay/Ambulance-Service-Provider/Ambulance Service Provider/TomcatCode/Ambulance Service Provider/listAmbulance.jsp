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
<script language="JavaScript">
 
 function validatePage()
 {
 	var choices = document.getElementsByName("check");
 	for(var i=0;i<choices.length;i++)
 	{
 		if(choices[i].checked)
 		{
 			return confirm("Do you want to really delete the Ambulance?");
 		}
 	} 
 	alert("Select atleast one Detention for this action");
 	return false;
 }
 function checkAll()
{
   var checkboxex = document.getElementsByName("check");
   for(var i=0;i<checkboxex.length;i++)
   {
   	 	checkboxex[i].checked=document.getElementById("checkall").checked;
   }
}
 </script>
<body>
<%
 
  AmbulanceDAO adao = new AmbulanceDAO();
  boolean flag=false;
  ArrayList al = adao.getAmbulanceDetails();
	  		
	  		
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
    <div class="sub4">Ambulance Details
      <div class="sub3">
          
          <form id="list" name="viewambulanceform" method="post" action="deleteAmbulanceAction.jsp"  onsubmit="return validatePage()">
           <%
	        if(al!=null && al.size()>0)
		   {
		     		
        %>
            <table width="245" border="1">
            <tr>
            <th><input type="checkbox" name="checkall" onclick="checkAll()" /></th>
            <th>Ambulance Number</th>
            <th>Ambulance Type</th>
            <th>Ambulance Location</th>
            <th>Ambulance Equipment</th>
            </tr>
            <% for(int i=0;i<al.size();i++)
		     {
		   	AmbulanceDTO adto = (AmbulanceDTO)al.get(i);
		   	%>	
            <tr>
            <td><input type="checkbox" name="check" value="<%=adto.getAid()%>" /></td>
            <td><%=adto.getAno()%></td>
	    	<td><%=adto.getAtype()%></td>
	    	<td><%=adto.getAlocation()%></td>
	    	<td><%=adto.getAequipement() %></td>
	    	</tr>
	    	<%} %>
            <tr><td><input type="button" value="Add New" onclick="javascript:location.href='newAmbulance.jsp'"/></td>
           <td><div align="center"><input type="submit" value="Delete" name="delete" /></div></td></tr>
            </table>
           <%}%> 
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
