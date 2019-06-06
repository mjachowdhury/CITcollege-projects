<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@page import="com.dts.dae.dao.StockDAO"%>
<%@page import="com.dts.core.util.CoreHash"%>
<%@page import="com.dts.dae.model.StockBean"%>
<%@page import="java.util.*"%>
<%
  StockDAO sdao = new StockDAO();
  CoreHash ch = sdao.getStockDetails();
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>ViewStock Details Page</title>
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
 			return confirm("Do you want to really delete the Stock?");
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
			<ul><form id="list" name="listform" method="post" action="DeleteStockAction.jsp"  onsubmit="return validatePage()">
			<%
	        if(ch!=null && ch.size()>0)
		    { 		
            %>
				   <fieldset>
                   <legend>Stock Details </legend>
            <table align="center" width="245" border="1">
            <tr>
            <th>select</th>
            <th>ProductId</th>
            <th>ProductName</th>
            <th>Price</th>
            <th>Category</th>
            </tr>
			<!--<tr><input type="checkbox" name="checkall" /></tr>-->
           <% Enumeration enumeration = ch.elements();
	        while(enumeration.hasMoreElements()) 
		    {
		    	StockBean sb = (StockBean)enumeration.nextElement();
		   	%>	
            <tr>
            <td><input type="checkbox" name="check" value="<%=sb.getProductid()%>" /></td>
	   		<td><%=sb.getProductid()%></td>
			<td><%=sb.getProductname()%></td>
	    	<td><%=sb.getPrice()%></td>
	    	<td><%=sb.getCategory()%></td>
	    	</tr>
	    	<%}%> 
            <tr><td colspan=3><div align="center"><input type="button" value="Add New" onclick="javascript:location.href='AddStock.jsp'"/></div></td>
            <td colspan=2><div align="center"><input type="submit" value="Delete" name="delete" /></div></td></tr>
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
