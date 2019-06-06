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
    <div class="sub4">Staff Details
      <div class="sub3">
 <% String temp="";
   StaffDAO sdao= new StaffDAO();
   StaffDTO sdto=null;
   temp=request.getParameter("id");
   if(temp!=null && !temp.equals(""))
   {
   	  sdto = sdao.getStaffDetail(temp);
   }
   %>
          <form id="form1" name="editstaffform" method="post" action="editStaffAction.jsp">
            
            <table width="245" border="0">
            <tr> 
			<td>First Name</td><td><input type="text" maxlength="25" size="20" name="firstname" value="<%=sdto.getFirstname() %>"/></td>
			<tr>
			<td >Last Name</td><td><input type="text" maxlength="25" size="20" name="lastname" value="<%=sdto.getLastname() %>"/></td>
			</tr>
			<tr>
        	<td>Role of the Staff Member</td>
        	<td><select name="stafftype">
        	<%if(sdto.getStaffRole().equals("Doctor")){%>
        	<option value="Doctor" selected>Doctor</option>
        	<option value="Helper">Helper</option>
        	<option value="Driver">Driver</option>
        	<%}else if(sdto.getStaffRole().equals("Helper")){%>
        	 <option value="Doctor">Doctor</option>
        	<option value="Helper" selected>Helper</option>
        	<option value="Driver">Driver</option>
        	<%}else{%>
        	<option value="Doctor">Doctor</option>
        	<option value="Helper">Helper</option>
        	<option value="Driver" selected>Driver</option>
        	<%}%>
        	</select></td>
        	</tr>	
			<tr>
				<td>Phone</td>
				<td><input type="text" maxlength="25" size="20" name="phone" value="<%=sdto.getPhone() %>"/></td>
			</tr>
			<tr><td>State</td><td>
			<input type="text" maxlength="25" size="20" name="email" value="<%=sdto.getEmail() %>"/>
			</td></tr>
			<tr><td height="20">Location</td>
			<td height="20">
			<input type="text" maxlength="25" size="20" name="location" value="<%=sdto.getLocation() %>"/>
			</td>
			</tr>
				<tr>
				<td>Address</td>
				<td><textarea  cols="20" rows="2" name="address"><%=sdto.getAddress() %></textarea>
				</td>
				</tr>
				
				<tr><td height="20">Working Hours</td>
			<td height="20">
			<input type="text" maxlength="25" size="20" name="workinghours" value="<%=sdto.getWorkinghours() %>"/>
			</td>
			</tr>
				<tr><td>		
				Date Of joining
				</td>
				<td>
				
				<input type="text" name="jdate" readonly value="<%=DateWrapper.parseDate(sdto.getJdate1()) %>"/>
				<a href="javascript:show_calendar('document.editstaffform.jdate',document.editstaffform.jdate.value);">
				<img src="images/cal.gif" width="18" height="18" border="0" />
				</a>		
			<tr><td>
			<input type="hidden" name="id" value=<%=temp%> />
			</td></tr>  	
			<tr><td><input type="submit" value="Update" name="submit"/>
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
