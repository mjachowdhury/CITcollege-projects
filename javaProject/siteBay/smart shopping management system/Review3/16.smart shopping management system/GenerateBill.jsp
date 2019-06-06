<%@page import="com.dts.dae.dao.GenerateBillDAO"%>
<%@page import="com.dts.core.util.CoreHash"%>
<%@page import="com.dts.dae.model.GenerateBillBean"%>
<%@page import="java.util.*"%>
<%@page import="com.dts.dae.dao.StockDAO"%>
<%@page import="com.dts.dae.model.StockBean"%>
<%
  String sStatus = (String)request.getParameter("status");
  String user=(String)session.getAttribute("user");
  GenerateBillDAO gbdao = new GenerateBillDAO();
  GenerateBillBean gbbean=new GenerateBillBean();
  gbbean.setCustomername(user);
  CoreHash ch = gbdao.generateBill(gbbean);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Bill Generation Page</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="default.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<body>
<!-- start header -->
<div id="logo">
	<h1><jsp:include page="header.html"/></h1>
	</div>
<div id="menu">
	<ul>
		<jsp:include page="customeroptions.html"/> 
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
		<form id="form1" method="post" action="GenerateBillAction.jsp">
            <fieldset>
        	<legend>TotalCart Bill </legend>
            <table align="center" width="350" border="1">
            <tr>
            <th>ProductName</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Total</th>
            </tr>
           <% 
            int i=0;
            long[] arr=new long[100];
            StockDAO sdao=new StockDAO();
            StockBean stkb=new StockBean();
            Enumeration enumeration = ch.elements();
	        while(enumeration.hasMoreElements()) 
		    {
		    	 gbbean = (GenerateBillBean)enumeration.nextElement();

		   	%>	
            <tr>
	   		<td><%=gbbean.getProductname()%></td>
			<td><%=gbbean.getPrice()%></td>
	    	<td><%=gbbean.getQuantity()%></td>
	    	<%
	    	stkb= new StockDAO().getAvailableStockDetails(gbbean.getProductname());
			//	Enumeration en = ch1.elements();
				String available = "";
			//	while (en.hasMoreElements()) {
			//		stkb = (StockBean) en.nextElement();
					available = stkb.getAvailablestock();
			//	}
				String cartqunatity=gbbean.getQuantity();
			int	smstock=Integer.parseInt(available) - Integer.parseInt(cartqunatity);
	    	new StockDAO().updateQuantity(gbbean.getProductname(),""+smstock);
	    	int price=Integer.parseInt(gbbean.getPrice());
	    	int quantity=Integer.parseInt(gbbean.getQuantity());
	    	long total=price*quantity;
	    	arr[i]=total;
	    	i++;
	    	%>
	    	<td align="right"><%=total%></td>
	    	</tr>
	    	<%}
	    	long grandtotal=0;
	    	for(i=0;i<arr.length;i++)
	    	{
	    		grandtotal=grandtotal+arr[i];
	    	}
	    	%>
	    	
	    	<tr><td colspan="3" align="right">Total Price :</td><td align="right"><%=grandtotal%></td></tr>
	        <tr><td colspan="4"><input name="print" type="button" value="Print Report" onclick="javascript:window.print();"/>
	       <!-- <input name="Buy" type="submit" value="BuyProducts"/>-->
	        </td>
	        </tr>
	        <input type="hidden" name="grandtotal" value="<%=grandtotal%>"/>
	        
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
</html>
