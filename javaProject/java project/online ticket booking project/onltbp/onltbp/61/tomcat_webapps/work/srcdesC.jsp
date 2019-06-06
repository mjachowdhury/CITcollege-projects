
<%@ page language="java"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
  <head>
    <html:base />
    
    <title>srcdes.jsp</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
  <center>
  <b><font color="#FF9900" size="3"><u>Choose Source and Destination</u></font></b><br><br>
   <html:form action="viewbusdesC">
SRC<html:select property="name" >
<html:options property="src"/>
</html:select>
Desc<html:select property="name1">
<html:options property="des" />
</html:select>

<br><br><br><br><br>


    <html:submit value="View Buses"></html:submit>
        </html:form>
        </center>
  </body>
</html:html>
