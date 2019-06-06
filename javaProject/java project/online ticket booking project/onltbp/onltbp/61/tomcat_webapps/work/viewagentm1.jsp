
<%@ page language="java"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
  <head>
    <html:base />
    
    <title>view1.jsp</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
  <form action="delMaila.do">
  
  &nbsp  &nbsp  &nbsp  &nbsp  &nbsp  &nbsp  &nbsp  &nbsp  &nbsp  &nbsp  &nbsp  &nbsp
  
  <b><font color="#FF9900" size="5"><center><u>Mails</u></font></b><br><br>
  <table border="+1">
  <tr>
  <th width="5%">    </th>
  <th width="37%"><center>Subject:</th>
  <th width="38%"><center>From:</th>
  <th width="20%"><center>Date:</th>
  </tr>
  <logic:iterate id="vmail" name="vmaila">
  <tr>
  <td><input  type="checkbox" name="ch" value="<bean:write name="vmail" property="id"/>">
  </td>

  <td><center>
  <a href="viewdesc2.jsp?desc=<bean:write name="vmail" property="desc"/>"/>
  <bean:write name="vmail" property="sub"/></a></td>
   
  <td><center><bean:write name="vmail" property="from"/></td>
  <td><center><bean:write name="vmail" property="per"/></td>
  
  </tr>
   </logic:iterate>
   
  </table>
      <br>
   
   
   &nbsp&nbsp&nbsp&nbsp&nbsp   <html:submit value="Delete"></html:submit>
      </form>
  </body>
  </html:html>
