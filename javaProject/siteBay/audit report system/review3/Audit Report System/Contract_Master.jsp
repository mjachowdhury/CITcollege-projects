<%@ page import="java.sql.*,DBcon.DataCon"%>
<html>
<body bgcolor="#E0E0E0"><jsp:include page="Home.jsp"/>
<TABLE width="100%">
<TR>
	<TD align="center" bgcolor="green"><jsp:include page="adminMenu.jsp"/></TD>
</TR>
</TABLE><br><br>
<center>
<h1> <font color="Green">Contract Details</font></h1>
 
   <form action="ContractMaster">
   <table>
   <tr><td><b>Contract Id</td><td>
   <%
   try{
   Connection con = new DataCon().getConnection(getServletContext());
   Statement st=con.createStatement();
   ResultSet rs=st.executeQuery("select max(contid) from con_master");
   int evtno=0;
   if(rs.next())
       evtno=rs.getInt(1);
   
   %><input type="text" name="TContractId" value="<%=evtno+1%>"></td></tr>
   <tr><td><b>Contract Category</td><td><input type="text" name="TContractCategory"></td>
   <%
   }catch(Exception e){} %>
   </tr><tr></tr>
   <tr>
<td colspan="2">&nbsp;&nbsp;
<input type="Submit" value="INSERT" name="TYPE">&nbsp;&nbsp;<input type="Button" value="DELETE"  name="TYPE" onclick="javascript:location.href='Del_Category_Master.jsp?prefix=delete'">&nbsp;&nbsp;<input type="Button" value="Modify"  name="TYPE" onclick="javascript:location.href='Del_Category_Master.jsp?prefix=modify'">&nbsp;&nbsp;
<input type="Reset" value="RESET"/>
</td></tr></table>
</body>
</html>
