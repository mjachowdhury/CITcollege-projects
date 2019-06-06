<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ page import="com.dts.dae.model.Profile,com.dts.dae.dao.SecurityDAO,com.dts.core.util.LoggerManager" %>
<%@page import="com.dts.dae.model.CustomerProfile"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'RecoverPasswordAction.jsp' starting page</title>
    
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
  	String target = "Recoverpasswordfprm.jsp?status=Entries not Matched... Try Again";;
      try{
          CustomerProfile cp=new CustomerProfile();
          String loginname=request.getParameter("username");
          String squestion=request.getParameter("squest");
          String sanswer=request.getParameter("sanswer");
          cp.setLoginname(loginname);
          cp.setOwnSecretQuestion(squestion);
          cp.setSecretAnswer(sanswer);
          String password="";
              password=new SecurityDAO().recoverPasswordByExistQuestion(cp);
          if(password.equals("") || password==null)
              target="Recoverpassword.jsp?status=Entries not Matched... Try Again";
          else
             target="LoginForm.jsp?status=Password "+password;
        }
        catch(Exception e)
        {
           LoggerManager.writeLogSevere(e);
        }
        RequestDispatcher rd = request.getRequestDispatcher(target);
        rd.forward(request,response);
  %>
  </body>
</html>
