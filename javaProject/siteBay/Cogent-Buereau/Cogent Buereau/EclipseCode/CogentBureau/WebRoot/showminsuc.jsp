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
  <center><font size=5><u>Minority Certificate</u></font></center>
 </td>
 </table>
<form  method="get" action="issuemincer.do">
<table width="30%" border="0" cellspacing="5" cellpadding="0" size=4>
  <tr>
   <td>
   <font size=4 >Certificate ID</font></td>
   <td><font size=4 >:</font></td>
   <td><font size=4><c:out value="${requestScope.mincer.mcid}" /></font></td>
  </tr>
  <tr>
   <td><font size=4>First Name</td>
   <td><font size=4>:</font></td>
   <td><font size=4><c:out value="${requestScope.mincer.fname}" /></font></td>
  </tr>
  <tr>
  <td><font size=4>Last Name</font></td>
  <td><font size=4>:</font></td>
  <td><font size=4><c:out value="${requestScope.mincer.lname}" /></font></td>
  </tr>
  <tr>
  <td><font size=4>Father Name</font></td>
  <td><font size=4>:</font></td>
  <td><font size=4><c:out value="${requestScope.mincer.father}" /></font></td>
  </tr>
  <tr>
  <td><font size=4>ResidentailAddress</font></td>
  <td><font size=4>:</font></td>
  <td><font size=4><c:out value="${requestScope.mincer.radd}"/></font></td>
  </tr>
  <tr>
  <td><font size=4>Perminent Address</font></td>
  <td><font size=4>:</font></td>
  <td><font size=4><c:out value="${requestScope.mincer.padd}"/></font></td>
  </tr>
  <tr>
  <td><font size=4>Date Of  Birth</font></td>
  <td><font size=4>:</font></td>
  <td><font size=4><c:out value="${requestScope.mincer.dob}"/></font></td>
  </tr>
  <tr>
  <td><font size=4>Sex</font></td>
  <td><font size=4>:</font></td>
  <td><font size=4><c:out value="${requestScope.mincer.sex}"/></font></td>
  </tr>
 <tr>
  <td><font size=4>Religion</font></td>
  <td><font size=4>:</font></td>
  <td><font size=4><c:out value="${requestScope.mincer.religion}" /></font></td>
  </tr>
 <tr>
  <td><font size=4>Occupation</font></td>
  <td><font size=4>:</font></td>
  <td><font size=4><c:out value="${requestScope.mincer.occup}" /></font></td>
  </tr>
<tr>
<tr>
  <td><font size=4>Purpose of Certificate</font></td>
  <td><font size=4>:</font></td>
  <td><font size=4><c:out value="${requestScope.mincer.purpose}" /></font></td>
  </tr>
<tr>
  <td><font size=4>Income</font></td>
  <td><font size=4>:</font></td>
  <td><font size=4><c:out value="${requestScope.mincer.income}" /></font></td>
  </tr>
<tr>
  <td><font size=4>Dateof Registration</font></td>
  <td><font size=4>:</font></td>
  <td><font size=4><c:out value="${requestScope.mincer.dor}" /></font></td>
  </tr>
</table>
<br>
<br>
<br>
<hr>
<table>
 <tr>
  <td><h3> For Office Use Only</h3></td>
 </tr>
 <tr>
  <td>Approve the certificate</td>
  <td>:</td>
  <td> <input type="radio" name="result" value="approve"><font size=4>Approve</font>
 <input type="radio" name="result" value="decline"><font size=4>Decline</font></td>
</tr>
<tr> <td><br><br></td></tr>
<tr align="center">
  <td><input type="submit" value="Issue"></td>
</tr>
</table>
  </center>

</body>
</html>