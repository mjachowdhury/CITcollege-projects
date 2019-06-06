<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@page import="com.dts.dae.dao.StaffDAO"%>
<%@page import="com.dts.dae.model.StaffBean"%>
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
			String target = "AddStaff.jsp?regs=Staff addtion Failed";
			try {
				StaffBean sb = new StaffBean();
				//sb.setEmpId(Integer.parseInt(request.getParameter("empid")));
				sb.setEmpName(request.getParameter("empname"));
				sb.setDesignatin(request.getParameter("designation"));
				sb.setDoj(request.getParameter("jdate"));
				sb.setSalary(request.getParameter("salary"));
				sb.setAccno(request.getParameter("accno"));
				sb.setContactAddress(request.getParameter("contactaddress"));
				sb.setPhoneNo(request.getParameter("phoneno"));
				
				if(sb==null)
				{
					out.println("Enter the all things");
				}
				//sb.setLocale(request.getLocale().toString()); 
				boolean flag = new StaffDAO().insertStaff(sb);

				if (flag)
					target = "AdminHome.jsp?regs=Staff addtion Success";
				else
					target = "AddStaff.jsp?regs=Staff addtion Failed";
			} catch (Exception e) {
			}
			RequestDispatcher rd = request.getRequestDispatcher(target);
			rd.forward(request, response);
		%>
	</body>
</html>
