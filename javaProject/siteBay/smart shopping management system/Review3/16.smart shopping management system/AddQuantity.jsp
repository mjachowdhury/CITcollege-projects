<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@page import="com.dts.dae.dao.StockDAO"%>
<%@page import="com.dts.core.util.CoreHash"%>
<%@page import="com.dts.dae.model.StockBean"%>
<%@page import="java.util.*"%>
<%
	try{
	String sStatus = (String)request.getParameter("regs");
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>AddQuantity Page</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="default.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<script language="JavaScript">

function submitQuery(name)
{
	//alert("Inside submitQuery():"+name);
	var category = document.getElementById("products").value;
	
	var paraString = "";
	if(name=="products")
	{
		paraString = "products="+products;
	}
	location.href="PurchaseProducts.jsp?"+paraString;	
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
<div id="latest-post">
<ul>
	<center><h2><%=sStatus==null?"":sStatus%></h2></center>
	  <form name="form1" method="post" action="AddQuantityAction.jsp">
	  <fieldset>
      <legend>AddProducts Quantity</legend>
                   
            <table width="245" height="102" border="0" align="center">
            
            <tr>
            <th><div align="justify">ProducyName</div></th>
            <%
 			  StockDAO sdao = new StockDAO();
 			  CoreHash ch = new CoreHash();
 			  	ch = sdao.getProductDetails();
			%>
			<td><SELECT name="products" ><option>[Select Product]</option>
			<% Enumeration enumeration1 = ch.elements();
	        while(enumeration1.hasMoreElements()) 
		    {
		    	StockBean sb = (StockBean)enumeration1.nextElement();
		   	%>	
		    <OPTION value="<%=sb.getProductid()%>">
			<%=sb.getProductname()%></OPTION>
			<%} %>
			</SELECT></td>
	    	</tr>
	    	<tr height="20"><td></td></tr>
	    	<tr>
            <th><div align="justify">Quantity</div></th>
            <td><input type="text" name="quantity"/></td>
            </tr>
	    	<tr><td height="20"></td></tr>
            <tr><td colspan="2" align="center"><input name="addquantity" type="submit" value="AddQuantity"/></td></tr>
           </table>
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
<%
	}catch(Exception e){e.printStackTrace();}
%>
</html>
