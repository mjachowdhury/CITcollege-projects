<%@ page import="java.sql.*,DBcon.DataCon"%>
<html>
<body bgcolor="#E0E0E0"><jsp:include page="Home.jsp"/>
<TABLE width="100%">
<TR>
	<TD align="center" bgcolor="green"><jsp:include page="adminMenu.jsp"/></TD>
</TR>
</TABLE><br><br>
<center>
<h1> <font color="Green">Executive Details</font></h1>
<form name="del" action="ExecMaster">
<%
   String prefix=request.getParameter("prefix");
%>
   <table>
   <tr><td><b>Select Executive ID</td><td><select name="TExecId1" onchange="javascript:var v=document.del.TExecId1.value;location.href='Del_exec_Master.jsp?id='+v+'&prefix=<%=prefix%>'">
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
   ResultSet rs=st.executeQuery("select execid from exec_master");

   while(rs.next()){
       evtno=rs.getInt(1);
   %>
   <option value="<%=evtno%>"><%=evtno%></option>
   <%}
%></select></td></tr>
<%  if(request.getParameter("id")!=null){
	rs=st.executeQuery("select execid,execname,location,mobileno from exec_master where execid="+request.getParameter("id"));
    while(rs.next()){
       evtno=rs.getInt(1);%>
<tr><td><b>Executive ID</td><td><input type="text" name="TExecId" value="<%=(evtno)%>" readonly></td></tr>
   <tr><td><b>Name</td><td>
   <input type="text" name="TExecName" value="<%=rs.getString(2)%>" <%=x%> readonly></td></tr>
   <tr><td><b>Location</td><td><input type="text" name="TLocation"  value="<%=rs.getString(3)%>" <%=x%>></td></tr>
   <tr><td><b>Mobile No</td><td><input type="text" name="TMobileNo" value="<%=rs.getString(4)%>" <%=x%>></td></tr><tr></tr>

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
<input type="submit" value="MODIFY"  name="TYPE"></td>
<%}
   %></tr></table></form>
</body>

</html>


