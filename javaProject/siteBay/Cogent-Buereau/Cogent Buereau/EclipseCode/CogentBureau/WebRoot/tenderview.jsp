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
<u>Tender Information</u>
</font>
<form method="get" name="cform" action="tenderinsert.jsp" target="main"
onSubmit="return validateAll();">
<script language="JavaScript">
  function validateAll() {
    if(document.cform.ndays.value=='')  {
	  alert("No. of days must be enter");
	  return false;
	}
	if(!parseInt(document.cform.ndays.value)) {
	  alert("Not a validate day");
	  return false;
	}

    if(document.cform.amt.value=='')  {
	  alert("Not a validate amount");
	  return false;
	}
	if(!parseInt(document.cform.amt.value)) {
	  alert("Amount must be a number");
	  return false;
	}
	return true;
  }
</script>
<table>
 <tr><td><html:errors/></td></tr>
<%	try{
	  System.out.println("---------reset--------");
      
      java.sql.Connection con=java.sql.DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","distcol","distcol");
      java.sql.Statement stmt=null;
	  String sqlSTMT="select tid,tname,workplace,tdesc,flag from tender where tid='"+request.getParameter("tid")+"'";
	  System.out.println(sqlSTMT);
      stmt=con.createStatement();
      java.sql.ResultSet rs=stmt.executeQuery(sqlSTMT);
	  if(rs.next()) {%>
 <tr>
  <td><font size=4 >Tender ID</td>
  <td><font size=4 >:</td>
  <td><input type="text" name="tid" value='<%=rs.getString(1)%>' size=26 readonly></td>
 </tr>
 <tr>
  <td><font size=4 >Tender Name</td>
  <td><font size=4 >:</td>
  <td><input type="text" name="tname" value='<%=rs.getString(2)%>' size=26 readonly></td>
 </tr>
 <tr>
  <td><font size=4 >Tender Place</td>
  <td><font size=4 >:</td>
  <td><textarea name="wplace" readonly><%=rs.getString(3)%></textarea></td>
 </tr>
  <tr>
  <td><font size=4 >Tender Description</td>
  <td><font size=4 >:</td>
  <td><textarea name="tdesc" rows="15" cols="50" readonly><%=rs.getString(4)%></textarea></td>
 </tr>
  <tr>
  <td><font size=4 >Tender Status</td>
  <td><font size=4 >:</td>
  <td><select name="flag" readonly>
     <option value="open">open</option>
	 </select></td>
 </tr>
 <tr>
  <td><font size=4 >No. of days</td>
  <td><font size=4 >:</td>
  <td><input type="text" name="ndays" size=26></td>
 </tr>
 <tr>
  <td><font size=4 >Amount</td>
  <td><font size=4 >:</td>
  <td><input type="text" name="amt" size=26></td>
 </tr>
</table>
</center>
<br>
<br>
<input type="hidden" name="method" value="create"/>
<table align="center">
 <tr>
  <td>
  <input type="submit" name="submit" value="Send Tender">
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

 