<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ page
	import="com.dts.dae.dao.ProfileDAO,com.dts.dae.dto.RegistrationBean,com.dts.dae.dao.SecurityDAO"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'ChangepasswordAction.jsp' starting page</title>
    
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
  <%
   		RegistrationBean rb = new RegistrationBean();
        rb.setPassword(request.getParameter("oldpassword"));
        rb.setLoginID(request.getParameter("username"));
        rb.setNewPassword(request.getParameter("newpassword"));
        
        boolean flag=new SecurityDAO().changePassword(rb);
        String pathstring="LoginForm.jsp";

		//|| ((String)session.getAttribute("role")).equals("user"))

        if(((String)session.getAttribute("role")).equals("admin"))
          pathstring="adminhome.jsp";
		  else
	            pathstring="userhome.jsp";		  
        
       String target="Changepassword.jsp?status=Change Failed"; 
        if(flag) 
           target = pathstring+"?status=Password Successfully changed"; 
        else  
            //target = "adminhome.jsp?status=Change Failed"; 
			target = "pathstring?status=Change Failed"; 
			
        RequestDispatcher rd = request.getRequestDispatcher(target);
        rd.forward(request,response);    
  %>
  </body>
</html>
