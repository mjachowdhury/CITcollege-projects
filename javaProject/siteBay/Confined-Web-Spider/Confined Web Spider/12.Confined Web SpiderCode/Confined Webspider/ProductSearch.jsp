<%@ include file="Common.jsp"%>
<%!
	String database="";
	public void jspInit() {
	String root=System.getProperty("user.dir");
	String split=System.getProperty("file.separator");
	javax.servlet.ServletConfig sconfig=getServletConfig();
	database=sconfig.getServletContext().getInitParameter("productsearch");
	}
%>
<%
   String explore=request.getParameter("explore");
   Search.ProductSearch socket=new Search.ProductSearch(database,explore);
   int pageno=socket.getInteger(request,"pageno");
   int limit=socket.getInteger(request,"limit",5);
   String query=socket.getString(request,"quest");
   String hlight=socket.getString(request,"hlightres","yes");
   String navigate=request.getServletPath();navigate=navigate.substring(navigate.lastIndexOf("/")+1);
   String qstring=request.getQueryString();qstring=navigate+"?"+qstring.substring(0,request.getQueryString().indexOf("&pageno"));
   String result=socket.getSearchResults(pageno,limit,query,hlight,qstring);
   out.println(result);
%><SCRIPT>setFooter();</SCRIPT>      