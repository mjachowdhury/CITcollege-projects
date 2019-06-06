<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<html>
 <head>
 </head>
 <%String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<body background="<%=path%>/images/shade1.gif">
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
   <td><font size=4><c:out value="${requestScope.passportcer.bcid}" /></font></td>
  </tr>
  <tr>
   <td><font size=4>First Name</td>
   <td><font size=4>:</font></td>
   <td><font size=4><c:out value="${requestScope.passportcer.fname}" /></font></td>
  </tr>
  <tr>
  <td><font size=4>Last Name</font></td>
  <td><font size=4>:</font></td>
  <td><font size=4><c:out value="${requestScope.passportcer.lname}" /></font></td>
  </tr>
  <tr>
  <td><font size=4>Spouse Name</font></td>
  <td><font size=4>:</font></td>
  <td><font size=4><c:out value="${requestScope.passportcer.sname}" /></font></td>
  </tr>
  <tr>
  <td><font size=4>Father Name</font></td>
  <td><font size=4>:</font></td>
  <td><font size=4><c:out value="${requestScope.passportcer.father}" /></font></td>
  </tr>
  <tr>
  <td><font size=4>Mother Name</font></td>
  <td><font size=4>:</font></td>
  <td><font size=4><c:out value="${requestScope.passportcer.mname}"/></font></td>
  </tr>
  <tr>
  <td><font size=4>ResidentailAddress</font></td>
  <td><font size=4>:</font></td>
  <td><font size=4><c:out value="${requestScope.passportcer.radd}"/></font></td>
  </tr>
  <tr>
  <td><font size=4>Perminent Address</font></td>
  <td><font size=4>:</font></td>
  <td><font size=4><c:out value="${requestScope.passportcer.padd}"/></font></td>
  </tr>
  <tr>
  <td><font size=4>Date OF Birth</font></td>
  <td><font size=4>:</font></td>
  <td><font size=4><c:out value="${requestScope.passportcer.dob}"/></font></td>
  </tr>
  <tr>
  <td><font size=4>Sex</font></td>
  <td><font size=4>:</font></td>
  <td><font size=4><c:out value="${requestScope.passportcer.sex}"/></font></td>
  </tr>
  <tr>
  <td><font size=4>Birth Place</font></td>
  <td><font size=4>:</font></td>
  <td><font size=4><c:out value="${requestScope.passportcer.bplace}" /></font></td>
  </tr>
  <tr>
  <td><font size=4>Telephone No.</font></td>
  <td><font size=4>:</font></td>
  <td><font size=4><c:out value="${requestScope.passportcer.telno}" /></font></td>
  </tr>
  <tr>
  <td><font size=4>Mobile Place</font></td>
  <td><font size=4>:</font></td>
  <td><font size=4><c:out value="${requestScope.passportcer.mobileno}" /></font></td>
  </tr>
  <tr>
  <td><font size=4>Email ID</font></td>
  <td><font size=4>:</font></td>
  <td><font size=4><c:out value="${requestScope.passportcer.emailid}" /></font></td>
  </tr>
  <tr>
  <td><font size=4>Qualification</font></td>
  <td><font size=4>:</font></td>
  <td><font size=4><c:out value="${requestScope.passportcer.qul}" /></font></td>
  </tr>
  <tr>
  <td><font size=4>Profession ID</font></td>
  <td><font size=4>:</font></td>
  <td><font size=4><c:out value="${requestScope.passportcer.prof}" /></font></td>
  </tr>
  <tr>
  <td><font size=4>Identification Marks</font></td>
  <td><font size=4>:</font></td>
  <td><font size=4><c:out value="${requestScope.passportcer.id}" /></font></td>
  </tr>
  <tr>
  <td><font size=4>Height</font></td>
  <td><font size=4>:</font></td>
  <td><font size=4><c:out value="${requestScope.passportcer.height}" /></font></td>
  </tr>
</table>
 </center>

</body>
</html>