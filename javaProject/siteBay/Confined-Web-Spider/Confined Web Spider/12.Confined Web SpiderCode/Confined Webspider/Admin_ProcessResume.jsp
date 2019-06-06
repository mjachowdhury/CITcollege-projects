<%@page errorPage="Admin_Malicious.jsp" isErrorPage="false"%>
<META Name="Author" name="Jaxp Search">
<META HTTP-EQUIV="page-enter" Content="blendTrans(duration=0.7)">
<LINK HREF="./Style/Search.cdf" REL="stylesheet"></HEAD>
<SCRIPT>var apptit="::Search Engine::"; for(tit=0;tit<150;tit++) apptit+="&nbsp;&nbsp;";document.write("<TITLE>"+apptit+"</TITLE>");</SCRIPT>
<SCRIPT SRC="./JScript/Email.cdf" Type="text/javascript"></SCRIPT>
<SCRIPT SRC="./JScript/Adminmenu.cdf" Type="text/javascript"></SCRIPT><title>Add New Products</title>
<body style="overflow:auto">
<title>Resumes Terminal</title>
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
<%	String[] nothing={"nothing"};
	String[] mails=request.getParameterValues("mailid")!=null?request.getParameterValues("mailid"):nothing;
	if(mails[0].equals("nothing")) {
	out.println("<BR><BR><center><h4>Please Select Resume To Delete</h4><br><BR><SCRIPT>setFooter();</SCRIPT>");
	return;}
	Search.ProcessResume pr=new Search.ProcessResume(database);
	if(pr.deleteResume(mails).equals("deleteanother")) response.sendRedirect("Admin_CheckResumes.jsp");
	out.println(pr.deleteResume(mails));
%>