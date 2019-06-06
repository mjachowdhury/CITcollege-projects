<%@ page import="java.sql.*,DBcon.DataCon"%>
<html>
<body bgcolor="#E0E0E0"><jsp:include page="Home.jsp"/>
<TABLE width="100%">
<TR>
	<TD align="center" bgcolor="green"><jsp:include page="adminMenu.jsp"/></TD>
</TR>
</TABLE><br><br>
<center>
<h1> <font color="Green">Agenda</font></h1>
<form name="del" action="MeetingDesc">
<%
   String prefix=request.getParameter("prefix");
%>
   <table>
   <tr><td><b>Select Meeting ID</td><td><select name="TMeetingId1" onchange="javascript:var v=document.del.TMeetingId1.value;location.href='Del_Meeting_Desc.jsp?id='+v+'&prefix=<%=prefix%>'" >
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
   ResultSet rs=st.executeQuery("select distinct(Meeting_id) from Meeting_desc");
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
	rs=st.executeQuery("select meeting_id,agenda,exec_id from meeting_desc where meeting_id="+request.getParameter("id"));
    while(rs.next()){
       evtno=rs.getInt(1);%>
<tr><td><b>Meeting ID</td><td><input type="text" name="TMeetingId" value="<%=(evtno)%>" readonly></td></tr>
   <tr><td><b>Agenda</td><td><input type="text" name="TAgenda" value="<%=rs.getString(2)%>" <%=x%>></td></tr>
   <tr><td><b>Executive ID</b></td><td>
   <%   
     if(prefix.equals("modify"))
	 {
   %>
   <select name="TExecId">
   <%
        rs=st.executeQuery("select execid from exec_master");
   while(rs.next()){
       evtno=rs.getInt(1);
   %>
   <option value="<%=evtno%>"><%=evtno%></option>
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
<%}else if(request.getParameter("id")!=null)
	 {
   %><td>
<input type="submit" value="Modify"  name="TYPE"></td>
<%}
   %></tr></table></form>
</body>

</html>


