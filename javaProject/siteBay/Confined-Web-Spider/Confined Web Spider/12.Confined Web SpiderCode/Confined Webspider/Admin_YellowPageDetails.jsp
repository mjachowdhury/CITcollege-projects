<%!
	String database="";
	public void jspInit() {
	String root=System.getProperty("user.dir");
	String split=System.getProperty("file.separator");
	javax.servlet.ServletConfig sconfig=getServletConfig();
	database=sconfig.getServletContext().getInitParameter("yellowpagesearch");
	}
%>
<HTML><HEAD>
<META Name="Author" name="Jaxp Search">
<META HTTP-EQUIV="page-enter" Content="blendTrans(duration=0.7)">
<LINK HREF="./Style/Search.cdf" REL="stylesheet"></HEAD>
<SCRIPT>var apptit="::Search Engine::"; for(tit=0;tit<150;tit++) apptit+="&nbsp;&nbsp;";document.write("<TITLE>"+apptit+"</TITLE>");</SCRIPT>
<SCRIPT SRC="./JScript/Email.cdf" Type="text/javascript"></SCRIPT>
<SCRIPT SRC="./JScript/Adminmenu.cdf" Type="text/javascript"></SCRIPT><title>Add New Products</title>
<body style="overflow:hidden;margin:0px"><fieldset><legend>Add Yellow Pages</legend><BR>
<table width=500 align=center cellspacing=0 cellpadding=3>
<form method=get action="./Admin_AddYellowPages.jsp">
<TR><TD align=right nowrap>City:<td><Select name=city onchange=getOther(this);>
<%
	Search.GetNodes gn=new Search.GetNodes(database);
	out.println(gn.fillNodes());
%><option value="new">New City</option></select>
</td></tr>
<TR style="display:none" id=ncatg><Td align=right nowrap>New City:<td><input name=newcity></td></tr>
<TR><TD align=right nowrap>Company/Industry:<TD><input name=company></td></tr>
<TR><TD align=right nowrap valign=right>Address:<TD><textarea rows=5 cols=30 name=address></textarea></td></tr>
<TR><TD align=right nowrap valign=right>Phone No:<TD><input name=phoneno></td></tr>
<TR><TD align=right nowrap>Website:<TD><input name=website></td></tr>
<TR><TD align=right nowrap>E-mail:<TD><input name=email></td></tr>
<TR><TD align=right nowrap valign=right>Details:<TD><textarea name=details rows=3 cols=60></textarea></td></tr>
<tr><Td colspan=2 align=center><BR>
<button type=submit accesskey="A"><u>A</u>dd Record</button>&nbsp;&nbsp;
<button type=reset accesskey="R"><u>R</u>efresh</button></TD></tr>
</form></table><br></fieldset>
<script>
function getOther(combo) {
var val=combo.options[combo.selectedIndex].value;
if(val=="new") document.getElementById("ncatg").style["display"]="block";
else document.getElementById("ncatg").style["display"]="none";
} document.forms[0].city.options[0].selected=true;
</script>






