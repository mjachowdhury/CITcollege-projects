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
<TABLE width=780 align=center><TR><TD>
<fieldset><legend>Products Search Engine</legend><BR>
<table width=600 cellspacing=0 cellpadding=3 align=center style="border:1px solid steelblue" id=advtab><form action="./ProductSearch.jsp">
<TR><TD>Search Keyword:</TD><Td><input name=quest></td></tr>
<TR><TD>Explore In:</TD><Td><select name=explore>
<%	
	Search.GetNodes gn=new Search.GetNodes(database);
	out.println(gn.fillNodes());
%>
</select></td></tr>
<TR><TD>Limit Results:</TD><Td><select name=limit>
<option value="5">05 Results per Page
<option value="10">10 Results per Page
<option value="15">15 Results per Page
<option value="20">20 Results per Page
<option value="25">25 Results per Page
</select>
</td></tr>
<TR><TD colspan=2 align=center><input type=hidden name="pageno" value="0"><button type=submit accesskey="S">Search</button>&nbsp;&nbsp;
<button type=reset accesskey="R">Reset</button>
</td></tr></form></table><BR></fieldset></TD></TR></table>
<script>setBg();</script><SCRIPT>setFooter();</SCRIPT>
