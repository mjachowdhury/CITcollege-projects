<%@ page import="java.io.*"%>
<%@ include file="Common.jsp"%>
<%!
	String database="";
	public void jspInit() {
	String root=System.getProperty("user.dir");
	String split=System.getProperty("file.separator");
	javax.servlet.ServletConfig sconfig=getServletConfig();
	database=sconfig.getServletContext().getInitParameter("topicsearch");
	}
%>
<% 
   String occur=request.getParameter("occur").toLowerCase().trim();
   String conditions=request.getParameter("explore").toLowerCase().trim();
   Search.AdvancedSearchEngine socket=new Search.AdvancedSearchEngine(database,occur,conditions);
   int pageno=socket.getInteger(request,"pageno");
   int limit=socket.getInteger(request,"limit",5);
   String records[][]=socket.getRecords();
   String query=socket.getString(request,"quest");
   String hlight=socket.getString(request,"hlightres","no");
   String navigate=request.getServletPath();navigate=navigate.substring(navigate.lastIndexOf("/")+1);
   String qstring=request.getQueryString();qstring=navigate+"?"+qstring.substring(0,request.getQueryString().indexOf("&pageno"));
   String result=socket.getSearchResults(pageno,limit,query,hlight,qstring);
   out.println(result);
%><SCRIPT>setFooter();</SCRIPT>      