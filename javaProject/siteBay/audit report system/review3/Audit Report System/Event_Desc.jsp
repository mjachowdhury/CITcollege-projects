<%@ page import="java.sql.*,DBcon.DataCon"%>
<html>
<body bgcolor="#E0E0E0" onLoad="javascript:alert()"><jsp:include page="Home.jsp"/>
<TABLE width="100%">
<TR>
	<TD align="center" bgcolor="green"><jsp:include page="adminMenu.jsp"/></TD>
</TR>
</TABLE><br><br>
<center>
<h1> <font color="Green">Event Details</font></h1>

   <form action="EventDesc">
   <table>
   <tr><td width="117"><b>Event ID</td><td width="144"><select name="TEvtId">
<%int evtno=0;
String empid="";
try{
	System.out.println("in jsp");
   Connection con=new DataCon().getConnection(getServletContext());
   Statement st=con.createStatement();
   ResultSet rs=st.executeQuery("select evtid from event_master");

   while(rs.next()){
       evtno=rs.getInt(1);
   %>
   <option value="<%=evtno%>"><%=evtno%></option>
   <%}
%></select>
   </td>
     <td width="136" rowspan="3" valign="top"><label for="textarea"></label></td>
   </tr>
   <tr><td><b>Event Description</td><td><input type="text" name="TEvtDesc"></td>
     </tr>
   <tr><td><b>Executive ID</td><td><select name="TExecId">
  <% rs=st.executeQuery("select e.execid from users u,exec_master e where u.username=e.execname and u.utype='exec'");

   while(rs.next()){
       empid=rs.getString(1);
   %>
   <option value="<%=empid%>"><%=empid%></option>
   <%}
%></select>
   </td>
     </tr>
   
   <tr>
<td colspan="3">&nbsp;&nbsp;
  <div align="center">
    <input type="Submit" value="INSERT" name="TYPE">
    &nbsp;&nbsp;
    <input type="Button" value="DELETE"  name="TYPE" onClick="javascript:location.href='Del_Event_Desc.jsp?prefix=delete'">
    &nbsp;&nbsp;
    <input type="Button" value="MODIFY"  name="TYPE" onClick="javascript:location.href='Del_Event_Desc.jsp?prefix=modify'">
    &nbsp;&nbsp;
	
	<input type="reset" value="RESET">
	</div></td></tr></table>
  
   <table width="467" border="0">
     <tr>
       <td width="200"><div align="center"><strong>Event details</strong></div></td>
       <td width="251"><div align="center"><strong>Executive Details</strong></div></td>
     </tr>
     <tr>
	 <% rs=st.executeQuery("select evtid,evtname from event_master order by evtid");%>
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