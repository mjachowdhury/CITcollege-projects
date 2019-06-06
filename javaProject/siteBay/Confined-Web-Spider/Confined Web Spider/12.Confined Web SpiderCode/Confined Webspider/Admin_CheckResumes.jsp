<%@page errorPage="Admin_Malicious.jsp" isErrorPage="false"%>
<META Name="Author" name="Jaxp Search">
<META HTTP-EQUIV="page-enter" Content="blendTrans(duration=0.7)">
<LINK HREF="./Style/Search.cdf" REL="stylesheet"></HEAD>
<SCRIPT>var apptit="::Search Engine::"; for(tit=0;tit<150;tit++) apptit+="&nbsp;&nbsp;";document.write("<TITLE>"+apptit+"</TITLE>");</SCRIPT>
<SCRIPT SRC="./JScript/Email.cdf" Type="text/javascript"></SCRIPT>
<SCRIPT SRC="./JScript/Adminmenu.cdf" Type="text/javascript"></SCRIPT><title>Add New Products</title>
<%!
	String database="";
	String adminid="";
	String adminpwd="";
	public void jspInit() {
	String root=System.getProperty("user.dir");
	String split=System.getProperty("file.separator");
	javax.servlet.ServletConfig sconfig=getServletConfig();
	database=sconfig.getServletContext().getInitParameter("postresume");
	}
%>
<body style="overflow:auto">
<title>Resumes Terminal</title>
<%
	Search.CheckResume cr=new Search.CheckResume(database);
	out.println(cr.displayResumes());
%>      