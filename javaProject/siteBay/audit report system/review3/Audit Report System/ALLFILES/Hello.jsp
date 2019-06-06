<%@ page import="java.util.*,java.text.*" %>


<%
SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss::dd:MMMM:yyyy");

String str = sdf.format(new Date());

out.println(str);
%>



