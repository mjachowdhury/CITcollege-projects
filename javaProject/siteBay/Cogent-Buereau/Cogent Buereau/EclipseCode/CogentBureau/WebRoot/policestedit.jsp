<html>
 <head>
<title>
   District Collectorate Management System
</title>
 </head>
<%String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<body background="<%=path%>/images/shade1.gif">
<center>
<font size=4 >
<u>Police Station Information</u>
</font>
<form method="get" action="editpolicestaction.do" target="main">
<table>
 <tr><td><html:errors/></td></tr>
<%	try{
	  System.out.println("---------reset--------");
      
      java.sql.Connection con=java.sql.DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","distcol","distcol");
      java.sql.Statement stmt=null;
	  String sqlSTMT="select pk,pname,addr,login,pwd from policest where pk='"+request.getParameter("pk")+"'";
	  System.out.println(sqlSTMT);
      stmt=con.createStatement();
      java.sql.ResultSet rs=stmt.executeQuery(sqlSTMT);
	  if(rs.next()) {%>
 <tr>
  <td><font size=4 >Police Station ID</td>
  <td><font size=4 >:</td>
  <td><input type="text" name="pk" value='<%=rs.getString(1)%>' size=26 readonly></td>
 </tr>
 <tr>
  <td><font size=4 >Police Station Name</td>
  <td><font size=4 >:</td>
  <td><input type="text" name="pname" value='<%=rs.getString(2)%>' size=26></td>
 </tr>
 <tr>
  <td><font size=4 >Address</td>
  <td><font size=4 >:</td>
  <td><textarea name="addr"><%=rs.getString(3)%></textarea></td>
 </tr>
 <tr>
  <td><font size=4>Login ID</td>
  <td><font size=4>:</td>
  <td><input type="text" name="login" value='<%=rs.getString(4)%>' size=26></td>
 </tr>
 <tr>
  <td><font size=4 >Password</font></td>
  <td><font size=4 >:</td>
  <td><font size=1><input type="text" name="pwd" value='<%=rs.getString(5)%>' size=26></font></td>
 </tr>
</table>
</center>
<br>
<br>
<input type="hidden" name="method" value="edit"/>
<table align="center">
 <tr>
  <td>
  <input type="submit" name="submit" value="edit">
  </td>
  <td>
  <input type="reset" value="cancel">
  </td>
 </tr>
</table>
<%	}  con.close();
	 }catch(Exception e)  {
	     System.out.println("Exception"+e);
      }
%>

</body>
</html>

 