<%@page errorPage="Admin_Malicious.jsp" isErrorPage="false"%>
<%!
	String database="";
	public void jspInit() {
	String root=System.getProperty("user.dir");
	String split=System.getProperty("file.separator");
	javax.servlet.ServletConfig sconfig=getServletConfig();
	database=sconfig.getServletContext().getInitParameter("topicsearch");
	}
%>
<HTML><HEAD>
<META Name="Author" name="Jaxp Search">
<META HTTP-EQUIV="page-enter" Content="blendTrans(duration=0.7)">
<LINK HREF="./Style/Search.cdf" REL="stylesheet"></HEAD>
<SCRIPT>var apptit="::Search Engine::"; for(tit=0;tit<150;tit++) apptit+="&nbsp;&nbsp;";document.write("<TITLE>"+apptit+"</TITLE>");</SCRIPT>
<SCRIPT SRC="./JScript/Email.cdf" Type="text/javascript"></SCRIPT>
<SCRIPT SRC="./JScript/Adminmenu.cdf" Type="text/javascript"></SCRIPT>

<%	String ncategory="";
	String category=request.getParameter("category");
	if(category.equals("new")) {
		ncategory+=request.getParameter("newcategory").toLowerCase();
		if(ncategory.length()==0) {
				out.println("<BR><BR><center><h4>New Category Name must be provided</center></h4></center><br><BR>");
				out.println("<SCRIPT>setFooter();</SCRIPT>");
				return;}
	}
	String url=request.getParameter("url");
	String desc=request.getParameter("desc");
	if(url.length()==0 || desc.length()==0 || category.length()==0) {
				out.println("<BR><BR><center><h4>All Fields are Mandatory</center></h4></center><br><BR>");
				out.println("<script>setFooter();</SCRIPT><Br><BR>");
				return;}
	String params[]={category,ncategory,url,desc};
	Search.AddUrls add=new Search.AddUrls(database,params);
	if(add.insertURL().equals("addanother")) response.sendRedirect("Admin_UrlDetails.jsp");
	else out.println(add.insertURL());
%>
