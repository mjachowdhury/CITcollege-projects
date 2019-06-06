<%@ include file="Common.jsp"%>
<%!
	String database="";
	public void jspInit() {
	String root=System.getProperty("user.dir");
	String split=System.getProperty("file.separator");
	javax.servlet.ServletConfig sconfig=getServletConfig();
	database=sconfig.getServletContext().getInitParameter("yellowpagesearch");
	}
%>
<TABLE align=center width=780><TR><TD>
<%
  String explore=request.getParameter("city");
  Search.YellowPagesSearch socket=new Search.YellowPagesSearch(database,explore);
  int pageno=socket.getInteger(request,"pageno");
  int limit=socket.getInteger(request,"limit",5);
  String query=socket.getString(request,"keyword");
  String hlight=socket.getString(request,"hlightres","yes");
  String navigate=request.getServletPath();navigate=navigate.substring(navigate.lastIndexOf("/")+1);
  String qstring=request.getQueryString();qstring=navigate+"?"+qstring.substring(0,request.getQueryString().indexOf("&pageno"));
  String result=socket.getSearchResults(pageno,limit,query,hlight,qstring);
  out.println(result+"</TABLE>");
%></TD></TR></TABLE><SCRIPT>setFooter();</SCRIPT>      