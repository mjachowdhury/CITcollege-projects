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
<br>
<br>
<font size=4 >
<u>Widow Certificate</u>
</font>
<br>
<br>
<br>
<br>
<form method="get" action="showwidowaction.do" target="main">
<table>
<tr>
<td>Select  WidowCertificate ID</td>
<td>:</td>
<td> 
 <%@ page import="java.sql.*" %>
<%    
    
    Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:xe","srinivas","srinivas");

    Statement stmt=con.createStatement();
    ResultSet rs=stmt.executeQuery("select  wid  from  widowcertificate where flag=0");
    out.println("<select name=icid  STYLE=\"width: 100px\">");
    while(rs.next())
    {
      String str=rs.getString("wid");
      out.println("<option>");
      out.println(str);
    }
    out.println("</select>");
  %>
</td>
</tr>
<tr>
<td>
<br>
<br>
</td>
</tr>
<tr align="right">
<td>
<input type="submit" value="View">
</td>
</tr>
</table>
</form>
</center>
</body>
</html>

