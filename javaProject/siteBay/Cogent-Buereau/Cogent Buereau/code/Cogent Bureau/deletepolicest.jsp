

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
<u>Police Station Information</u>
</font>
<form method="get" name="pform" action="deletepolicestaction.do" onSubmit="return validateAll();" target="main">
<script language="JavaScript">
  function validateAll() {
    if(document.pform.pk.length==0)  {
	  alert("ID must be select");
	  return false;
	}
	return true;
  }
</script>
<table>
 <tr><td><html:errors/></td></tr>
 <tr>
  <td><font size=4 >Police Station ID</td>
  <td><font size=4 >:</td>
  <td><select name="pk" size="1">
  <%   try
   {
      
      java.sql.Connection con=java.sql.DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","distcol","distcol");
      java.sql.Statement stmt=con.createStatement();
	  String sqlSTMT="select pk,pname from policest";
      java.sql.ResultSet rs=stmt.executeQuery(sqlSTMT);
	  while(rs.next()) {
	  %>
	  <option value='<%=rs.getString(1)%>'><%=rs.getString(1)%>&nbsp;(<%=rs.getString(2)%>)</option>
  <%} con.close();
	  }catch(Exception e) {System.out.println(e); }
  %>
  </select>
  </td>
 </tr>
</table>
</center>
<br>
<br>
<input type="hidden" name="method" value="delete"/>
<table align="center">
 <tr>
  <td>
  <input type="submit" name="submit" value="delete">
  </td>
  <td>
  <input type="reset" value="cancel">
  </td>
 </tr>
</table>

</body>
</html>

 