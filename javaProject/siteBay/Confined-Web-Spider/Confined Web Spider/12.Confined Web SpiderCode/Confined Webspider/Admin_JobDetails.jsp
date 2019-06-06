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

<title>Add Job Details</title>
<body style="overflow:scroll">
<fieldset><legend>Add Job Details</legend><BR>
<table width=500 align=center cellspacing=0 cellpadding=3>
<form method=post action="./Admin_AddJobs.jsp">
<TR><TD align=right nowrap>Category:<td><Select name=category onchange=getOther(this);>
<%
	Search.GetNodes gn=new Search.GetNodes(database);
	out.println(gn.fillNodes());
%><option value="new">New Category</option></select>
</td></tr>
<TR style="display:none" id=ncatg><Td align=right nowrap>New Category:<td><input name=newcategory></td></tr>
<TR><TD align=right>Experience:</TD><Td><select name=experience>
<option value="0">Fresher
<option value="1">0-1 year(s)
<option value="2">2-years
<option value="3">3-years
<option value="above">4-years or Above
</select>
</td></tr>
<TR><TD align=right nowrap>Type:<TD><select name=type>
<option value="full">Full Time</option>
<option value="part">Part Time</option>
<option value="contract">Contract</option>
</select></td></tr>
<TR><TD align=right nowrap>Location:<TD><select name=location>
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
<option value="vizag">Visakhapatnam</option></select></td></tr>
<TR><TD align=right nowrap valign=top>Description:<TD><textarea name=desc rows=3 cols=60></textarea></td></tr>
<tr><Td colspan=2 align=center><BR>
<button type=submit accesskey="A"><u>A</u>dd Record</button>&nbsp;&nbsp;
<button type=reset accesskey="R"><u>R</u>efresh</button></TD></tr>
</form></table><BR></fieldset>
<script>
function getOther(combo) {
var val=combo.options[combo.selectedIndex].value;
if(val=="new") document.getElementById("ncatg").style["display"]="block";
else document.getElementById("ncatg").style["display"]="none";}
document.forms[0].category.options[0].selected=true;
</script>








