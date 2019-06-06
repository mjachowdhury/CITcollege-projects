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
<TABLE width=780 align=center><TR><TD><fieldset><legend>Jobs Search Engine</legend><BR>
<table width=600 cellspacing=0 cellpadding=3 align=center style="border:1px solid steelblue" id=advtab><form action="./JobSearch.jsp">
<TR><TD>Search Keyword:</TD><Td><input name=quest></td></tr>
<TR><TD>Jobs Based on:</TD><Td><select name=explore>
   
<%
	Search.GetNodes gn=new Search.GetNodes(database);
	out.println(gn.fillNodes());
%>
</select>
</td></tr>
<TR><TD>Experience:</TD><Td><select name=experience>
<option value="0">Fresher
<option value="1">0-1 year(s)
<option value="2">2-years
<option value="3">3-years
<option value="above">4-years or Above
</select>
</td></tr>
<TR><TD>Type:</TD><Td><select name=type>
<option value="full">Full Time</option>
<option value="part">Part Time</option>
<option value="contract">contract</option>
</select>
</td></tr>
<TR><TD>Location:</TD><Td><select name=location>
<option value="ahmedabad">Ahmedabad</option>
<option value="bhuhaneswar">Bhuhaneswar</option>
<option value="bangalore">Bangalore</option>
<option value="chennai">Chennai</option>
<option value="chandigarh">Chandigarh</option>
<option value="delhi">Delhi</option>
<option value="hyderabad">Hyderabad</option>
<option value="jaipur">Jaipur</option>
<option value="lucknow">Lucknow</option>
<option value="mumbai">Mumbai</option>
<option value="pune">Pune</option>
<option value="vijayawada">Vijayawada</option>
<option value="vizag">Visakhapatnam</option></select>
</td></tr>
<TR><TD>Limit Results:</TD><Td><select name=limit>
<option value="5">05 Results per Page
<option value="10">10 Results per Page
<option value="15">15 Results per Page
<option value="20">20 Results per Page
<option value="25">25 Results per Page
</select>
</td></tr>
<TR><TD colspan=2 align=center><input type=hidden name=pageno value=0><button type=submit accesskey="S">Search</button>&nbsp;&nbsp;
<button type=reset accesskey="R">Reset</button>
</td></tr>
</form></table><BR></fieldset></TD></TR></TABLE>
<script>setBg();</script><SCRIPT>setFooter();</SCRIPT>
