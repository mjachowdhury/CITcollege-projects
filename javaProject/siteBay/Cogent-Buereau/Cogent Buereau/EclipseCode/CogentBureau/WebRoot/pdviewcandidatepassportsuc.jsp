<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ page import="org.district.PassportCer"%>
<html>
 <head>
 </head>
 <%String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%><%PassportCer br=(PassportCer)request.getAttribute("passportcer");%>
<body background="<%=path%>/images/shade1.gif">
<form name="pform" method="get" action="decidePolice.do">
 <center>
 <table border="0" cellspacing="0" cellpadding="0">
  <tr>
  <td>
  <center><font size=5><u> Passport Certificate</u></font></center>
 </td>
 </table>
<table width="30%" border="0" cellspacing="5" cellpadding="0" size=4>
 <tr>
  <td>
   <font size=4 >Certificate ID</font></td>
   <td><font size=4 >:</font></td>
   <td><font size=4><%=br.getBcid()%></font></td>
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
  <td><font size=4>Spouse Name</font></td>
  <td><font size=4>:</font></td>
  <td><font size=4><%=br.getSname()%></font></td>
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
  <td><font size=4>Date OF Birth</font></td>
  <td><font size=4>:</font></td>
  <td><font size=4><%=br.getDob()%></font></td>
  </tr>
  <tr>
  <td><font size=4>Sex</font></td>
  <td><font size=4>:</font></td>
  <td><font size=4><%=br.getSex()%></font></td>
  </tr>
  <tr>
  <td><font size=4>Birth Place</font></td>
  <td><font size=4>:</font></td>
  <td><font size=4><%=br.getBplace()%></font></td>
  </tr>
  <tr>
  <td><font size=4>Telephone No.</font></td>
  <td><font size=4>:</font></td>
  <td><font size=4><%=br.getTelno()%></font></td>
  </tr>
  <tr>
  <td><font size=4>Mobile Place</font></td>
  <td><font size=4>:</font></td>
  <td><font size=4><%=br.getMobileno()%></font></td>
  </tr>
  <tr>
  <td><font size=4>Email ID</font></td>
  <td><font size=4>:</font></td>
  <td><font size=4><%=br.getEmailid()%></font></td>
  </tr>
  <tr>
  <td><font size=4>Qualification</font></td>
  <td><font size=4>:</font></td>
  <td><font size=4><%=br.getQul()%></font></td>
  </tr>
  <tr>
  <td><font size=4>Profession ID</font></td>
  <td><font size=4>:</font></td>
  <td><font size=4><%=br.getProf()%></font></td>
  </tr>
  <tr>
  <td><font size=4>Identification Marks</font></td>
  <td><font size=4>:</font></td>
  <td><font size=4><%=br.getId()%></font></td>
  </tr>
  <tr>
  <td><font size=4>Height</font></td>
  <td><font size=4>:</font></td>
  <td><font size=4><%=br.getHeight()%></font></td>
  </tr><br><br>
</table>
 </center>
</form>
</body>
</html>