<%@ include file="Common.jsp"%>
<%!
	String database="";
	public void jspInit() {
	String root=System.getProperty("user.dir");
	String split=System.getProperty("file.separator");
	javax.servlet.ServletConfig sconfig=getServletConfig();
	database=sconfig.getServletContext().getInitParameter("postresume");
	}
%>
<%
	String params[]={
					request.getParameter("mailid"),
					request.getParameter("resumetitle"),
					request.getParameter("jobcode"),
					request.getParameter("resume"),
					};
	if((params[0]!=null && params[0].length()>0) && (params[3]!=null && params[3].length()>0)) {
	Search.PostResume resume=new Search.PostResume(database,params);
	out.println(resume.uploadResume());
	out.println("<SCRIPT>setFooter();</SCRIPT>");     
	} else {
			out.println("<BR><BR><center><h4><center>E-mail Id and Resume Upload is Mandatory</center></h4>");
			out.println("<SCRIPT>setFooter();</SCRIPT>");     
	}
%>
