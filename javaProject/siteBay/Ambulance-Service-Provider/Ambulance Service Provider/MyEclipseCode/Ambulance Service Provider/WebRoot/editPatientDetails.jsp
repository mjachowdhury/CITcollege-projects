<%@page import="com.dts.em.dao.*" %>
<%@page import="com.dts.es.dto.*" %>
<%@page import="com.dts.core.util.DateWrapper"%>

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
<script type="text/javascript" src="scripts/general.js"> </script>
<script type="text/javascript" src="scripts/ts_picker.js"> </script>

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


<%--		<jsp:include page="adminoptions.html"/>  --%>
	</ul>
</div>
<hr />
<div id="latest-post" class="post">
	<p class="byline">&nbsp;</p>
  <h1 class="title"><a href="#">Welcome to</a></h1>
  <p class="title">&nbsp;</p>
  <div class="entry">
  <div class="sub1">
    <div class="sub4">Example form
      <div class="sub3">
 <%String temp="";
   PatientDAO pdao= new PatientDAO();
   PatientDTO pdto=null;
   temp=request.getParameter("id");
   if(temp!=null && !temp.equals(""))
   {
   	  pdto = pdao.getPatientDetails(temp);
   }
   %>
          <form id="form1" name="editpatientform" method="post" action="editPatientAction.jsp">
            
            <table width="245" border="0">
            
			<tr> 
			<td>First Name</td><td><input type="text" maxlength="25" size="20" name="firstname" value="<%=pdto.getFirstname() %>"/></TD>
			<tr>
			<td >Last Name</td><td><input type="text" maxlength="25" size="20" name="lastname" value="<%=pdto.getLastname() %>"/></TD>
			</tr>	
			<tr>
			<td>Date of Birth</td>
			<td> <input type="text" name="dob" readonly value="<%=DateWrapper.parseDate(pdto.getDob1()) %>" />
			<a href="javascript:show_calendar('document.editpatientform.dob',document.editpatientform.dob.value);">
			<img src="images/cal.gif" width="18" height="18" border="0"/></a>
			</td>	
			</tr>
			<TR><td height="20">Location</td>
			<td height="20">
			<input type="text" maxlength="25" size="20" name="location" value="<%=pdto.getLocation() %>"/>
			</td>
			</TR>
			<tr>
				<td>City</td>
				<td><input type="text" maxlength="25" size="20" name="city" value="<%=pdto.getCity() %>"/></td>
			</tr>
			<tr><td>State</td><td>
			<input type="text" maxlength="25" size="20" name="state" value="<%=pdto.getState() %>"/>
			</td></tr>
				
				<tr>
				<td>Address</td>
				<td><textarea  cols="20" rows="2" name="address"><%=pdto.getAddress() %></textarea>
				</td>
				</tr>
				<tr>
				<td>Phone Number</td>
				<td><input type="text" name="phone" value="<%=pdto.getPhone() %>"/>
				</td>
				</tr>
				<tr>
				<td>Patient Height</td>
				<td><input type="text" name="height" value="<%=pdto.getHeight() %>"/>
				</td>
				</tr>
				<tr>
				<td>Patient Weight</td>
				<td><input type="text" name="weight" value="<%=pdto.getWeight() %>"/>
				</td>
				</tr>
				<tr><td>		
				Date Of joining
				</td>
				<td>
				<input type="text" name="jdate" readonly value="<%=DateWrapper.parseDate(pdto.getJdate1()) %>"/>
				<a href="javascript:show_calendar('document.editpatientform.jdate',document.editpatientform.jdate.value);">
				<img src="images/cal.gif" width="18" height="18" border="0" />
				</a>	
				</td></tr>	
				<tr>
				<td>Type of Message</td>
				<td>
				<select name="type">
				<% if(pdto.getType().equals("Phone")){%>
				<option value="Phone" selected>Phone</option>
				<option value="SMS">SMS</option>
<!--				<option value="Email">Email</opiton> -->
				<%}else if(pdto.getType().equals("SMS")){ %>
				<option value="Phone">Phone</option>
				<option value="SMS" selected>SMS</option>
				<option value="Email">Email</opiton>
				<%}else { %>
				<option value="Phone">Phone</option>
				<option value="SMS">SMS</option>
				<option value="Email" selected>Email</opiton>
				<%} %>
				</td>
				</tr>
				
			<tr><td>
			<input type="hidden" name="id" value="<%=temp %>" />
			</td></tr>
			
			<tr><td>
			<input type="submit" value="submit" name="submit"/>
			<td>
			<input type="reset" value="Clear" name="reset"/>
			<td></td>
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
