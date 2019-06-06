<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@page import="com.dts.dae.dao.PurchaseProductsDAO"%>
<%@page import="com.dts.dae.model.PurchaseProductsBean"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'PurchaseProductsAction.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
 <body>
		<br>
		<%
			String user=(String)session.getAttribute("user");
			String target = "PurchaseProducts.jsp?status=adding to cart Failed";
			try {
				PurchaseProductsBean ppb = new PurchaseProductsBean();
				ppb.setPaidStatus("No");
				ppb.setCustomername(user);
				ppb.setProductname((String)request.getParameter("products"));
				ppb.setQuantity((String)request.getParameter("quantity"));
				PurchaseProductsDAO pdao=new PurchaseProductsDAO();	
				boolean flag = pdao.addtoCart(ppb);

				if (flag)
					target = "PurchaseProducts.jsp?status=Item added to the cart successfully";
				else
					target = "PurchaseProducts.jsp?status=adding to cart Failed";
			} catch (Exception e) {
			}
			RequestDispatcher rd = request.getRequestDispatcher(target);
			rd.forward(request, response);
		%>
	</body>
</html>
