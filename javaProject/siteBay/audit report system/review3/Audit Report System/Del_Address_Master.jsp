<%@ page import="java.sql.*,DBcon.DataCon"%>
<html>
<body bgcolor="#E0E0E0"><jsp:include page="Home.jsp"/>
<TABLE width="100%">
<TR>
	<TD align="center" bgcolor="green"><jsp:include page="adminMenu.jsp"/></TD>
</TR>
</TABLE><br><br>
<center>
<h1> <font color="Green">Company Details</font></h1>
<form name="del" action="Addres">
<%
   String prefix=request.getParameter("prefix");
%>
   <center><table>
   <tr><td><b>Select Company ID</td><td><select name="TAddRefNo1" onchange="javascript:var v=document.del.TAddRefNo1.value;location.href='Del_Address_Master.jsp?id='+v+'&prefix=<%=prefix%>'">
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
   ResultSet rs=st.executeQuery("select AddRefNo from Address");

   while(rs.next()){
       evtno=rs.getInt(1);
   %>
   <option value="<%=evtno%>"><%=evtno%></option>
   <%}
%></select></td></tr>
<%  if(request.getParameter("id")!=null){
	rs=st.executeQuery("select a.addrefno,a.compname,p.phno1,e.cont_person,a.location,p.phno2,e.emailid1,a.city,p.mobile,e.emailid2,a.state,e.others,a.country from address a,phnos p,email e where a.addrefno="+request.getParameter("id")+"and e.addrefno=a.addrefno and e.addrefno=p.addrefno");
    while(rs.next()){
       evtno=rs.getInt(1);%>
<tr>
<td><b>Company ID</td><td><input type="Text" name="TAddRefNo" value="<%=evtno%>" readonly></td></tr>
<tr>
<td><b>Company Name</td><td><input type="text" name="TCompName" value="<%=rs.getString(2)%>" <%=x%>></td><td>&nbsp;&nbsp;</td><td><b>Phone1</td><td><input type="text" name="TPhno1" value="<%=rs.getString(3)%>" <%=x%>></td><td>&nbsp;&nbsp;</td><td><b>Contact Person</td><td><input type="text" name="TContactPerson" value="<%=rs.getString(4)%>" <%=x%>></td></tr>
<tr>
<td><b>Location</td><td><input type="text" name="TLocation" value="<%=rs.getString(5)%>" <%=x%>></td><td>&nbsp;&nbsp;</td><td><b>Phone2</td><td><input type="text" name="TPhno2" value="<%=rs.getString(6)%>" <%=x%>></td><td>&nbsp;&nbsp;</td><td><b>Email ID1</td><td><input type="text" name="TEmailId1" value="<%=rs.getString(7)%>" <%=x%>></td></tr>
<tr>
<td><b>City</td><td><input type="text" name="TCity" value="<%=rs.getString(8)%>" <%=x%>></td><td>&nbsp;&nbsp;</td><td><b>Mobile</td><td><input type="text" name="TMobile" value="<%=rs.getString(9)%>" <%=x%>></td><td>&nbsp;&nbsp;</td><td><b>Email ID2</td><td><input type="text" name="TEmailId2" value="<%=rs.getString(10)%>" <%=x%>></td></tr>
<tr>
<td><b>State</td><td><input type="text" name="TState" value="<%=rs.getString(11)%>" <%=x%>></td><td>&nbsp;&nbsp;</td><td><b></td><td></td><td>&nbsp;&nbsp;</td><td><b>Others</td><td><input type="text" name="TOthers" value="<%=rs.getString(12)%>" <%=x%>></td></tr>
<tr>
<td><b>Country</td><td><input type="text" name="TCountry"  value="<%=rs.getString(13)%>" <%=x%>></td><td>&nbsp;&nbsp;</td><td><b></td><td></td><td>&nbsp;&nbsp;</td><td><b></td><td></td></tr><tr></tr>

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


