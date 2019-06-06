<%
if(session.getAttribute("Client")==null) response.sendRedirect("Signin.jsp");
else {
	session.invalidate();
	response.sendRedirect("Home.jsp");
}
%>