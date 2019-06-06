<%@ page import="java.sql.*,DBcon.DataCon"%>
<html>
<body bgcolor="#E0E0E0"><jsp:include page="Home.jsp"/>
<TABLE width="100%">
<TR>
	<TD align="center" bgcolor="green"><jsp:include page="adminMenu.jsp"/></TD>
</TR>
</TABLE><br><br>
<center>
<h1> <font color="Green">Category Details</font></h1>
<form name="del" action="ContractMaster">
<%
   String prefix=request.getParameter("prefix");
%>
   <table>
   <tr><td><b>Select Category ID</td><td><select name="TContractId1" onchange="javascript:var v=document.del.TContractId1.value;location.href='Del_Category_Master.jsp?id='+v+'&prefix=<%=prefix%>'">
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
   ResultSet rs=st.executeQuery("select contid from con_master");

   while(rs.next()){
       evtno=rs.getInt(1);
   %>
   <option value="<%=evtno%>"><%=evtno%></option>
   <%}
%></select></td></tr>
<%  if(request.getParameter("id")!=null){
	rs=st.executeQuery("select contid,cont_category from con_master where contid="+request.getParameter("id"));
    while(rs.next()){
       evtno=rs.getInt(1);%>
<tr><td><b>Contract ID</td><td><input type="text" name="TContractId" value="<%=(evtno)%>" readonly></td></tr>
   <tr><td><b>Contract Category</td><td><input type="text" name="TContractCategory" value="<%=rs.getString(2)%>" <%=x%>></td></tr>
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


