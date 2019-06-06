<%@ include file="Common.jsp"%>
<%!
	String database="";
	public void jspInit() {
	String root=System.getProperty("user.dir");
	String split=System.getProperty("file.separator");
	javax.servlet.ServletConfig sconfig=getServletConfig();
	database=sconfig.getServletContext().getInitParameter("jobsearch");
	}
%>
<%
  String expe=request.getParameter("experience");
  String basis=request.getParameter("type");
  String loca=request.getParameter("location");
  String explore=request.getParameter("explore");
  Search.JobSearch socket=new Search.JobSearch(database,explore,expe,basis,loca);
  int pageno=socket.getInteger(request,"pageno");
  int limit=socket.getInteger(request,"limit",5);
  String query=socket.getString(request,"quest");
  String hlight=socket.getString(request,"hlightres","no");
  String navigate=request.getServletPath();navigate=navigate.substring(navigate.lastIndexOf("/")+1);
  String qstring=request.getQueryString();qstring=navigate+"?"+qstring.substring(0,request.getQueryString().indexOf("&pageno"));
  String result=socket.getSearchResults(pageno,limit,query,hlight,qstring);
  out.println(result);
%>
</TABLE><center><BR><BR>
	<button style='width:140px;height:25px;' onclick='location="javascript:history.back()"' accesskey="C"><u>C</u>ontinue Back</button>&nbsp;&nbsp;&nbsp;
	<button style='width:140px;height:25px;' onclick='location="./Home.jsp"' accesskey="N"><u>N</u>avigate Home</button>
<BR><BR><BR></center>
<Table width=780 align=center cellspacing=0 cellpadding=1 border=0>
<TR><Th align=center><font color=gray>For More Details Mail to me at&nbsp;&nbsp;<a href="compose.htm?ghkishore@hotmail.com">ghkishore@hotmail.com</font></a>&nbsp;&nbsp;or meet me at<bR><bR>
<a href="http://ghvishnu.tripod.com/hari.htm">http://ghvishnu.tripod.com/hari.htm</a></th></tr>
</TABLE>
<btody></body></html>