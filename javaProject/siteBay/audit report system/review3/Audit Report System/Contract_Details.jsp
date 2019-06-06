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
<form action="ContractDetails">
   <table>
   <tr><td><b>Contract ID</td><td><select name="TContractId">
<%int evtno=0;
String empid="";
try{
	System.out.println("in jsp");
   Connection con=new DataCon().getConnection(getServletContext());
   Statement st=con.createStatement();
   ResultSet rs=st.executeQuery("select contid from con_master");

   while(rs.next()){
       evtno=rs.getInt(1);
   %>
   <option value="<%=evtno%>"><%=evtno%></option>
   <%}
%></select>
   </td></tr>
   <tr><td><b>Company ID</td><td><select name="TCompanyId">
<%evtno=0;
    rs=st.executeQuery("select addrefno from address");

   while(rs.next()){
       evtno=rs.getInt(1);
   %>
   <option value="<%=evtno%>"><%=evtno%></option>
   <%}
%></select>
   </td></tr>
   <tr><td><b>Contract Description</td><td><input type="text" name="TContractDesc"></td></tr>

   <tr><td><b>Contract task</td><td><input type="text" name="TConTask"></td></tr>

   <tr><td><b>Executive ID</td><td><select name="TExecId">
  <% rs=st.executeQuery("select execid from exec_master");

   while(rs.next()){
       empid=rs.getString(1);
   %>
   <option value="<%=empid%>"><%=empid%></option>
   <%}
%></select>
  
</td></tr>
   <tr></tr>
   <tr>
<td colspan="2">&nbsp;&nbsp;<input type="Submit" value="INSERT" name="TYPE">&nbsp;&nbsp;<input type="Button" value="DELETE"  name="TYPE" onclick="javascript:location.href='Del_Category_Desc.jsp?prefix=delete'">&nbsp;&nbsp;<input type="Button" value="MODIFY"  name="TYPE" onclick="javascript:location.href='Del_Category_Desc.jsp?prefix=modify'">&nbsp;&nbsp;</td></tr></table>

<table width="467" border="0">
     <tr>
       <td width="200"><div align="center"><strong>Contract details</strong></div></td>
       <td width="251"><div align="center"><strong>Company Details</strong></div></td>
	   <td width="251"><div align="center"><strong>Executive Details</strong></div></td>
     </tr>
     <tr>
	 <% rs=st.executeQuery("select contid,cont_category from con_master order by contid");%>
       <td align="center"><select name="select" size="10">
         
         <% while(rs.next()){
    %>        <option><%=rs.getString(1)%>. <%=rs.getString(2)%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </option>
         <%}
   %>
       </select></td>
       <td align="center">
	   <% rs=st.executeQuery("select addrefno,compname from address");%>
        <select name="exec" size="10">
		
  <% while(rs.next()){
    %><option><%=rs.getString(1)%>. <%=rs.getString(2)%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </option>
   
		<%}
   %>
		 </select>
      </td>
	  <td align="center">
	   <% rs=st.executeQuery("select e.execid,e.execname from users a,exec_master e where a.username=e.execname");%>
        <select name="exec" size="10">
		
  <% while(rs.next()){
    %><option><%=rs.getString(1)%>. <%=rs.getString(2)%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </option>
   
		<%}
   %>
		 </select>
      </td>
	   
	   <%}catch(Exception e){}
%>
     </tr>
   </table>

</form>
</body></html>


