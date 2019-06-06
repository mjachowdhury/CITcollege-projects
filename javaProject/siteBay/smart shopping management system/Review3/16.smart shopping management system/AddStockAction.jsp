<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@page import="com.dts.dae.dao.StockDAO"%>
<%@page import="com.dts.dae.model.StockBean"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>My JSP 'RegisterAction.jsp' starting page</title>

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
			String target = "AddStock.jsp?regs='Stock addtion Failed'";
			try {
				StockBean sb = new StockBean();
				//sb.setProductid(request.getParameter("pid"));
				sb.setProductname(request.getParameter("pname"));
				sb.setPrice(request.getParameter("price"));				
		//		sb.setStock(request.getParameter("stock"));
		        sb.setCategory(request.getParameter("category"));
								
				//sb.setLocale(request.getLocale().toString()); 
				boolean flag = new StockDAO().insertStock(sb);

				if (flag)
					target = "AdminHome.jsp?regs=Stock addtion Success";
				else
					target = "AddStock.jsp?regs=Stock addtion Failed";
			} catch (Exception e) {
			}
			RequestDispatcher rd = request.getRequestDispatcher(target);
			rd.forward(request, response);
		%>
	</body>
</html>
