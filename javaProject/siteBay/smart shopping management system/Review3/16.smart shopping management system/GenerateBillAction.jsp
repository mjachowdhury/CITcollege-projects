<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@page import="com.dts.dae.dao.GenerateBillDAO"%>
<%@page import="com.dts.dae.model.GenerateBillBean"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>My JSP 'GenerateBillAction.jsp' starting page</title>

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
			String target = "GenerateBill.jsp?status=Sorry "+user+" click buy again !...";
			try {
				GenerateBillBean gbb = new GenerateBillBean();
				
				gbb.setCustomername(user);
				gbb.setGrandtotal(request.getParameter("grandtotal"));
				
								
				//sb.setLocale(request.getLocale().toString()); 
				boolean flag = new GenerateBillDAO().insertProductsBought(gbb);

				if (flag)
				{
					target = "GenerateBill.jsp?status=Thank you "+user+" visit again";
					//GenerateBillDAO gbd=new GenerateBillDAO();
					//gbd.updateShoppingcartStatus(user)
				}	
				else
					target = "GenerateBill.jsp?status=Sorry "+user+" click buy again !...";
			} catch (Exception e) {
			}
			RequestDispatcher rd = request.getRequestDispatcher(target);
			rd.forward(request, response);
		%>
	</body>
</html>
