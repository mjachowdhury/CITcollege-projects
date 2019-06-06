
<%@ page language="java"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
  <head>
    <html:base />
    
    <title>newpasssucC1.jsp</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
<font color="#FF9900" size="5"><b><center><u>Details</u></font><br><br> 
<% HttpSession hs=request.getSession();%></center>   <br>
<pre>
<b>	UserId	:		<%=hs.getAttribute("userid")%>
<br>
   	PassWord :       <%=hs.getAttribute("passc")%>
<br><br><br>
<a href="Userlogin.jsp"><center>Login?</a>
</pre>
  </body>
</html:html>
