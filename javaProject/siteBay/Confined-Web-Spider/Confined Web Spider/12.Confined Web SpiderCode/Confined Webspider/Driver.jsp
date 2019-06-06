<%@ page import="java.net.URL"%>
<%@ page import="java.net.URLConnection"%>
<%@ page import="java.util.Vector"%>
<%@ page import="org.w3c.dom.Document"%>
<%@ page import="org.w3c.dom.Element"%>
<%@ page import="javax.xml.parsers.DocumentBuilderFactory"%>
<%@ page import="javax.xml.parsers.DocumentBuilder"%>
<%
try{
	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	DocumentBuilder domBuilder = null;
	Document doc = null;
	factory.setValidating(false);
	factory.setIgnoringElementContentWhitespace(false);
	domBuilder = factory.newDocumentBuilder();
	String dbase=request.getRequestURL().toString();
	String cpath=request.getContextPath();
	dbase=dbase.substring(0,dbase.indexOf(cpath)+cpath.length());
	URL url=new URL(dbase+"/database/database.xml");
	URLConnection urlc=url.openConnection();
	doc = domBuilder.parse(urlc.getInputStream());
	Vector driver=new Vector();
	Element nl = doc.getDocumentElement();//item(0),item(2),item(4),item(6) --> #text;
	driver.addElement(nl.getChildNodes().item(1).getFirstChild().getNodeValue());
	driver.addElement(nl.getChildNodes().item(3).getFirstChild().getNodeValue());
	driver.addElement(nl.getChildNodes().item(5).getFirstChild().getNodeValue());
	driver.addElement(nl.getChildNodes().item(7).getFirstChild().getNodeValue());
	}catch(Exception e) {out.println(e);}
%>