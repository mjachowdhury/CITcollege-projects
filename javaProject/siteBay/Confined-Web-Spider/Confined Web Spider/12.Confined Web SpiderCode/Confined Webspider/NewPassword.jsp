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
String exist=request.getParameter("exist");
String pass=request.getParameter("pass");
if(uname==null || exist==null || pass==null) throw new Exception("Provide All Values. To Change PAssword");
try{
DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
DocumentBuilder domBuilder = null;
Document doc = null;
factory.setValidating(false);
factory.setIgnoringElementContentWhitespace(false);
domBuilder = factory.newDocumentBuilder();
File fl=new File(database);
if(!fl.exists()) {
	out.println("<center><h4>Sorry ! Currently No User Available</h4><SCRIPT>setFooter();</SCRIPT>");
		return;
}
doc = domBuilder.parse(fl);
boolean change=false;
Element nl = doc.getDocumentElement();
Element category=doc.createElement("user");
for(int u=0;u<nl.getChildNodes().getLength();u++) {
String uid=nl.getChildNodes().item(u).getChildNodes().item(0).getChildNodes().item(0).getNodeValue().toString();
String pwd=nl.getChildNodes().item(u).getChildNodes().item(1).getChildNodes().item(0).getNodeValue().toString();
Node cpwd=nl.getChildNodes().item(u).getChildNodes().item(1).getChildNodes().item(0);
if(uname.equals(uid) && exist.equals(pwd)) {
	cpwd.setNodeValue(pass);	
	change=true;
	break;}
}
DOMSource ds=new DOMSource(doc);
StreamResult sr=new StreamResult(new FileOutputStream(database));
TransformerFactory tfac=TransformerFactory.newInstance();
Transformer trans=tfac.newTransformer();
trans.transform(ds,sr);
if(change) out.println("<center><h4>Congractulations ! Password Changed Successfully!</h4><SCRIPT>setFooter();</SCRIPT>");
else out.println("<center><h4>Sorry ! Unable To Change Password.<BR><BR>Please Check Details Once Again</h4><SCRIPT>setFooter();</SCRIPT>");
}catch(Exception e) {out.println(e);}
%>