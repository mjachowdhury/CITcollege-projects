<%@ page import="java.sql.*,DBcon.DataCon"%>
<html>
<body bgcolor="#E0E0E0" onload="javascript:alert()"><jsp:include page="Home.jsp"/>
<TABLE width="100%">
<TR>
	<TD align="center" bgcolor="green"><jsp:include page="adminMenu.jsp"/></TD>
</TR>
</TABLE><br><br>
<center>
<h1> <font color="Green">Agenda</font></h1>

   <form action="MeetingDesc">
   <table>
   <tr><td><b>Meeting ID</td><td><select name="TMeetingId">
<%int evtno=0;
String empid="";
try{
	System.out.println("in jsp");
   Connection con=new DataCon().getConnection(getServletContext());
   Statement st=con.createStatement();
   ResultSet rs=st.executeQuery("select Meeting_id from meeting_master");

   while(rs.next()){
       evtno=rs.getInt(1);
   %>
   <option value="<%=evtno%>"><%=evtno%></option>
   <%}
%></select>
   </td></tr>
   <tr><td><b>Agenda</td><td><input type="text" name="TAgenda"></td></tr>
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
<td colspan="2">&nbsp;&nbsp;
<input type="Submit" value="INSERT" name="TYPE">&nbsp;&nbsp;
<input type="Button" value="DELETE"  name="TYPE" onclick="javascript:location.href='Del_Meeting_Desc.jsp?prefix=delete'">&nbsp;&nbsp;<input type="Button" value="MODIFY"  name="TYPE" onclick="javascript:location.href='Del_Meeting_Desc.jsp?prefix=modify'">&nbsp;&nbsp;
<input type="reset" value="RESET"/>
</td></tr></table>

<table width="467" border="0">
     <tr>
       <td width="200"><div align="center"><strong>Meeting details</strong></div></td>
       <td width="251"><div align="center"><strong>Executive Details</strong></div></td>
     </tr>
     <tr>
	 <% rs=st.executeQuery("select Meeting_id,Meetingdesc from meeting_master order by meeting_id");%>
       <td align="center"><select name="select" size="10">
         
         <% while(rs.next()){
    %>        <option><%=rs.getString(1)%>. <%=rs.getString(2)%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </option>
         <%}
   %>
       </select></td>
       <td align="center">
	   <% rs=st.executeQuery("select e.execid,u.username from users u,exec_master e where u.username=e.execname and u.utype='exec'");%>
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




