<%!
	String database="";
	public void jspInit() {
	String root=System.getProperty("user.dir");
	String split=System.getProperty("file.separator");
	javax.servlet.ServletConfig sconfig=getServletConfig();
	database=sconfig.getServletContext().getInitParameter("productsearch");
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
<fieldset><legend>Add New Products</legend><BR>
<table width=500 align=center cellspacing=0 cellpadding=3>
<form method=post action="Admin_AddProducts.jsp">
<TR><TD align=right nowrap>Category:<td><Select name=category onchange=getOther(this);>
<%
	Search.GetNodes gn=new Search.GetNodes(database);
	out.println(gn.fillNodes());
%><option value="new">New Category</option></select>
</td></tr>
<TR style="display:none" id=ncatg><Td align=right nowrap>New Category:<td><input name=newcategory></td></tr>
<TR><TD align=right nowrap>Product Name:<TD><input name=pname></td></tr>
<TR><TD align=right nowrap>Vendor/Company:<TD><input name=vendcomp></td></tr>
<TR><TD align=right nowrap>Unit Cost:<TD><input name=unitcost></td></tr>
<TR><TD align=right nowrap>Website/E-mail:<TD><input name=webmail></td></tr>
<TR><TD align=right nowrap>Description:<TD><textarea name=desc rows=3 cols=60></textarea></td></tr>
<tr><Td colspan=2 align=center><BR>
<button type=submit accesskey="A"><u>A</u>dd Record</button>&nbsp;&nbsp;
<button type=reset accesskey="R"><u>R</u>efresh</button></TD></tr>
</form></table><br></fieldset>
<script>
function getOther(combo) {
var val=combo.options[combo.selectedIndex].value;
if(val=="new") document.getElementById("ncatg").style["display"]="block";
else document.getElementById("ncatg").style["display"]="none";}
document.forms[0].category.options[0].selected=true;
</script>






