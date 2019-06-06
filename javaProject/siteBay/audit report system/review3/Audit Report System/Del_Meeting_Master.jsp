<%@ page import="java.sql.*,DBcon.DataCon"%>
<html>
<body bgcolor="#E0E0E0"><jsp:include page="Home.jsp"/>
<TABLE width="100%">
<TR>
	<TD align="center" bgcolor="green"><jsp:include page="adminMenu.jsp"/></TD>
</TR>
</TABLE><br><br>
<center>
<h1> <font color="Green">Meeting Details</font></h1>
<form name="del" action="MeetingMaster">
<%
   String prefix=request.getParameter("prefix");
%>
   <table>
   <tr><td><b>Select Meeting ID</td><td><select name="TMeetingId1" onchange="javascript:var v=document.del.TMeetingId1.value;location.href='Del_Meeting_Master.jsp?id='+v+'&prefix=<%=prefix%>'">
   <option>--Select--</option>
   <%String x="";
   
     if(prefix.equals("delete"))
	 {
          x="readonly";
	 }
   %>
<% 

int evtno=0;
try{
   Connection con=new DataCon().getConnection(getServletContext());
   Statement st=con.createStatement();
   ResultSet rs=st.executeQuery("select meeting_id from meeting_master");

   while(rs.next()){
       evtno=rs.getInt(1);
   %>
   <option value="<%=evtno%>"><%=evtno%></option>
   <%}
%></select></td></tr>
<%  if(request.getParameter("id")!=null){
	rs=st.executeQuery("select meeting_id,meetingdesc,time,to_char(curdate,'DD-MON-YYYY'),address,location,city,phno,state from meeting_master where meeting_id="+request.getParameter("id"));
    while(rs.next()){
       evtno=rs.getInt(1);%>
<tr><td><b>Meeting ID</td><td><input type="text" name="TMeetingId" value="<%=(evtno)%>" readonly></td></tr>
   <tr><td><b>Meeting Description</td><td><input type="text" name="TMeetingDesc" value="<%=rs.getString(2)%>" <%=x%>></td></tr>
   <tr><td><b>Time</td><td><input type="text" name="TTime"  value="<%=rs.getString(3)%>" <%=x%>></td></tr>
   <tr><td><b>Date</td><td><input type="text" name="TDate" value="<%=rs.getString(4)%>" <%=x%>></td><td>(DD-MMM-YYYY)</td></tr>
   <tr><td><b>Address</td><td><input type="text" name="TAddress"  value="<%=rs.getString(5)%>" <%=x%>></td></tr>
   <tr><td><b>Location</td><td><input type="text" name="TLocation"  value="<%=rs.getString(6)%>" <%=x%>></td></tr>
   <tr><td><b>City</td><td><input type="text" name="TCity"  value="<%=rs.getString(7)%>" <%=x%>></td></tr>
   <tr><td><b>Phone No</td><td><input type="text" name="TPhno"  value="<%=rs.getString(8)%>" <%=x%>></td></tr>
   <tr><td><b>State</td><td><input type="text" name="TState"  value="<%=rs.getString(9)%>" <%=x%>></td></tr><tr></tr>

<%}}}catch(Exception e){e.printStackTrace();}
%>
   <tr></tr>
   <tr>
   <%
     if(prefix.equals("delete") && request.getParameter("id")!=null)
	 {
   %><td>
<input type="submit" value="DELETE"  name="TYPE"></td>
<%}else if(request.getParameter("id")!=null)
	 {
   %><td>
<input type="submit" value="Modify"  name="TYPE"></td>
<%}
   %></tr></table></form>
</body>

</html>


