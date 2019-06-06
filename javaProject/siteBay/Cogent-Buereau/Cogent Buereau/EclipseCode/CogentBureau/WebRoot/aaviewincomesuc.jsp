<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ page import="org.district.IncomeCer"%>
<html>
 <head>
 </head>
 <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
 <%IncomeCer br=(IncomeCer)request.getAttribute("incomecer");%>
<body background="<%=path%>/images/shade1.gif">
 <center>
 <table border="0" cellspacing="0" cellpadding="0">
  <tr>
  <td>
  <center><font size=5><u>Approved Income Certificate</u></font></center>
 </td>
 </table>
<table width="30%" border="0" cellspacing="5" cellpadding="0" size=4>
  <tr>
   <td>
   <font size=4 >Certificate ID</font></td>
   <td><font size=4 >:</font></td>
   <td><font size=4><%=br.getIcid()%></font></td>
  </tr>
  <tr>
   <td><font size=4>First Name</td>
   <td><font size=4>:</font></td>
   <td><font size=4><%=br.getFname()%><font></td>
  </tr>
  <tr>
  <td><font size=4>Last Name</font></td>
  <td><font size=4>:</font></td>
  <td><font size=4><%=br.getLname()%></font></td>
  </tr>
  <tr>
  <td><font size=4>Father Name</font></td>
  <td><font size=4>:</font></td>
  <td><font size=4><%=br.getFather()%></font></td>
  </tr>
  <tr>
  <td><font size=4>ResidentailAddress</font></td>
  <td><font size=4>:</font></td>
  <td><font size=4><%=br.getRadd()%></font></td>
  </tr>
  <tr>
  <td><font size=4>Perminent Address</font></td>
  <td><font size=4>:</font></td>
  <td><font size=4><%=br.getPadd()%></font></td>
  </tr>
  <tr>
  <td><font size=4>Date Of  Birth</font></td>
  <td><font size=4>:</font></td>
  <td><font size=4><%=br.getDob()%></font></td>
  </tr>
  <tr>
  <td><font size=4>Qualification</font></td>
  <td><font size=4>:</font></td>
  <td><font size=4><%=br.getqual()%></font></td>
  </tr>
 <tr>
  <td><font size=4>Occupation</font></td>
  <td><font size=4>:</font></td>
  <td><font size=4><%=br.getOccup()%></font></td>
  </tr>
<tr>
<tr>
  <td><font size=4>Purpose of Certificate</font></td>
  <td><font size=4>:</font></td>
  <td><font size=4><%=br.getPurpose()%></font></td>
  </tr>
<tr>
  <td><font size=4>Dateof Registration</font></td>
  <td><font size=4>:</font></td>
  <td><font size=4><%=br.getDor()%></font></td>
  </tr>
<tr>
  <td><font size=4>Income</font></td>
  <td><font size=4>:</font></td>
  <td><font size=4><%=br.getIncome()%></font></td>
  </tr>
</table>

  </center>

</body>
</html> 