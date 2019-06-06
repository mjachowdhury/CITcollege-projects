
<%@ page language="java" import="java.util.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Card.jsp' starting page</title>
    
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
  <form action="card.do" >
  <CENTER><br><br> SELECTED TICKET COST = 500 RS(INR)
          <br><br>
            
    <H1> ENTER UR CARD NUMBER</H1>
    <input type="text" name="cno"/>
    <input type="submit" value="submit"/>
    </CENTER>

          <br>
          <a href="Userlogin.do">Back</a>
          </form>
  </body>
</html>
