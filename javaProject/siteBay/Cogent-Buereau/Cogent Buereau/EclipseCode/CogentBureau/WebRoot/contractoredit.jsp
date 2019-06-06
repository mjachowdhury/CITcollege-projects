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
<u>Contractor Information</u>
</font>
<form method="get" action="editcontractoraction.do" target="main">
<table>
 <tr><td><html:errors/></td></tr>
<%	try{
	  System.out.println("---------reset--------");
      
      java.sql.Connection con=java.sql.DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","distcol","distcol");
      java.sql.Statement stmt=null;
	  String sqlSTMT="select pk,cname,caddr,bid,bname,baddr,years,pwd from contractor where pk='"+request.getParameter("pk")+"'";
	  System.out.println(sqlSTMT);
      stmt=con.createStatement();
      java.sql.ResultSet rs=stmt.executeQuery(sqlSTMT);
	  if(rs.next()) {%>
 <tr>
  <td><font size=4 >Contractor ID</td>
  <td><font size=4 >:</td>
  <td><input type="text" name="pk" value='<%=rs.getString(1)%>' size=26 readonly></td>
 </tr>
 <tr>
  <td><font size=4 >Contractor Name</td>
  <td><font size=4 >:</td>
  <td><input type="text" name="cname" value='<%=rs.getString(2)%>' size=26></td>
 </tr>
 <tr>
  <td><font size=4 >Contractor Address</td>
  <td><font size=4 >:</td>
  <td><textarea name="caddr"><%=rs.getString(3)%></textarea></td>
 </tr>
  <tr>
  <td><font size=4 >Bank Account ID</td>
  <td><font size=4 >:</td>
  <td><input type="text" size=26 name="bid" value='<%=rs.getString(4)%>'></td>
 </tr>
  <tr>
  <td><font size=4 >Bank Name</td>
  <td><font size=4 >:</td>
  <td><textarea name="bname"><%=rs.getString(5)%></textarea></td>
 </tr>
  <tr>
  <td><font size=4 >Bank Address</td>
  <td><font size=4 >:</td>
  <td><textarea name="baddr"><%=rs.getString(6)%></textarea></td>
 </tr>
 <tr>
  <td><font size=4>Years Of Exp.</td>
  <td><font size=4>:</td>
  <td><input type="text" name="years" value='<%=rs.getString(7)%>' size=26></td>
 </tr>
  <tr>
  <td><font size=4 >Login Password</font></td>
  <td><font size=4 >:</td>
  <td><font size=1><input type="text" name="pwd" value='<%=rs.getString(8)%>' size=26></font></td>
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

 