<%@page errorPage="Admin_Malicious.jsp" isErrorPage="false"%>
<%!
	String database="";
	public void jspInit() {
	String root=System.getProperty("user.dir");
	String split=System.getProperty("file.separator");
	javax.servlet.ServletConfig sconfig=getServletConfig();
	database=sconfig.getServletContext().getInitParameter("yellowpagesearch");
	}
%>
<META Name="Author" name="Jaxp Search">
<META HTTP-EQUIV="page-enter" Content="blendTrans(duration=0.7)">
<LINK HREF="./Style/Search.cdf" REL="stylesheet"></HEAD>
<SCRIPT>var apptit="::Search Engine::"; for(tit=0;tit<150;tit++) apptit+="&nbsp;&nbsp;";document.write("<TITLE>"+apptit+"</TITLE>");</SCRIPT>
<SCRIPT SRC="./JScript/Email.cdf" Type="text/javascript"></SCRIPT>
<SCRIPT SRC="./JScript/Adminmenu.cdf" Type="text/javascript"></SCRIPT><title>Add New Products</title>
<%
String city=request.getParameter("city");
String ncity="";
String company=request.getParameter("company");
String address=request.getParameter("address");
String phoneno=request.getParameter("phoneno");
String website=request.getParameter("website");
String email=request.getParameter("email");
String details=request.getParameter("details");
if(city.equals("new")) {
		ncity+=request.getParameter("newcity").toLowerCase();
		if(ncity.length()==0) {
				out.println("<BR><BR><center><h4>New City Name must be provided</center></h4></center><br><BR>");
				out.println("<SCRIPT>setFooter();</SCRIPT>");
				return;}
	}if(city.length()==0 || details.length()==0) {
				out.println("<BR><BR><center><h4>Category and Description are Mandatory</center></h4></center><br><BR>");
				out.println("<SCRIPT>setFooter();</SCRIPT>");
				return;}
	String params[]={city,ncity,company,address,phoneno,website,email,details};
	Search.AddYellowPages add=new Search.AddYellowPages(database,params);
	if(add.insertYellowPage().equals("addanother")) response.sendRedirect("Admin_YellowPageDetails.jsp");
	else out.println(add.insertYellowPage());
%>