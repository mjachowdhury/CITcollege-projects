<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ page import="org.district.DeathCer"%>
<html>
 <head>
 </head>
 <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
 <%DeathCer  br=(DeathCer)request.getAttribute("deathcer");%>
<body background="<%=path%>/images/shade1.gif">
 <form method="get" name="aform" action="decideDeath.do" target="main">
<center>
 <table border="0" cellspacing="0" cellpadding="0">
  <tr>
  <td>
  <center><font size=5><u>Approved Death Certificate</u></font></center>
 </td>
 </table>
<table width="30%" border="0" cellspacing="5" cellpadding="0" size=4>
  <tr>
   <td>
   <font size=4 >Certificate ID</font></td>
   <td><font size=4 >:</font></td>
   <td><font size=4><%=br.getDcid()%></font></td>
  </tr>
  <tr>
   <td><font size=4>First Name</td>
   <td><font size=4>:</font></td>
   <td><font size=4><%=br.getFname()%></font></td>
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
  <td><font size=4>Mother Name</font></td>
  <td><font size=4>:</font></td>
  <td><font size=4><%=br.getMname()%></font></td>
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
  <td><font size=4>Date Of Death</font></td>
  <td><font size=4>:</font></td>
  <td><font size=4><%=br.getDod()%></font></td>
  </tr>
  <tr>
  <td><font size=4>Sex</font></td>
  <td><font size=4>:</font></td>
  <td><font size=4><%=br.getSex()%></font></td>
  </tr>
  <tr>
  <td><font size=4>Caste</font></td>
  <td><font size=4>:</font></td>
  <td><font size=4><%=br.getCast()%></font></td>
  </tr>
  <tr>
  <td><font size=4>DeathPlace</font></td>
  <td><font size=4>:</font></td>
  <td><font size=4><%=br.getDplace()%></font></td>
  </tr>
 <tr>
  <td><font size=4>Dr. Name</font></td>
  <td><font size=4>:</font></td>
  <td><font size=4><%=br.getDrname()%></font></td>
  </tr>
<tr>
  <td><font size=4>Occupation</font></td>
  <td><font size=4>:</font></td>
  <td><font size=4><%=br.getOccup()%></font></td>
  </tr>
<tr>
<tr>
  <td><font size=4>Reason for Death</font></td>
  <td><font size=4>:</font></td>
  <td><font size=4><%=br.getReason()%></font></td>
  </tr>
<tr>
  <td><font size=4>Dateof Registration</font></td>
  <td><font size=4>:</font></td>
  <td><font size=4><%=br.getDor()%></font></td>
  </tr>
<tr>
  <td><font size=4>Hospital</font></td>
  <td><font size=4>:</font></td>
  <td><font size=4><%=br.getHospital()%></font></td>
  </tr>
</table>
  </center>
</form>
</body>
</html>