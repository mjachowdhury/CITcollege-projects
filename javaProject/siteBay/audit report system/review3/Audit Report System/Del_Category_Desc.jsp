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
<form name="del" action="ContractDetails">
<%
   String prefix=request.getParameter("prefix");
%>
   <table>
   <tr><td><b>Select Contract ID</td><td><select name="TContractId1" onchange="javascript:var v=document.del.TContractId1.value;location.href='Del_Category_Desc.jsp?id='+v+'&prefix=<%=prefix%>'" >
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
   ResultSet rs=st.executeQuery("select distinct(contid) from con_details");
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
	rs=st.executeQuery("select c.contid,c.addrefno,c.con_desc,c.cont_task,c.exec_id,s.status from con_details c,con_status s where c.contid="+request.getParameter("id")+" and c.contid=s.contid and c.exec_id=s.exec_id");
    while(rs.next()){
       evtno=rs.getInt(1);%>
<tr><td><b>Contract ID</td><td><input type="text" name="TContractId" value="<%=(evtno)%>" readonly></td></tr>
   <tr><td><b>Company Id</td><td><input type="text" name="TCompanyId" value="<%=rs.getString(2)%>"  readonly></td></tr>
   <tr><td><b>Contract Description</td><td><input type="text" name="TContractDescription" value="<%=rs.getString(3)%>" <%=x%>></td></tr>
   <tr><td><b>Task</td><td><input type="text" name="TConTask" value="<%=rs.getString(4)%>" <%=x%>></td></tr>
   <tr><td><b>Executive ID</td>
   <%   
     if(prefix.equals("modify"))
	 {
   %>
   <td><select name="TExecId">
   <%
        rs=st.executeQuery("select e.execid from users u,exec_master e where u.username=e.execname and u.utype='exec'");
   while(rs.next()){
       evtno=rs.getInt(1);
   %>
   <option value="<%=evtno%>"><%=evtno%></option>
   <%}
%></select>
  
   
   </td></tr><tr><td><b>Status</td><td><select name="TStatus">
   <option>Not Finished</option>
   <option>Finished</option>
   <option>Fine</option>
   </select>
   
	 <%
	 }
      else
	 {
   %>
   <td><input type="text" name="TExecId"  value="<%=rs.getString(5)%>" <%=x%>></td></tr>
   <tr><td><b>Status</td><td><input type="text" name="TStatus" value="<%=rs.getString(6)%>" <%=x%>></td></tr>
	 <%
		 }
   %>
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


