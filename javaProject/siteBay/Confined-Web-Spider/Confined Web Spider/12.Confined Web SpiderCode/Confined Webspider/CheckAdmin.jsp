<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ page import="org.w3c.dom.*"%>
<%@ page import="javax.xml.parsers.*"%>
<%@ page import="javax.xml.transform.*"%>
<%@ page import="javax.xml.transform.dom.*"%>
<%@ page import="javax.xml.transform.stream.*"%>
<%@ include file="Common.jsp"%>
<%!
	String database="";
	String adminid="";
	String adminpwd="";
	public void jspInit() {
	String root=System.getProperty("user.dir");
	String split=System.getProperty("file.separator");
	javax.servlet.ServletConfig sconfig=getServletConfig();
	database=sconfig.getServletContext().getInitParameter("register");
	adminid=sconfig.getServletContext().getInitParameter("userid");
	adminpwd=sconfig.getServletContext().getInitParameter("password");
	}
%>
<%
String uname=request.getParameter("userid");
String exist=request.getParameter("password");
String admin=request.getParameter("admin")!=null?"yes":"no";
String dbase=database;
DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
DocumentBuilder domBuilder = null;
Document doc = null;
factory.setValidating(false);
factory.setIgnoringElementContentWhitespace(false);
domBuilder = factory.newDocumentBuilder();
File fl=new File(dbase);
if(!fl.exists()) {
out.println("<center><h4>Sorry ! Currently No User Available</h4><BR><BR><a href='javascript:history.back()'>Continue</a></center><BR><Br>");
		return;
}
doc = domBuilder.parse(fl);
boolean change=false;
Element nl = doc.getDocumentElement();
if(admin.equals("yes")){
	   if(adminid.equals(uname) && adminpwd.equals(exist)) {
		session.setAttribute("Client",request.getParameter("userid"));
		response.sendRedirect("Admin_Home.jsp");}
		else {
			out.println("<center><h4>Sorry ! Invalid Admin ID and Password.<BR><BR>Please Check Once Again</h4><BR><BR><SCRIPT>setFooter();</SCRIPT>");
			return;
   		     }
} else {
	for(int u=0;u<nl.getChildNodes().getLength();u++) {
	String uid=nl.getChildNodes().item(u).getChildNodes().item(0).getChildNodes().item(0).getNodeValue().toString();
	String pwd=nl.getChildNodes().item(u).getChildNodes().item(1).getChildNodes().item(0).getNodeValue().toString();
	if(uname.equals(uid) && exist.equals(pwd)) {
		session.setAttribute("Client",request.getParameter("userid"));
		response.sendRedirect("PostResume.jsp");}
		}
		out.println("<center><h4>Sorry ! Invalid User Name and Password. Please Check Once Again</h4>");
	}
%>
<SCRIPT>setFooter();</SCRIPT>

