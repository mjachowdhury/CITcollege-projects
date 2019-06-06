
<%@ page language="java"%>
<%@ page import="java.util.*"%>
<%@ page import="eticket.*"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
  <head>
    <html:base />
    
    <title>Mail1.jsp</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
<form action="sendMaila.do">
<center><b><font color="#FF9900" size="5"><u>Send Mails</u></font></b><br><br>

</center>
   <b>
 &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspTo&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<select name="to">
   
   <%! String temp1;%>
   <% Vector v=new Vector();
   HttpSession hs=request.getSession();
   v=(Vector)hs.getAttribute("maila");
    temp1=(String)hs.getAttribute("froma");
   Iterator i=v.iterator();  
   while(i.hasNext()){
   String temp=(String)i.next();
   %>
<option><%=temp%></option>
<%}%>

   </select><br><br>
 &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspFrom:&nbsp
    &nbsp&nbsp&nbsp&nbsp<input type="text" name="from" size="40"value="<%=temp1%>"><br><br>
  &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspSub:&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
     <input type="text" name="sub" size="40" ><br><br>
 &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspDesc:&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<br>
&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp <textarea name="desc" rows="10" cols="40">
   </textarea><br><br>
  
    
 &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<input type="submit" value="SendMail">
   
   
    </form>
   
   
   </body>
</html:html>
