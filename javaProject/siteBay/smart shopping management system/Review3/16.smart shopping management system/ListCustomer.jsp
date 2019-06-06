<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@page import="com.dts.core.util.CoreHash"%>
<%@page import="com.dts.dae.model.CustomerProfile"%>
<%@page import="java.util.*"%>
<%@page import="com.dts.dae.dao.CustomerDAO"%>
<%
  CustomerDAO cdao = new CustomerDAO();
  CoreHash ch = cdao.getCustomerDetails();
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>ViewCustomer Details Page</title>
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
 			return confirm("Do you want to really delete the Customer?");
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
	<!-- 	<div id="latest-post">
		  <h1>Welcome to Our Website!</h1>
	  </div>  -->
		<!-- start content -->
	<!-- end content -->
	<!-- start sidebar -->
<div id="sidebar">
			<ul>
			<form id="list" name="listform" method="post" action="DeleteCustomerAction.jsp"  onsubmit="return validatePage()">
			<%
	        if(ch!=null && ch.size()>0)
		    { 		
            %>
				   <fieldset>
                   <legend>Customer Details </legend>
            <table align="center" width="245" border="1">
            <tr>
            <!--<th><input type="checkbox" name="checkall" /></th>-->
			<th>select</th>
            <th>FirstName</th>
            <th>LastName</th>
            <th>DOB</th>
            <th>City</th>
            <th>State</th>
            <th>Country</th>
            <th>PhoneNo</th>
            <th>E-Mail</th>
            <th>LoginName</th>
            </tr>
           <% Enumeration enumeration = ch.elements();
	        while(enumeration.hasMoreElements()) 
		    {
		    	CustomerProfile cp = (CustomerProfile)enumeration.nextElement();
		   	%>	
            <tr>
            <td><input type="checkbox" name="check" value="<%=cp.getLoginname()%>" /></td>
	   		<td><%=cp.getFirstName()%></td>
			<td><%=cp.getLastName()%></td>
	    	<td><%=cp.getBirthDate()%></td>
			<td><%=cp.getCity()%></td>
	    	<td><%=cp.getState()%></td>
	    	<td><%=cp.getCountry()%></td>
			<td><%=cp.getPhoneNo()%></td>
	    	<td><%=cp.getEmail()%></td>
	    	<td><%=cp.getLoginname()%></td>
	    	</tr>
	    	<%}%> 
            <tr>
            <td colspan=10><div align="center"><input type="submit" value="Delete" name="delete" /></div></td></tr>
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
