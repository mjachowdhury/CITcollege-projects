
<%@page import="java.util.ArrayList"%>
<%@page import="com.dts.em.dao.AmbulanceDAO"%>
<%@page import="com.dts.es.dto.AmbulanceDTO"%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

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
<script language="javascript">
function validatePage()
{
    if(document.search.location.value=="")
    {
        alert("Enter location");
    	return false;
    }
    return true;
 }
 </script>   
</head>
<body>
<%
	String location = request.getParameter("location");
	ArrayList al = null;
	if(location!=null && !location.equals(""))
	{
		al = new  AmbulanceDAO().getAvailableAmbulances(location.trim());
	}
		
 %>

	<h1><jsp:include page="header.html"/></h1>
	

<div id="menu">
	<ul>
		<jsp:include page="useroptions.jsp"/>  
	</ul>
</div>
<hr />
<div id="latest-post" class="post">
	<p class="byline">&nbsp;</p>
  <h1 class="title"><a href="#">Welcome to</a></h1>
  <p class="title">&nbsp;</p>
  <div class="entry">
  <div class="sub1">
    <div class="sub4">Search For Ambulance
      <div class="sub3">
          <form name="search" id="form1" method="post" action="searcAmbulance.jsp" onsubmit ="return validatePage()" >
            <br />
            <table width="245" border="0">
              <tr>
                <td nowrap>Enter Location</td>
                <td><input type="text" name="location" /></td>
              </tr>
              <tr>
              <td colspan=2><input type="submit" value="submit" />
              </td>
              </tr>
            </table>
            <%if(al!=null){
             if(al.size()>0)
             {
            %>
            <table width="245" border="1">
            <tr>
            <th nowrap>Ambulance Number</th>
            <th nowrap>Ambulance Type</th>
            <th nowrap>Ambulance Location</th>
            <th nowrap>Ambulance Equipment</th>
            <th nowrap>Ambulance Status</th>
            </tr>
            <%String status = ""; 
            for(int i=0;i<al.size();i++)
		     {
		   	AmbulanceDTO adto = (AmbulanceDTO)al.get(i);
		   	%>	
            <tr>
            <td><%=adto.getAno()%></td>
	    	<td><%=adto.getAtype()%></td>
	    	<td><%=adto.getAlocation()%></td>
	    	<td><%=adto.getAequipement() %></td>
	    	<% status = new AmbulanceDAO().getAmbulanceStatus(adto.getAid()); 
	    	if(status.equals("S")){ 
	    	%>
	    	<td><strong><font color="#FF0000">Not Available</font></strong></td>
	    	<%}else{ %>
	    	<td><strong><font color="#00FF33">Available</font></strong></td>
	    	<%} %>
	    	</tr>
	    	<%} %>
            </table>
            <%}else{%>
            <table width="245" border="0">
            <tr><td>No Records Found</td></tr> 
            </table>
             <%} %>
              
            <%}else{} %>
            
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
