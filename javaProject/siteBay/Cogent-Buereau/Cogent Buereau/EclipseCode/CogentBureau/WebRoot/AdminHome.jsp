<html>
 <head>
 <title>   </title>
 <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
 
<% 
System.out.println("session="+session);
System.out.println("username="+session.getAttribute("username"));
System.out.println("profileid="+session.getAttribute("profileid"));


if(session==null || session.getAttribute("username")==null || session.getAttribute("profileid")==null || !(session.getAttribute("profileid")).equals("1")) {
	  response.sendRedirect("login.html");
   }
%>
<frameset rows="130,*" frameborder=0 framespacing=0>
 <frame src="ahead.jsp" scrolling="no" noresize>
  <frameset cols="200,*">
   <frame name="list"  src=""  frameborder=0  scrolling="no" noresize>
   <frame name="main"  src="welcome.jsp" scrolling="yes" noresize>
 </frameset>
</frameset>
</html>