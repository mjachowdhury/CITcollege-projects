<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@page import="com.dts.dae.model.CustomerProfile"%>
<%@page import="com.dts.dae.dao.CustomerDAO"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

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
			String target = "Registerform.jsp?regs=Registration Failed";
			try {
				CustomerProfile cp = new CustomerProfile();
				cp.setFirstName(request.getParameter("fname"));
				cp.setLastName(request.getParameter("lname"));
				cp.setBirthDate(request.getParameter("bdate"));
				cp.setHno(request.getParameter("hno"));
				cp.setStreet(request.getParameter("street"));
				cp.setCity(request.getParameter("city"));
				cp.setState(request.getParameter("state"));
				cp.setCountry(request.getParameter("country"));
				cp.setPincode(request.getParameter("pincode"));
				cp.setPhoneNo(request.getParameter("phoneno"));
				cp.setEmail(request.getParameter("email"));
				cp.setLoginname(request.getParameter("loginname"));
				cp.setPassword(request.getParameter("password"));
				cp.setOwnSecretQuestion(request.getParameter("squest"));
				cp.setSecretAnswer(request.getParameter("sanswer"));
				cp.setCreditcardNo(request.getParameter("creditcardno"));

				cp.setLocale(request.getLocale().toString()); 
				boolean flag = new CustomerDAO().registration(cp);

				if (flag)
					target = "LoginForm.jsp?ls=Registration Success";
				else
					target = "Registerform.jsp?regs=Registration Failed";
			} catch (Exception e) {
			}
			RequestDispatcher rd = request.getRequestDispatcher(target);
			rd.forward(request, response);
		%>
	</body>
</html>
