<%@ page import="java.sql.*,DBcon.DataCon"%>
<html>
<body bgcolor="#E0E0E0"><jsp:include page="Home.jsp"/>
<TABLE width="100%">
<TR>
	<TD align="center" bgcolor="green"><jsp:include page="adminMenu.jsp"/></TD>
</TR>
</TABLE><br><br>
<center>
<h1> <font color="Green">Event Details</font></h1>
<form name="del" action="EventMaster">
<%
   String prefix=request.getParameter("prefix");
%>
   <table>
   <tr><td><b>Select Event ID</td><td><select name="TEvtId1" onchange="javascript:var v=document.del.TEvtId1.value;location.href='Del_Event_Master.jsp?id='+v+'&prefix=<%=prefix%>'">
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
   ResultSet rs=st.executeQuery("select evtid from event_master");

   while(rs.next()){
       evtno=rs.getInt(1);
   %>
   <option value="<%=evtno%>"><%=evtno%></option>
   <%}
%></select></td></tr>
<%  if(request.getParameter("id")!=null){
	System.out.println("select evtid,evtname,venue,to_char(curdate,'DD-MON-YYYY'),address,location,city,phno from event_master where evtid="+request.getParameter("id"));
	rs=st.executeQuery("select evtid,evtname,venue,to_char(curdate,'DD-MON-YYYY'),address,location,city,phno from event_master where evtid="+request.getParameter("id"));
    while(rs.next()){
       evtno=rs.getInt(1);%>
<tr><td><b>Event ID</td><td><input type="text" name="TEvtId" value="<%=(evtno)%>" readonly></td></tr>
   <tr><td><b>Event Name</td><td><input type="text" name="TEvtName" value="<%=rs.getString(2)%>" <%=x%>></td></tr>
   <tr><td><b>Venue</td><td><input type="text" name="TVenue"  value="<%=rs.getString(3)%>" <%=x%>></td></tr>
   <tr><td><b>Date</td><td><input type="text" name="TDate" value="<%=rs.getString(4)%>" <%=x%>></td><td>(DD-MMM-YYYY)</td></tr>
   <tr><td><b>Address</td><td><input type="text" name="TAddress"  value="<%=rs.getString(5)%>" <%=x%>></td></tr>
   <tr><td><b>Location</td><td><input type="text" name="TLocation"  value="<%=rs.getString(6)%>" <%=x%>></td></tr>
   <tr><td><b>City</td><td><input type="text" name="TCity"  value="<%=rs.getString(7)%>" <%=x%>></td></tr>
   <tr><td><b>Phone No</td><td><input type="text" name="TPhno"  value="<%=rs.getString(8)%>" <%=x%>></td></tr><tr></tr>

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


