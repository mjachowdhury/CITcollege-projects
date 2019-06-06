<%@ page import="java.sql.*,DBcon.DataCon"%>
<html>
<body bgcolor="#E0E0E0"><jsp:include page="Home1.jsp"/>
<TABLE width="100%">
<TR>
	<TD align="center" bgcolor="green"><jsp:include page="ExecMenu.jsp"/></TD>
</TR>
</TABLE><br><br>
<center>
<h1> <font color="Green">Contract Details</font></h1>
<form name="del" action="UpdateContractDetails">
<%
   String prefix=request.getParameter("prefix");
%>
   <table>
   
<% 

int evtno=0;
try{
   Connection con=new DataCon().getConnection(getServletContext());
   Statement st=con.createStatement();
   ResultSet rs=st.executeQuery("select c.contid,c.addrefno,c.con_desc,c.cont_task,c.exec_id,s.status from con_details c,con_status s where c.contid="+request.getParameter("prefix")+" and c.contid=s.contid and c.exec_id=s.exec_id");
    while(rs.next()){
       evtno=rs.getInt(1);%>
<tr><td><b>Contract ID</td><td><input type="text" name="TContractId" value="<%=(evtno)%>" readonly></td></tr>
   <tr><td><b>Company Id</td><td><input type="text" name="TCompanyId" value="<%=rs.getString(2)%>"  readonly></td></tr>
   <tr><td><b>Contract Description</td><td><input type="text" name="TContractDescription" value="<%=rs.getString(3)%>" readonly></td></tr>
   <tr><td><b>Task</td><td><input type="text" name="TConTask" value="<%=rs.getString(4)%>" readonly></td></tr>
   <tr><td><b>Executive ID</td>
   <td><input type="text" name="TExecId" value="<%=rs.getString(5)%>" readonly></td></tr>
   <tr><td><b>Status</td><td><select name="TStatus">
   <option>Not Finished</option>
   <option>Finished</option>
   <option>Fine</option>
   </select>
   	 
   </tr>

<%}}catch(Exception e){e.printStackTrace();}
%>
   <tr></tr>
   <tr>
  <td>

<input type="submit" value="MODIFY"  name="TYPE"></td>
</tr></table></form>
</body>

</html>


