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
	public Document getDocument(DocumentBuilder db,String database) {
		Document doc=null;
		try{
		File f=new File(database);
		if(f.exists()) 	return db.parse(f);
		else {
			doc=db.newDocument();
			Element el=doc.createElement("users");
			doc.appendChild(el);
			}
		}catch(Exception e) { System.out.println(e);}
		return doc;
	}
%>
<%
	String params[][]= {
				{request.getParameter("user_name"),"userid"},
				{request.getParameter("password"),"password"},
				{request.getParameter("alternate_email"),"email"},
				{request.getParameter("sex"),"sex"},
				{request.getParameter("date"),"dob"},
				{request.getParameter("hint_question"),"hintqs"},
				{request.getParameter("hint_answer"),"hintans"},
				{request.getParameter("address"),"address"},
				{request.getParameter("occupation"),"occupation"},
				{request.getParameter("zodiac"),"sunsign"}
				};
try{
DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
DocumentBuilder domBuilder = null;
Document doc = null;
factory.setValidating(false);
factory.setIgnoringElementContentWhitespace(false);
domBuilder = factory.newDocumentBuilder();
doc=getDocument(domBuilder,database);
Element ell,userid,password,email,sex,dob,hintqs,hintans,address,occupation,zodiac;
ell = doc.getDocumentElement();
Element category=doc.createElement("user");
for(int u=0;u<ell.getChildNodes().getLength();u++) {
String already=ell.getChildNodes().item(u).getChildNodes().item(0).getChildNodes().item(0).getNodeValue().toString();
if(params[0][0].equals(already)) {
	out.println("<center><h4>Sorry! Already Such User Exist.<BR><BR>Please Choose Another ID</h4></center><SCRIPT>setFooter();</SCRIPT>");
	return;}
	}
for(int i=0;i<params.length;i++) {
	 Element tag=doc.createElement(params[i][1]);
	 Text node=doc.createTextNode(params[i][0]);
	 tag.appendChild(node);
	 category.appendChild(tag);
	 ell.appendChild(category);
 }
DOMSource ds=new DOMSource(doc);
StreamResult sr=new StreamResult(new FileOutputStream(database));
TransformerFactory tfac=TransformerFactory.newInstance();
Transformer trans=tfac.newTransformer();
trans.transform(ds,sr);
out.println("<center><h4>Congractulations ! Registration Completed Successfully!</h4></center><SCRIPT>setFooter();</SCRIPT>");
}catch(Exception e) {out.println(e);}
%>