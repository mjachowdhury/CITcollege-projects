

<html>
 <head>
<title>
   District Collectorate Management System
</title>
 </head>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<body background="<%=path%>/images/shade1.gif">
<center>
<font size=4 >
<u>Caste Certificate</u>
</font>
<form method="get" action="storecasteaction.do" target="main">
<table>
 <tr>
  <td><font size=4 >First Name</td>
  <td><font size=4 >:</td>
  <td><input type="text" name="first"></td>
  <td><font size=4 >Last  Name</td>
  <td><font size=4 >:</td>
  <td><input type="text" name="last"></td>
 </tr>
  <tr>
  <td><font size=4 >Father Name</td>
  <td><font size=4 >:</td>
  <td><input type="text" name="fname"></td>
  <td><font size=4>Date Of Birth</td>
  <td><font size=4>:</td>
  <td><input type="text" name="dob">(yyyy-mm-dd)</td>
 </tr>
 <tr>
  <td><font size=4 >Residential Address</td>
  <td><font size=4 >:</td>
  <td><textarea name="resadd"></textarea></td>
  <td><font size=4 >Perminent Address</td>
  <td><font size=4 >:</td>
  <td><textarea name="peradd"></textarea></td>
 </tr>
 <tr>
   <td><font size=4>Sex</td>
  <td><font size=4>:</td>
  <td><input type="radio" name="sex" value="male"><font size=4>Male</font>
  <input type="radio" name="sex" value="female"><font size=4>Female</font></td>
   <td><font size=4>Caste</td>
  <td><font size=4>:</td>
  <td><input type="text" name="caste"></td>
 </tr>
  <tr> 
   <td><font size=4>Relegion</td>
  <td><font size=4>:</td>
  <td><input type="text" name="religion"></td>
    <td><font size=4>Occupation</td>
    <td><font size=4>:</td>
   <td><input type="text"  name="occup"></td>
 </tr>
 <tr>
    <td><font size=4>Income</td>
    <td><font size=4>:</td>
   <td><input type="text" name="income"></td>  
   <td><font size=4>CertificatePurpose</td>
  <td><font size=4>:</td>
  <td><input type="text" name="purpose"></td>

</table>
</center>
<br>
<br>
<table align="center">
 <tr>
  <td>
  <input type="submit" value="submit">
  </td>
  <td>
  <input type="reset" value="cancel">
  </td>
 </tr>
</table>
</body>
</html>

 