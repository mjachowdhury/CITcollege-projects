
<%@ page language="java"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
  <head>
    <html:base />
    
    <title>bookticket.jsp</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
  </head>
  
  <body><center>
  <font color="#FF9900" size="4">TicketForm</font><br><br>
    
<html:form action="bookAg1">
BusNumber<html:text property="busno" readonly="true"/><br><br>
TicketId<html:text property="tickid" readonly="true"/><br><br><br>

Date Of Jouney
<html:select property="bdday">
<html:options property="day"/>
</html:select>&nbsp;&nbsp;
<html:select property="bdmonth">
<html:options property="month"/>
</html:select>
<html:select property="bdyear">
<html:options property="year"/>
</html:select><br><br>
UserName<html:select  property="username1">
<html:options property="username"/>
</html:select><br><br>

No_Of_Tickets
<html:select property="not">
<html:options property="not1"/>											
</html:select>				
<html:submit value="BookTicket"> </html:submit>	
</html:form>
</center>
  </body>
</html:html>
