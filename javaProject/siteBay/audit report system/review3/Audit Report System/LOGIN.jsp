<%@ page import="java.sql.*,DBcon.DataCon"%>
<%
try{
Connection con=new DataCon().getConnection(getServletContext());
Statement st=con.createStatement();
System.out.println("select * from users where username='"+request.getParameter("user")+"' and password='"+request.getParameter("pass")+"'" );
ResultSet rs=st.executeQuery("select * from users where username='"+request.getParameter("user")+"' and password='"+request.getParameter("pass")+"'" );
request.getSession(true);
while(rs.next()){
 String str=rs.getString(3);
if(str.equalsIgnoreCase("Admin"))
{session.setAttribute("user",request.getParameter("user"));
	%>
	<jsp:forward page="f1.jsp"/>
<%

}
else if(str.equalsIgnoreCase("exec"))
{
	rs=st.executeQuery("select e.execid from exec_master e,users u where u.username='"+request.getParameter("user")+"' and u.username=e.execname");
if(rs.next()){
	str=rs.getString(1);
	session.setAttribute("execid",str);
    session.setAttribute("user",request.getParameter("user"));
	System.out.println((String)session.getAttribute("user"));
%>
	<jsp:forward page="f2.jsp"/>
<%}
}
}
response.sendRedirect("login.html");
}
catch(Exception e){e.printStackTrace();}
%>