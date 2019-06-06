<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@page import="com.dts.dae.dao.StaffDAO"%>
<%@page import="com.dts.core.util.CoreHash"%>
<%@page import="com.dts.dae.model.StaffBean"%>
<%@page import="java.util.*"%>
<%
  StaffDAO sdao = new StaffDAO();
  CoreHash ch = sdao.getStaffDetails();
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>ListStaff Page</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="default.css" rel="stylesheet" type="text/css" media="screen" />
</head>
 <script language="JavaScript">
 
 function validatePage()
 {
 	var choices = document.getElementsByName("check");
 	for(var i=0;i<choices.length;i++)
 	{
 		if(choices[i].checked)
 		{
 			return confirm("Do you want to really delete the Staff?");
 		}
 	} 
 	alert("Select atleast one Detention for this action");
 	return false;
 }
 </script>
<body>
<!-- start header -->
<div id="logo">
	<h1><jsp:include page="header.html"/></h1>
	</div>
<div id="menu">
	<ul>
		<jsp:include page="adminoptions.html"/> 
	</ul>
</div>
<!-- end header -->
<!-- start page -->
<div id="page">
  <div id="page-bg">
		<!-- <div id="latest-post">
		  <h1>Welcome to Our Website!</h1>
	  </div>-->
		<!-- start content -->
	<!-- end content -->
	<!-- start sidebar -->
<div id="sidebar">
			<ul>
			<form id="list" name="listform" method="post" action="DeleteStaffAction.jsp"  onsubmit="return validatePage()">
			<%
	        if(ch!=null && ch.size()>0)
		    { 		
            %>
				   <fieldset>
                   <legend>Staff Details </legend>
                   
            <table align="center" width="245" border="1">
            <tr>
            <th>select checkbox for delettion</th>
            <th>Employee No</th>
            <th>Employee Name</th>
            <th>Designation</th>
            <th>Salary</th>
            <th>Joining Date</th>
            <th>Account No</th>
            <th>Contact Address</th>
            <th>Phone No</th>
			<!--<th><input type="checkbox" name="checkall" /></th>-->
            </tr>
           <% Enumeration enumeration = ch.elements();
	        while(enumeration.hasMoreElements()) 
		    {
		    	StaffBean sb = (StaffBean)enumeration.nextElement();
		   	%>	
            <tr>
            <td><input type="checkbox" name="check" value="<%=sb.getEmpId()%>" /></td>
	   		<td><%=sb.getEmpId()%></td>
			<td><%=sb.getEmpName()%></td>
	    	<td><%=sb.getDesignatin()%></td>
	    	<td><%=sb.getSalary()%></td>
	    	<td><%=sb.getDoj()%></td>
	    	<td><%=sb.getAccno()%></td>
	    	<td><%=sb.getContactAddress() %></td>
	    	<td><%=sb.getPhoneNo()%></td>
	    	</tr>
	        <%}%> 
            <tr>
            <td colspan=5><div align="center"><input type="button" value="Add New" onclick="javascript:location.href='AddStaff.jsp'"/></div></td>
            <td colspan=4><div align="center"><input type="submit" value="Delete" name="delete" /></div></td></tr>
           </table>
           <%}%>
           </fieldset>
                  </form>
				
				
					<div align="center"></div>
	  </ul>
		</div>
	  <div style="clear: both;">&nbsp;</div>
	</div>
  <div align="center"></div>
</div>
<!-- end page -->
<div id="footer">
			<p id="legal">
				<a href="#">Privacy Policy</a> |
				<a href="#">Terms of Use</a>
			</p>
		</div>
</body>
</html>
