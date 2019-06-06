

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
<u>Birth Certificate</u>
</font>
<form method="get" action="storebirthaction.do" target="main">
<table>
 <tr><td><html:errors/></td></tr>
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
  <td><font size=4 >Mother Name</td>
  <td><font size=4 >:</td>
  <td><input type="text" name="mname"></td>
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
  <td><font size=4>Date Of Birth</td>
  <td><font size=4>:</td>
  <td><input type="text" name="dob">(yyyy-mm-dd)</td>
  <td><font size=4>Sex</td>
  <td><font size=4>:</td>
  <td><input type="radio" name="sex" value="male"><font size=4>Male</font>
 <input type="radio" name="sex" value="female"><font size=4>Female</font></td>
 </tr>
  <tr>
  <td><font size=4>Caste</td>
  <td><font size=4>:</td>
  <td><input type="text" name="caste"></td>
 <td><font size=4>Birth Place</td>
  <td><font size=4>:</td>
  <td><input type="text" name="bplace"></td>
 </tr>
 <tr>
  <td><font size=4>Dr. Name</td>
  <td><font size=4>:</td>
  <td><input type="text" name="drname"></td>
  <td><font size=4>Father Occupation</td>
  <td><font size=4>:</td>
  <td><input type="text" name="foccup"></td>
 </tr>
  <tr>
  <td><font size=4>Hospital Name</td>
  <td><font size=4>:</td>
  <td><input type="text" name="hname"></td>
 </tr>
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

 