<%@page errorPage="Admin_Malicious.jsp" isErrorPage="false"%>
<%!
	String database="";
	public void jspInit() {
	String root=System.getProperty("user.dir");
	String split=System.getProperty("file.separator");
	javax.servlet.ServletConfig sconfig=getServletConfig();
	database=sconfig.getServletContext().getInitParameter("jobsearch");
	}
%>
<HTML><HEAD>
<META Name="Author" name="Jaxp Search">
<META HTTP-EQUIV="page-enter" Content="blendTrans(duration=0.7)">
<LINK HREF="./Style/Search.cdf" REL="stylesheet"></HEAD>
<SCRIPT>var apptit="::Search Engine::"; for(tit=0;tit<150;tit++) apptit+="&nbsp;&nbsp;";document.write("<TITLE>"+apptit+"</TITLE>");</SCRIPT>
<SCRIPT SRC="./JScript/Email.cdf" Type="text/javascript"></SCRIPT>
<SCRIPT SRC="./JScript/Adminmenu.cdf" Type="text/javascript"></SCRIPT><title>Add New Products</title>
<body style="overflow:hidden;margin:0px">
<%
	String ncategory="";
	String category=request.getParameter("category");
	String exp=request.getParameter("experience");
	String type=request.getParameter("type");
	String loc=request.getParameter("location");
	String desc=request.getParameter("desc");
	if(category.equals("new")) {
		ncategory+=request.getParameter("newcategory").toLowerCase();
		if(ncategory.length()==0) {
				out.println("<BR><BR><center><h4>New Category Name must be provided</center></h4></center><br><BR>");
				out.println("<SCRIPT>setFooter();</SCRIPT>");
				return;}
	}if(category.length()==0 || desc.length()==0) {
				out.println("<BR><BR><center><h4>Category and Description are Mandatory</center></h4></center><br><BR>");
				out.println("<SCRIPT>setFooter();</SCRIPT>");
				return;}
	String params[]={category,ncategory,exp,type,loc,desc};
	Search.AddJobs add=new Search.AddJobs(database,params);
	if(add.insertJob().equals("addanother")) response.sendRedirect("Admin_JobDetails.jsp");
	else out.println(add.insertJob());
%>