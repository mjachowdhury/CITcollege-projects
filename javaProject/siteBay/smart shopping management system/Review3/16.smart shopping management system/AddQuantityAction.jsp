<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@page import="com.dts.dae.dao.StockDAO"%>
<%@page import="com.dts.dae.model.StockBean"%>
<%@page import="com.dts.core.util.CoreHash"%>
<%@page import="java.util.Enumeration"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>My JSP 'AddQuantity Action.jsp' starting page</title>

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
			StockDAO sdao = new StockDAO();
			StockDAO sdao1 = new StockDAO();
			String target = "AddQuantity.jsp?regs=Quantity addtion Failed";
			try {
				StockBean stkb = new StockBean();
				stkb.setProductid(Integer.parseInt(request
						.getParameter("products")));
				String quantityaddition = request.getParameter("quantity");
				System.out.println("**************"+Integer.parseInt(request
						.getParameter("products"))+"**********");
				
				CoreHash ch = sdao.getProductDetails(Integer.parseInt(request
						.getParameter("products")));
				Enumeration en = ch.elements();
				String available = "";
				while (en.hasMoreElements()) {
					stkb = (StockBean) en.nextElement();
					available = stkb.getAvailablestock();
				}
				int totalquantity = Integer.parseInt(quantityaddition)
						+ Integer.parseInt(available);
				stkb.setAvailablestock("" + totalquantity);
				boolean flag = sdao1.addQuantity(Integer.parseInt(request
						.getParameter("products")), "" + totalquantity);

				if (flag)
					target = "AddQuantity.jsp?regs=Quantity addtion Success";
				else
					target = "AddQuantity.jsp?regs=Quantity addtion Failed";
			} catch (Exception e) {
			}
			RequestDispatcher rd = request.getRequestDispatcher(target);
			rd.forward(request, response);
		%>
	</body>
</html>
