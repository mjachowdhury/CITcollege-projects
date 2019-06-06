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
	public void jspInit() {
	String root=System.getProperty("user.dir");
	String split=System.getProperty("file.separator");
	javax.servlet.ServletConfig sconfig=getServletConfig();
	database=sconfig.getServletContext().getInitParameter("register");
	}
%>
<%
String uname=request.getParameter("user");
String hintqs=request.getParameter("hint_question");
String hintans=request.getParameter("hint_answer");
try{
DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
DocumentBuilder domBuilder = null;
Document doc = null;
factory.setValidating(false);//Set Validating
factory.setIgnoringElementContentWhitespace(false);
domBuilder = factory.newDocumentBuilder();
File fl=new File(database);
if(!fl.exists()) {
	out.println("<center><h4>Sorry ! Currently No User Available</h4><SCRIPT>setFooter();</SCRIPT>");
		return;
}
doc = domBuilder.parse(fl);
Element ell = doc.getDocumentElement();
for(int u=0;u<ell.getChildNodes().getLength();u++) {
String user=ell.getChildNodes().item(u).getChildNodes().item(0).getChildNodes().item(0).getNodeValue().toString();
String fhintqs=ell.getChildNodes().item(u).getChildNodes().item(5).getChildNodes().item(0).getNodeValue().toString();
String fhintans=ell.getChildNodes().item(u).getChildNodes().item(6).getChildNodes().item(0).getNodeValue().toString();
String pwd=ell.getChildNodes().item(u).getChildNodes().item(1).getChildNodes().item(0).getNodeValue().toString();
if(user.equals(uname) && hintqs.equals(fhintqs) && hintans.equals(fhintans)) {
out.println("<center><h4>Your's Password is:&nbsp;&nbsp;\""+pwd+"\"</h4></center><SCRIPT>setFooter();</SCRIPT>");
return;}
}out.println("<center><h4>Incorrect Details.<BR><BR>Please Check The Details Once Again</h4><SCRIPT>setFooter();</SCRIPT>");
}catch(Exception e) {out.println(e);}
%>