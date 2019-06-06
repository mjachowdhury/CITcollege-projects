<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@page import="com.dts.dae.model.CustomerProfile"%>
<%@page import="com.dts.dae.dao.LoginCheckDAO"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>My JSP 'LoginAction.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	--><!-- 
	<script type="javascript">
	function validate()
			{
			   var temp = document.register;
			   if(temp.username.value=="" || temp.password.value=="")
				{
				   alert("User name and password must be required");
				    return false;
   			    }
				</script> -->

	</head>

	<body>
		<br>
		<%
	String target = "LoginAction.jsp?status=Invalid username and password"; 
			try
			{
				CustomerProfile cp = new CustomerProfile();
				String username = request.getParameter("username");
				cp.setLoginname(username);
				String password = request.getParameter("password");
				cp.setPassword(password);
				
					
				if (username.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin"))
				{
					target = "AdminHome.jsp?status=Welcome " + username;
					session.setAttribute("user", username);
				}
				else
				{
					LoginCheckDAO lcd=new LoginCheckDAO();
					boolean flag=lcd.loginCheck(username,password);
					if(flag)
					{
						target = "CustomerHome.jsp?status=Welcome " + username;
						session.setAttribute("user", username);
					}
					else
					{
						target = "LoginForm.jsp?status=Invalid username and password";
						//response.sendRedirect("Registerform.jsp");
					}
				}
			}
			catch (Exception e)
			{
			}
			RequestDispatcher rd = request.getRequestDispatcher(target);
			rd.forward(request, response);
		%>
	</body>
</html>
