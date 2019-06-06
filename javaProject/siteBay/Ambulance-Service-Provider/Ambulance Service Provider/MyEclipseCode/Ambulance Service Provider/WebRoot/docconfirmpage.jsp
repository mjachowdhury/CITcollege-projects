<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@page import="com.dts.em.dao.StaffDAO"%>
<%@page import="com.dts.es.dto.StaffDTO"%>

<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
StaffDTO docdto=null;
String temp= "";
// String edit="false";
String mode="";
temp=request.getParameter("mode");
System.out.println("temp @ 29"+temp);
if(temp!=null && !temp.equals(""))
{
  mode=temp;
}
System.out.println("temp @ 34"+mode);
try
{
	docdto = new StaffDTO();
	if(mode.equals("edit") || mode.equals("delete"))
	{
		docdto.setDocid(Integer.parseInt(request.getParameter("id")));	
	}
	
	docdto.setFirstname(request.getParameter("docfirstname"));
	docdto.setLastname(request.getParameter("docLastname"));
	docdto.setAddress(request.getParameter("docaddress"));
	docdto.setCity(request.getParameter("docctiy"));
	docdto.setState(request.getParameter("docstate"));
	docdto.setSpecialization(request.getParameter("docspec"));
	docdto.setWorkinghours(request.getParameter("docworkhours"));
	docdto.setLocation(request.getParameter("docloc"));
	
	
}catch(Exception e)
{
	com.dts.core.util.LoggerManager.writeLogSevere(e);
}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'docconfirmpage.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body> 
  <%
   	StaffDAO docdao= new StaffDAO();
          boolean bool=true;
          if(mode.equals("delete"))
          {
          	bool = docdao.deleteDoctor(docdto);
          }
          else if(mode.equals("edit"))
          {
          	bool = docdao.updateDoctors(docdto);		
          }else{
      		 bool = docdao.registerDoctors(docdto);
      	}
      	if(bool==true)
      	{
   %>
   
   <center>Doctor Details</center>
   <table>
   <tr><th>First Name</th><th>Last Name</th><th>Address</th><th>City</th><th>State</th>
   <th>Specialization </th><th>WorkingHours</th><th>Location</th></tr>
   <%
   	ArrayList al= docdao.getDocDetails();
       if(al!=null && al.size()>0)
       {
       	for(int i=0;i<al.size();i++)
       	{
       		 docdto=(StaffDTO)al.get(i);
   %>
    
    	<tr>
    	
    	<td><%=docdto.getFirstname()%></td>
    	<td><%=docdto.getLastname()%></td>
    	<td><%=docdto.getAddress()%></td>
    	<td><%=docdto.getCity()%></td>
    	<td><%=docdto.getState()%></td>
    	<td><%=docdto.getSpecialization()%></td>
    	<td><%=docdto.getWorkinghours()%></td>
    	<td><%=docdto.getLocation()%></td>
    	<td><a href="docDetails.jsp?mode=edit&docid=<%=docdto.getDocid()%>">EDIT</a></td>
    	<td><a href="docconfirmpage.jsp?mode=delete&id=<%=docdto.getDocid()%>">DELETE</a></td>  
    	</tr>
    <% 
    	}	}
    	
    
    %>
   </table>
	<%}else {%>Your operation is not success <%  } %>
  </body>
</html>
