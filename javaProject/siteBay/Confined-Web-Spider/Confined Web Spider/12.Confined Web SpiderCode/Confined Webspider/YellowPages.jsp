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
<title>Login New User</title>
<TABLE align=center width=780><TR><TD><fieldset><legend align=center>Search Yellow Pages With Few Letters of Company</legend><BR>
<table width=300 align=center cellspacing=2 cellpadding=5><form action="./YellowPageSearch.jsp">
<TR><TD align=right nowrap>Search For :</td><td><input name=keyword></td></tr>
<TR><TD align=right nowrap>City:</td><TD><Select name=city>
<%
	Search.GetNodes gn=new Search.GetNodes(database);
	out.println(gn.fillNodes());
%></select>
</td></tr>
<TR><TD colspan=2 align=center><input type=hidden value=0 name=pageno><button type=submit accesskey="S">Search</button>&nbsp;&nbsp;
<button type=reset accesskey="R">Reset</button>
</td></tr>
</form>
</TABLE><BR></fieldset></TD></TR></table><script>setFooter();</SCRIPT>
