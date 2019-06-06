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
<form name="del" action="EventDesc">
<%
   String prefix=request.getParameter("prefix");
%>
   <table>
   <tr><td><b>Select Event ID</td><td><select name="TEvtId1" onchange="javascript:var v=document.del.TEvtId1.value;location.href='Del_Event_Desc.jsp?id='+v+'&prefix=<%=prefix%>'" >
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
   ResultSet rs=st.executeQuery("select distinct(evtid)from event_desc");
   String sele="";
   while(rs.next()){
       evtno=rs.getInt(1);
	   if(request.getParameter("id")!=null){
	   if(Integer.parseInt(request.getParameter("id"))==evtno)
	   {sele="Selected";}else{sele="";}}
   %>
   <option value="<%=evtno%>"><%=evtno%></option>
   <%}
%></select></td></tr>
<%  if(request.getParameter("id")!=null){
	rs=st.executeQuery("select evtid,evtdesc,exec_id from event_desc where evtid="+request.getParameter("id"));
    while(rs.next()){
       evtno=rs.getInt(1);%>
<tr><td><b>Event ID</td><td><input type="text" name="TEvtId" value="<%=(evtno)%>" readonly></td></tr>
   <tr><td><b>Event Description</td><td><input type="text" name="TEvtName" value="<%=rs.getString(2)%>" <%=x%>></td></tr>
   <tr><td><b>Executive ID</b></td><td>
   <%   
     if(prefix.equals("modify"))
	 {
   %>
   <select name="TExecId">
   <%
        rs=st.executeQuery("select execid from exec_master");// where utype='exec'");
   while(rs.next()){
       //evtno=rs.getInt(1);
       String uname=rs.getString(1);
   %>
   <option value="<%=evtno%>"><%=uname%></option>
   <%}
%></select>
  
   
   
   
	 <%
	 }
      else
	 {
   %>
   <input type="text" name="TExecId"  value="<%=rs.getString(3)%>" <%=x%>>
   
   
	 <%
		 }
   %>
   </td></tr>
   <tr></tr>

<%}}}catch(Exception e){e.printStackTrace();}
%>
   <tr></tr>
   <tr>
   <%
     if(prefix.equals("delete") && request.getParameter("id")!=null)
	 {
   %><td>
<input type="submit" value="DELETE"  name="TYPE"></td>
<%}else if(prefix.equals("modify") &&request.getParameter("id")!=null)
	 {
   %><td>
<input type="submit" value="Modify"  name="TYPE"></td>
<%}
   %></tr></table></form>
</body>

</html>


